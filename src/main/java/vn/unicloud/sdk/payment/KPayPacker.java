package vn.unicloud.sdk.payment;

import com.google.gson.Gson;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import vn.unicloud.sdk.payment.exception.PayResponseCode;
import vn.unicloud.sdk.payment.exception.PaymentException;
import vn.unicloud.sdk.payment.security.PackedMessage;
import vn.unicloud.sdk.payment.security.SecurityUtils;

@Slf4j
public class KPayPacker {

    private final String clientId;
    private final String encryptKey;
    private final String secretKey;
    private final long maxTimestampDiff;
    private final Gson gson;

    public KPayPacker(String clientId, String encryptKey, String secretKey, long maxTimestampDiff) {
        this.clientId = clientId;
        this.encryptKey = encryptKey;
        this.secretKey = secretKey;
        this.maxTimestampDiff = maxTimestampDiff;
        this.gson = new Gson();
    }

    @SneakyThrows
    public <T> T decode(
            PackedMessage packedMessage,
            Class<T> tClass) {
        if (packedMessage.getClientId() == null || !packedMessage.getClientId().equals(this.clientId)) {
            throw new PaymentException(PayResponseCode.PAYMENT_CLIENT_ID_INVALID);
        }
        // check timestamp
        long checkTime = System.currentTimeMillis() - packedMessage.getTimestamp();
        if (System.currentTimeMillis() - packedMessage.getTimestamp() > maxTimestampDiff) {
            log.error("expire payment: {}, threshold: {}", checkTime, maxTimestampDiff);
            throw new PaymentException(PayResponseCode.PAYMENT_TRANSACTION_EXPIRED);
        }
        // check signature
        try {
            String signature = SecurityUtils.hmacEncode(
                    SecurityUtils.buildRawSignature(clientId, String.valueOf(packedMessage.getTimestamp()), packedMessage.getEncryptedData()),
                    secretKey
            );
            if (signature != null && signature.equals(packedMessage.getSignature())) {
                // Decrypt Data
                String decryptBodyString = SecurityUtils.decryptAES(packedMessage.getEncryptedData(), encryptKey);
                return gson.fromJson(decryptBodyString, tClass);
            }
        } catch (Exception e) {
            log.error("authenticate error: {}", e.getMessage());
        }
        throw new PaymentException(PayResponseCode.PAYMENT_SECURITY_VIOLATION);
    }

    @SneakyThrows
    public PackedMessage encode(Object data) {
        long timestamp = System.currentTimeMillis();
        // encrypt data body
        if (data == null) {
            throw new PaymentException(PayResponseCode.PAYMENT_TRANSACTION_FAILED);
        }
        log.debug("Data body: {}", data);
        String jsonString;
        try {
            jsonString = gson.toJson(data);
        } catch (Exception e) {
            log.error("Parse data error: {}", e.getMessage());
            throw new PaymentException(PayResponseCode.PAYMENT_TRANSACTION_FAILED);
        }
        String encryptData = SecurityUtils.encryptAES(jsonString, encryptKey);

        //encrypt header validation
        String xApiValidate = SecurityUtils.hmacEncode(
                SecurityUtils.buildRawSignature(clientId, String.valueOf(timestamp), encryptData),
                secretKey
        );

        return new PackedMessage(clientId, timestamp, xApiValidate, encryptData);
    }

}
