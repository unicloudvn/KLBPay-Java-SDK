package testpayment.config;

import org.springframework.beans.factory.annotation.Value;
import vn.unicloud.sdk.payment.KPayClient;
import vn.unicloud.sdk.payment.KPayPacker;
import vn.unicloud.sdk.payment.config.PaymentConfigurationProperties;

public class Config extends PaymentConfigurationProperties {

    @Value("${klb.payment.host}")
    private String host;

    @Value("${klb.payment.client-id}")
    private String clientId;

    @Value("${klb.payment.secret-key}")
    private String secretKey;

    @Value("${klb.payment.encrypt-key}")
    private String encryptKey;

    @Value("${klb.payment.accept-time-diff}")
    private long maxTimestampDiff;

//    public KPayClient Client() {
//        KPayPacker kPayPacker = new KPayPacker(
//                clientId,
//                encryptKey,
//                secretKey,
//                maxTimestampDiff,
//                configuration.getHost());
//
//        return new KPayClient(kPayPacker, host, kPayPacker1, client, kPayPacker2);
//    }

}
