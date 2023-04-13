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
    private String host = "https://api-staging.kienlongbank.co/pay-mirror";

    @JsonProperty("client-id")
    private String clientId = "84964d87-9846-4035-9dd6-f997f7933f41";

    @JsonProperty("secret-key")
    private String secretKey = "KP50qwbWF+rktXv6Gr+QuRHIBmrqWXke+Yuu2tFD/KVc9Xkj9FHeyx8sIWeG+WTU1I/kMrZJz9NbUCgxrYbDLoUD+tTtyybdfR9Vzo3CGrG+lLoiUixiRdez2zD+UL0lpi48HGz+WJ42ilIO+B8ZkUdKYY71bUqik1Sa/ygm9obLKaye/GsQq4/88HIOAkjWwMJH24N+5Kp1KELZx99FHd84FdvILX2sphfT5IEw3vOJxAglqDHXIjN0neRrVdoBKbnBUs8FqzLuFOE0e216eQqHs7pFQI15yauSqI+DAgS2Fx0ZA2Lchn5193NYpuHmpIy8qTGJebm4v9rF9/NsEg==";

    @JsonProperty("encrypt-key")
    private String encryptKey = "CA51E201A56FFA44DC3D7C22A00724BD51FFEFD53F57963B1D925E65012BAE94";

    @JsonProperty("accept-time-diff")
    private long acceptTimeDiff = 1800; // Second
}
