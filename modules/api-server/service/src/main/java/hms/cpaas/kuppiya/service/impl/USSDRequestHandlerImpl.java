package hms.cpaas.kuppiya.service.impl;

import hms.cpaas.kuppiya.api.error.ErrorCodes;
import hms.cpaas.kuppiya.ideamart.connector.ussd.USSDApi;
import hms.cpaas.kuppiya.ideamart.connector.ussd.USSDMoListener;
import hms.cpaas.kuppiya.ideamart.connector.ussd.USSDMtExecutor;
import hms.cpaas.kuppiya.ideamart.connector.ussd.USSDOperation;
import hms.cpaas.kuppiya.ideamart.connector.ussd.domain.USSDReceiveIndication;
import hms.cpaas.kuppiya.ideamart.connector.ussd.domain.USSDReceiveResponse;
import hms.cpaas.kuppiya.ideamart.connector.ussd.domain.USSDSendConfirmation;
import hms.cpaas.kuppiya.ideamart.connector.ussd.domain.USSDSendRequest;
import hms.cpaas.kuppiya.persistence.mongo.apiSession.ApiSessionDataRepositoryImpl;
import hms.cpaas.kuppiya.persistence.mysql.session.SessionStatus;
import hms.cpaas.kuppiya.persistence.mysql.session.ussd.USSDSession;
import hms.cpaas.kuppiya.persistence.mysql.session.ussd.USSDSessionManager;
import hms.cpaas.kuppiya.service.USSDRequestHandler;
import hms.cpaas.kuppiya.service.config.SystemConfigurationService;
import hms.cpaas.kuppiya.service.config.ussd.*;
import hms.cpaas.kuppiya.service.ussd.USSDOptionRetriever;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class USSDRequestHandlerImpl implements USSDRequestHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(USSDRequestHandlerImpl.class);

    private final USSDSessionManager sessionManager;
    private final ApiSessionDataRepositoryImpl sessionDataRepository;
    private final SystemConfigurationService systemConfigurationService;
    private final USSDOptionRetriever optionRetriever;
    private final USSDMoListener ussdMoListener;
    private final USSDMtExecutor ussdMtExecutor;

    @Value("${ideamart.application.password}")
    private String ideamartAppPassword;

    @Autowired
    public USSDRequestHandlerImpl(USSDSessionManager sessionManager,
                                  ApiSessionDataRepositoryImpl sessionDataRepository,
                                  SystemConfigurationService systemConfigurationService,
                                  USSDOptionRetriever optionRetriever,
                                  USSDMoListener ussdMoListener,
                                  USSDMtExecutor ussdMtExecutor) {
        this.sessionManager = sessionManager;
        this.sessionDataRepository = sessionDataRepository;
        this.systemConfigurationService = systemConfigurationService;
        this.optionRetriever = optionRetriever;
        this.ussdMoListener = ussdMoListener;
        this.ussdMtExecutor = ussdMtExecutor;
    }

    @Override
    public Mono<USSDReceiveResponse> handleUSSDFlow(String requestId, USSDReceiveIndication indication) {
        MDC.put("trxId", requestId);
        try {
            USSDFlowConfig ussdFlowConfig = systemConfigurationService.loadUSSDFlowConfigWithError().getFlowConfig();
            if (indication.getUssdOperation().equals(USSDOperation._MO_INIT)) {
                USSDSession session = new USSDSession();
                session.setSessionId(indication.getSessionId());
                session.setMaskedMsisdn(indication.getSourceAddress());
                session.setCurrentMenu(ussdFlowConfig.getBaseMenu().getId());
                session.setCurrentAction(ussdFlowConfig.getBaseMenu().getId());
                session.setUssdOperation(indication.getUssdOperation().getValue());
                session.setStatus(SessionStatus.ONLINE);

                sessionDataRepository.saveSessionData(indication.getSessionId(), indication.getSourceAddress()).subscribe();

                return ussdMoListener
                        .onReceived(indication)
                        .flatMap(ind -> sessionManager.createSession(session))
                        .map(ussdSession -> getFlowActionForBaseMenu(ussdFlowConfig.getBaseMenu()))
                        .flatMap(nextAction -> ussdMtExecutor.sendRequest(getUSSDSendRequestFromNextAction(nextAction, indication)))
                        .map(this::getReceiveResponseFromUssdMTResponse)
                        .switchIfEmpty(Mono.defer(() ->
                                Mono.just(USSDApi.newReceiveUSSD(
                                        ErrorCodes.UNEXPECTED_SERVER_ERROR,
                                        "Error occurred while processing request"))));
            } else {
                return ussdMoListener
                        .onReceived(indication)
                        .flatMap(ind -> sessionManager.findUSSDSessionBySessionIdAndMaskedMsisdn(indication.getSessionId(), indication.getSourceAddress()))
                        .map(sessionData -> {
                            sessionDataRepository.updateSessionAction(indication.getSessionId(), indication.getSourceAddress(),
                                    sessionData.getCurrentAction(), indication.getMessage(), sessionData.getCurrentMenu()).subscribe();
                            return sessionData;
                        })
                        .flatMap(ussdSession -> retrieveAndUpdateNextActionForSession(
                                ussdSession.getCurrentMenu(),
                                ussdSession.getCurrentAction(),
                                ussdFlowConfig,
                                ussdSession,
                                indication.getMessage()))
                        .flatMap(nextAction -> ussdMtExecutor.sendRequest(getUSSDSendRequestFromNextAction(nextAction, indication)))
                        .map(this::getReceiveResponseFromUssdMTResponse)
                        .switchIfEmpty(Mono.defer(() ->
                                Mono.just(USSDApi.newReceiveUSSD(
                                        ErrorCodes.UNEXPECTED_SERVER_ERROR,
                                        "Error occurred while processing request"))));
            }
        } finally {
            MDC.clear();
        }
    }

    private USSDReceiveResponse getReceiveResponseFromUssdMTResponse(USSDSendConfirmation confirmation) {
        USSDReceiveResponse response = new USSDReceiveResponse();
        response.setStatusCode(confirmation.getStatusCode());
        response.setStatusDetail(confirmation.getStatusDetail());
        return response;
    }

    private USSDFlowAction getFlowActionForBaseMenu(BaseMenu menu) {
        USSDFlowAction ussdFlowAction = new USSDFlowAction();
        ussdFlowAction.setId(menu.getId());
        ussdFlowAction.setTitle(menu.getTitle());
        ussdFlowAction.setPriority(0);
        ussdFlowAction.setOptions(menu.getOptions());
        return ussdFlowAction;
    }

    private USSDSendRequest getUSSDSendRequestFromNextAction(USSDFlowAction action, USSDReceiveIndication indication) {
        USSDSendRequest sendRequest = new USSDSendRequest();
        sendRequest.setApplicationId(indication.getApplicationId());
        sendRequest.setPassword(ideamartAppPassword);
        sendRequest.setDestinationAddress(indication.getSourceAddress());
        sendRequest.setEncoding(indication.getEncoding());
        sendRequest.setVersion(indication.getVersion());
        sendRequest.setUssdOperation(USSDOperation._MO_CONT);
        sendRequest.setMessage(getStringFromUssdAction(action));
        sendRequest.setSessionId(indication.getSessionId());
        return sendRequest;
    }

    private String getStringFromUssdAction(USSDFlowAction action) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(action.getTitle()).append("\n");
        for (MenuOption option: action.getOptions()) {
            stringBuilder.append(option.getValue()).append("\n");
        }
        return stringBuilder.toString();
    }

    private Mono<USSDFlowAction> retrieveAndUpdateNextActionForSession(String flowId, String actionId,
                                                                          USSDFlowConfig config, USSDSession session,
                                                                          String message) {
        if (message.equalsIgnoreCase("000")) {
            return Mono.just(config.getFinishedAction());
        }

        if (flowId.equalsIgnoreCase(config.getBaseMenu().getId())) {
            Optional<MenuOption> selectedBaseMenuOption = config.getBaseMenu().getOptions()
                    .stream()
                    .filter(flow -> flow.getValue().contains(message))
                    .findFirst();
            if (selectedBaseMenuOption.isPresent()) {
                MenuOption selectedBaseMenuOpt = selectedBaseMenuOption.get();
                Optional<USSDFlow> ussdFlowOptional = config.getAvailableFlows()
                        .stream()
                        .filter(flow -> flow.getId().equalsIgnoreCase(selectedBaseMenuOpt.getRef()))
                        .findFirst();

                if (ussdFlowOptional.isPresent()) {
                    USSDFlow ussdFlow = ussdFlowOptional.get().copy();
                    if (ussdFlow.getFlowActions().size() > 0) {

                        return getCompleteNextAction(config, session, ussdFlow, 0);
                    } else {
                        return Mono.empty();
                    }
                } else {
                    return Mono.empty();
                }
            } else {
                return Mono.empty();
            }
        }

        Optional<USSDFlow> ussdFlowOptional = config.getAvailableFlows()
                .stream()
                .filter(flow -> flow.getId().equalsIgnoreCase(flowId))
                .findFirst();
        if (ussdFlowOptional.isPresent()) {

            USSDFlow ussdFlow = ussdFlowOptional.get().copy();

            Optional<USSDFlowAction> ussdActionOptional = ussdFlow.getFlowActions()
                    .stream()
                    .filter(action -> action.getId().equalsIgnoreCase(actionId))
                    .findFirst();

            if (ussdActionOptional.isPresent()) {
                USSDFlowAction ussdFlowAction = ussdActionOptional.get();

                int actionIndex = ussdFlow.getFlowActions().indexOf(ussdFlowAction);
                if (actionIndex >= ussdFlow.getFlowActions().size() - 1) {
                    session.setStatus(SessionStatus.COMPLETED);
                    sessionManager.updateSession(session).subscribe();

                    return Mono.just(config.getFinishedAction());
                } else {
                    int nextActionIndex;

                    if (message.contains("999")) {
                        nextActionIndex = actionIndex - 1;
                    } else {
                        nextActionIndex = actionIndex + 1;
                    }

                    return getCompleteNextAction(config, session, ussdFlow, nextActionIndex);
                }
            } else {
                return Mono.empty();
            }
        } else {
            return Mono.empty();
        }
    }

    private Mono<USSDFlowAction> getCompleteNextAction(USSDFlowConfig config, USSDSession session, USSDFlow ussdFlow, int i) {
        USSDFlowAction nextAction = ussdFlow.getFlowActions().get(i);
        session.setCurrentMenu(ussdFlow.getId());
        session.setCurrentAction(nextAction.getId());
        session.setStatus(SessionStatus.ONLINE);
        sessionManager.updateSession(session).subscribe();

        return optionRetriever
                .retrieveMenuOptions(nextAction.getId(), session.getSessionId(), session.getMaskedMsisdn())
                .collectList()
                .map(options -> updateOptions(nextAction, config.getCommonMenuOptions(), options, ussdFlow.getTitle()));
    }

    public USSDFlowAction updateOptions(USSDFlowAction action,
                                        List<MenuOption> commonOptions,
                                        List<MenuOption> derivedOptions,
                                        String flowTitle) {

        action.getOptions().addAll(derivedOptions);

        if (action.getId().equalsIgnoreCase("baseMenu") || action.getId().equalsIgnoreCase("exitMenu")) {
            commonOptions.stream()
                    .filter(commonOption -> commonOption.getId().equalsIgnoreCase("exit"))
                    .findFirst()
                    .ifPresent(e -> action.getOptions().add(e));
        } else {
            action.getOptions().addAll(commonOptions);
        }


        if (Objects.isNull(action.getTitle()) || action.getTitle().trim().isEmpty()) {
            action.setTitle(flowTitle);
        }

        return action;
    }
}
