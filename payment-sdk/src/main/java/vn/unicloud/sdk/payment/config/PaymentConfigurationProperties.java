package vn.unicloud.sdk.payment.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "klb.payment", ignoreUnknownFields = false)
public class PaymentConfigurationProperties {

    @JsonProperty("host")
    private String host = "https://example.com";

    @JsonProperty("client-id")
    private String clientId = "client-id";

    @JsonProperty("secret-key")
    private String secretKey = "6EB00D84532E5006CF86A237DE038D9A";

    @JsonProperty("encrypt-key")
    private String encryptKey = "6EB00D84532E5006CF86A237DE038D9A";

    @JsonProperty("accept-time-diff")
    private long acceptTimeDiff = 1800; // Second
}
