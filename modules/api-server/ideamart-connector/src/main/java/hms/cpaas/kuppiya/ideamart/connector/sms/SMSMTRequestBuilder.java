package hms.cpaas.kuppiya.ideamart.connector.sms;

import hms.cpaas.kuppiya.ideamart.connector.AbstractSDKBuilders;
import hms.cpaas.kuppiya.ideamart.connector.InvalidRequestExcpetion;
import hms.cpaas.kuppiya.ideamart.connector.MessageEncoding;
import hms.cpaas.kuppiya.ideamart.connector.MsisdnUtils;
import hms.cpaas.kuppiya.ideamart.connector.sms.domain.SMSMTRequest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * SMSMTRequestBuilder generates a Mobile Terminated SMS Request with following format..
 *
 * <pre>
 *     {
 *         "version": "1.0",
 *         "applicationId": "APP_999999",
 *         "password": "password",
 *         "message": "Hello",
 *         "destinationAddresses": [
 *         "tel:94716177393"
 *         ],
 *         "sourceAddress": "77000",
 *         "deliveryStatusRequest": "1",
 *         "encoding": "245",
 *         "binaryHeader": "526574697265206170706c69636174696f6e20616e642072656c6561736520524b7320696620666f756e642065787069726564"
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
 *
 * @author Tiran Wijesekara.
 */
public class SMSMTRequestBuilder extends AbstractSDKBuilders {

    private String message;
    private List<String> destinationAddresses;
    private String sourceAddress;
    private String deliveryStatusRequest = "0";
    private MessageEncoding encoding = MessageEncoding._0;
    private String binaryHeader;
    private String platform;

    SMSMTRequestBuilder() {

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
    public SMSMTRequestBuilder forPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    /**
     * <p>Configures message</p>
     * <p>Message is mandatory and if not configured, {@link InvalidRequestExcpetion} will be thrown in creation time.</p>
     *
     * @param message to be send as the content.
     * @return this builder.
     */
    public SMSMTRequestBuilder message(String message) {
        this.message = message;
        return this;
    }

    /**
     * <p>Configures Destination addresses</p>
     *
     * <p>Destination addresses are mandatory. If the destination address is not configured via one of {@link #to(List)},
     * {@link #to(String)} or {@link #toAll()}, {@link InvalidRequestExcpetion} will be thrown in creation time.</p>
     *
     * @param destinationAddresses list of receiver addresses.
     * @return this builder.
     */
    public SMSMTRequestBuilder to(List<String> destinationAddresses) {
        this.destinationAddresses = destinationAddresses.stream()
                .map(MsisdnUtils::toValidMsisdn)
                .collect(Collectors.toList());
        return this;
    }

    /**
     * <p>Configures destination address</p>
     *
     * <p>Destination addresses are mandatory. If the destination address is not configured via one of {@link #to(List)},
     * {@link #to(String)} or {@link #toAll()}, {@link InvalidRequestExcpetion} will be thrown in creation time.</p>
     *
     * @param destinationAddress receiver address.
     * @return this builder.
     */
    public SMSMTRequestBuilder to(String destinationAddress) {
        this.destinationAddresses = Collections.singletonList(MsisdnUtils.toValidMsisdn(destinationAddress));
        return this;
    }

    /**
     * <p>Configures destination address to be <b>{@literal tel:all}</b>.
     * This will make the message to be broadcast to all the subscribers</p>
     *
     * <p>Destination addresses are mandatory. If the destination address is not configured via one of {@link #to(List)},
     * {@link #to(String)} or {@link #toAll()}, {@link InvalidRequestExcpetion} will be thrown in creation time.</p>
     *
     * @return this builder
     */
    public SMSMTRequestBuilder toAll() {
        this.destinationAddresses = Collections.singletonList(MsisdnUtils.TEL_ALL);
        return this;
    }

    /**
     * <p>Configures the source address of the request.</p>
     *
     * <p>Source address is not mandatory and if not provided, will be resolved from the configurations.</p>
     *
     * @param sourceAddress senders address of the message
     * @return this builder.
     */
    public SMSMTRequestBuilder from(String sourceAddress) {
        this.sourceAddress = sourceAddress;
        return this;
    }

    /**
     * <p>Configures whether the delivery status is required.</p>
     *
     * @return this builder.
     */
    public SMSMTRequestBuilder withDeliveryStatus() {
        this.deliveryStatusRequest = "1";
        return this;
    }

    /**
     * <p>Configures the message encoding. default is {@code 0}</p>
     *
     * @param messageEncoding encoding of the message.
     * @return this builder.
     * @see MessageEncoding
     */
    public SMSMTRequestBuilder encoding(MessageEncoding messageEncoding) {
        this.encoding = messageEncoding;
        return this;
    }

    /**
     * <p>Configures an optional binary header to the message.</p>
     *
     * @param binaryHeader Binary header content of the message.
     * @return this builder
     */
    public SMSMTRequestBuilder binaryHeader(String binaryHeader) {
        this.binaryHeader = binaryHeader;
        return this;
    }

    /**
     * <p>Creates the request.</p>
     *
     * @return Mobile terminated request.
     * @throws InvalidRequestExcpetion if message / destinationAddresses is null or configuration cannot be found.
     */
    public SMSMTRequest create() {
        if (message == null) throw new InvalidRequestExcpetion("Message should not be null");
        if (destinationAddresses == null || destinationAddresses.isEmpty())
            throw new InvalidRequestExcpetion("At least one destination address should be configured.");

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
        if (this.sourceAddress == null) {
            this.sourceAddress = config.getSender();
        }

        SMSMTRequest smsmtRequest = new SMSMTRequest();
        smsmtRequest.setApplicationId(config.getApplicationId());
        smsmtRequest.setPassword(config.getPassword());
        smsmtRequest.setVersion(config.getVersion());
        smsmtRequest.setMessage(message);
        smsmtRequest.setDestinationAddresses(this.destinationAddresses);
        smsmtRequest.setSourceAddress(this.sourceAddress);
        smsmtRequest.setDelivaryStatusRequest(deliveryStatusRequest);
        smsmtRequest.setEncoding(this.encoding.getValue());
        smsmtRequest.setBinaryHeader(this.binaryHeader);

        return smsmtRequest;
    }

    /**
     * generates json representation of the request.
     *
     * @return json representation of Mobile Terminated Request
     */
    public String asJson() {
        return toJson(create());
    }
}
