package hms.cpaas.kuppiya.ideamart.connector.ussd;

import hms.cpaas.kuppiya.api.domain.USSDSendRequestObject;
import hms.cpaas.kuppiya.ideamart.connector.MtExecutor;
import hms.cpaas.kuppiya.ideamart.connector.ussd.domain.USSDSendRequest;
import hms.cpaas.kuppiya.ideamart.connector.ussd.domain.USSDSendConfirmation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Service
public class USSDMtExecutor implements MtExecutor<USSDSendRequest, USSDSendConfirmation> {
    private static final Logger LOGGER = LoggerFactory.getLogger(USSDMtExecutor.class);

    @Value("${ideamart.base.url}")
    private String ideamartBaseUrl;
    @Value("${ideamart.ussd.mt.url.fragment}")
    private String ussdMtUrlFragment;

    private WebClient ideamartApiClient;

    @PostConstruct
    public void init() {
        this.ideamartApiClient = WebClient
                .builder()
                .clientConnector(new ReactorClientHttpConnector())
                .baseUrl(ideamartBaseUrl)
                .build();
    }

    @Override
    public Mono<USSDSendConfirmation> sendRequest(USSDSendRequest mtRequest) {
        LOGGER.debug("Sending USSDConfirmRequest [{}] path [{}]", mtRequest, ussdMtUrlFragment);
        USSDSendRequestObject convertedRequest = convert(mtRequest);
        return ideamartApiClient
                .post()
                .uri(ussdMtUrlFragment)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(fromObject(convertedRequest))
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(USSDSendConfirmation.class))
                .doOnSuccess(response -> {
                    LOGGER.info("USSDMtRequest response [{}]", response);
                })
                .doOnError(err -> {
                    LOGGER.error("Error while retrieving USSDMtRequest response ", err);
                })
                .onErrorResume(err -> createErrorResumeResponse(err, mtRequest));
    }

    private USSDSendRequestObject convert(USSDSendRequest request) {
        USSDSendRequestObject convertedRequest = new USSDSendRequestObject();
        convertedRequest.setApplicationId(request.getApplicationId());
        convertedRequest.setPassword(request.getPassword());
        convertedRequest.setDestinationAddress(request.getDestinationAddress());
        convertedRequest.setUssdOperation(request.getUssdOperation().getValue());
        convertedRequest.setEncoding(request.getEncoding().getValue());
        convertedRequest.setSessionId(request.getSessionId());
        convertedRequest.setVersion(request.getVersion());
        convertedRequest.setMessage(request.getMessage());
        return convertedRequest;
    }
    private Mono<USSDSendConfirmation> createErrorResumeResponse(final Throwable e,
                                                                       final USSDSendRequest request) {
        LOGGER.error("Exception Occurred on server-response ", e);
        USSDSendConfirmation errorResponse = new USSDSendConfirmation(
                request.getVersion(),
                request.getSessionId(),
                LocalDateTime.now().toString(),
                "E14010",
                "Error occurred while handling response");
        return Mono.just(errorResponse);
    }
}
