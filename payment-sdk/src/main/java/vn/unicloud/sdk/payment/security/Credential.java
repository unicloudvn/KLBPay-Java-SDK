package vn.unicloud.sdk.payment.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credential {

    private String clientId;
    private String secretKey;
    private String encryptKey;

}
