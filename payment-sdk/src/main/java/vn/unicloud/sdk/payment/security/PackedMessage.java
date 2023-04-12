package vn.unicloud.sdk.payment.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PackedMessage {

    private String clientId;
    private Long timestamp;
    private String signature;
    private String encryptedData;

}
