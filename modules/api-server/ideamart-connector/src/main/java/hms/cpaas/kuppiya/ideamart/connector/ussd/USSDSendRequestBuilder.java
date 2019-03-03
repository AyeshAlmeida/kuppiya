package hms.cpaas.kuppiya.ideamart.connector.ussd;

import hms.cpaas.kuppiya.ideamart.connector.AbstractSDKBuilders;
import hms.cpaas.kuppiya.ideamart.connector.InvalidRequestExcpetion;
import hms.cpaas.kuppiya.ideamart.connector.MessageEncoding;
import hms.cpaas.kuppiya.ideamart.connector.MsisdnUtils;
import hms.cpaas.kuppiya.ideamart.connector.ussd.domain.USSDSendRequest;

import java.util.Optional;

/**
 * USSDSendRequestBuilder generates a USSD Send Request with following format..
 *
 * <pre>
 *     {
 *         "version": "1.0",
 *         "applicationId": "APP_000001",
 *         "password": "password",
 *         "sessionId": "1330929317043",
 *         "ussdOperation": "mt-cont",
 *         "destinationAddress": "tel: 94711122336 ",
 *         "encoding": "440"
 *
 *     }
 * </pre>
 * <p>
 * This builder does not provide configuration methods for {@code applicationId},  {@code password} or {@code version}
 * and those values are provided through the configurations.
 * </p>
 * <p>
 * The configurations for {@code applicationId},  {@code password} and {@code version} are resolved based on following rules.
 * </p>
 *
 * <ol>
 *     <li>If the platform is configured using {@link #forPlatform(String)} method, the configurations are loaded based on the platform</li>
 *     <li>In only one platform is configured, the configured platform configurations are loaded</li>
 *     <li>For multi-platform systems, platform should be configured using {@link #forPlatform(String)} and if not configured,
 *  {@link InvalidRequestExcpetion} will be thrown</li>
 * </ol>
 */
public class USSDSendRequestBuilder extends AbstractSDKBuilders {

    private String platform;
    private String sessionId;
    private USSDOperation ussdOperation = USSDOperation._MO_CONT;
    private String destinationAddress;
    private MessageEncoding encoding = MessageEncoding._0;

    USSDSendRequestBuilder() {

    }

    /**
     * <p>
     * This configures the platform to resolve {@code applicationId},  {@code password} and {@code version}.
     * For single platform applications, this method is not mandatory and the parameters are resolved based
     * on the configuration. For multi-platform applications, platform should be configured.
     * </p>
     * <p>
     * Platform value should be the key of one of the configurations in the {@literal tap.conf} configuration file.
     * If the configuration is not found during the creation, {@link InvalidRequestExcpetion} will be thrown.
     * </p>
     *
     * @param platform to resolve {@code applicationId},  {@code password} and {@code version}
     * @return this builder.
     */
    public USSDSendRequestBuilder forPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    /**
     * <p>Configures Session Id</p>
     * <p>Session Id is mandatory and if not configured, {@link InvalidRequestExcpetion} will be thrown in creation time.</p>
     *
     * @param sessionId Session Id
     * @return this builder.
     */
    public USSDSendRequestBuilder sessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    /**
     * <p>Configures USSD Operation</p>
     * <p>USSD Operation is mandatory and if not configured, {@link InvalidRequestExcpetion} will be thrown in creation time.</p>
     *
     * @param ussdOperation USSD Operation
     * @return this builder.
     */
    public USSDSendRequestBuilder ussdOperation(USSDOperation ussdOperation) {
        this.ussdOperation = ussdOperation;
        return this;
    }

    /**
     * <p>Configures Destination Address</p>
     * <p>Destination Address is mandatory and if not configured, {@link InvalidRequestExcpetion} will be thrown in creation time.</p>
     *
     * @param msisdn Destination Address
     * @return this builder.
     */
    public USSDSendRequestBuilder destinationAddress(String msisdn) {
        this.destinationAddress = msisdn.startsWith("tel:") ? msisdn : MsisdnUtils.toValidMsisdn(msisdn);
        return this;
    }

    /**
     * <p>Configures whether the encoding is required.</p>
     *
     * @param encoding encoding of the message
     * @return this builder.
     */
    public USSDSendRequestBuilder encoding(MessageEncoding encoding) {
        this.encoding = encoding;
        return this;
    }

    public USSDSendRequest create() {
        if (sessionId == null) {
            throw new InvalidRequestExcpetion("Session ID should not be null");
        }
        if (destinationAddress == null) {
            throw new InvalidRequestExcpetion("Destination Address should not be null");
        }

        if (remoteConfigs.size() == 1) {
            platform = remoteConfigs.get(0).getOperator();
        }

        if (platform == null)
            throw new InvalidRequestExcpetion("Platform cannot be empty. [Please note that in single platform systems platform is automatically resolved]");

        Optional<RemoteConfig> configOp = getRemoteConfig(platform);
        if (!configOp.isPresent()) {
            throw new InvalidRequestExcpetion("Invalid platform is configured.");
        }

        RemoteConfig config = configOp.get();

        USSDSendRequest ussdSendRequest = new USSDSendRequest();
        ussdSendRequest.setApplicationId(config.getApplicationId());
        ussdSendRequest.setPassword(config.getPassword());
        ussdSendRequest.setVersion(config.getVersion());
        ussdSendRequest.setSessionId(this.sessionId);
        ussdSendRequest.setUssdOperation(ussdOperation);
        ussdSendRequest.setDestinationAddress(this.destinationAddress);
        ussdSendRequest.setEncoding(this.encoding);

        return ussdSendRequest;
    }

    /**
     * generates json representation of the request.
     *
     * @return json representation of USSD Send Request
     */
    public String asJson() {
        return toJson(create());
    }
}
