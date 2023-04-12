package vn.unicloud.sdk.payment.client;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.unicloud.sdk.payment.exception.PayResponseCode;
import vn.unicloud.sdk.payment.exception.PaymentException;
import vn.unicloud.sdk.payment.security.HeaderBase;
import vn.unicloud.sdk.payment.security.PackedMessage;

import java.util.Objects;

@Component
@Log4j2
public class ThirdPartyClient {

    private final RestTemplate restTemplate;

    public ThirdPartyClient(@Qualifier("payRestTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private <T> ResponseEntity<T> execute(String url, HttpMethod httpMethod, HttpHeaders httpHeaders, Object request, Class<T> responseClassType) {
        try {
            HttpEntity<Object> httpEntity = new HttpEntity<>(request, httpHeaders);

            log.debug("Calling EXTERNAL {} {}", httpMethod, url);

            return restTemplate.exchange(url, httpMethod, httpEntity, responseClassType);

        } catch (Exception e) {
            log.error("Call api error: {}", e.getMessage());
            throw new PaymentException(PayResponseCode.PAYMENT_SERVICE_UNAVAILABLE);
        }
    }

    public PackedMessage callAPI(String host, String path, PackedMessage packedMessage) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.set(HeaderBase.CLIENT, packedMessage.getClientId());
        headers.set(HeaderBase.SIGNATURE, packedMessage.getSignature());
        headers.set(HeaderBase.TIMESTAMP, String.valueOf(packedMessage.getTimestamp()));

        EncryptedBodyRequest request = new EncryptedBodyRequest(packedMessage.getEncryptedData());

        ResponseEntity<ResponseBase> response = this.execute(
            String.format("%s%s", host, path),
            HttpMethod.POST,
            headers,
            request,
            ResponseBase.class
        );
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new PaymentException(PayResponseCode.PAYMENT_TRANSACTION_FAILED);
        }
        ResponseBase<?> responseBase = response.getBody();
        if (responseBase == null) {
            throw new PaymentException(PayResponseCode.PAYMENT_TRANSACTION_FAILED);
        }
        log.debug("response : {}", responseBase);
        PayResponseCode responseCode = PayResponseCode.valueOf(responseBase.getCode());
        if (responseCode == null) {
            throw new PaymentException(PayResponseCode.PAYMENT_TRANSACTION_FAILED);
        }
        if (!responseCode.equals(PayResponseCode.SUCCESS)) {
            throw new PaymentException(responseCode);
        }
        String encryptResponse = (String) responseBase.getData();
        if (encryptResponse == null) {
            throw new PaymentException(PayResponseCode.PAYMENT_TRANSACTION_FAILED);
        }
        HttpHeaders responseHeader = response.getHeaders();
        if (!responseHeader.containsKey(HeaderBase.CLIENT) ||
            !responseHeader.containsKey(HeaderBase.TIMESTAMP) ||
            !responseHeader.containsKey(HeaderBase.SIGNATURE)) {
            throw new PaymentException(PayResponseCode.PAYMENT_SECURITY_VIOLATION);
        }
        return new PackedMessage(
            responseHeader.getFirst(HeaderBase.CLIENT),
            Long.parseLong(Objects.requireNonNull(responseHeader.getFirst(HeaderBase.TIMESTAMP))),
            responseHeader.getFirst(HeaderBase.SIGNATURE),
            encryptResponse
        );
    }

}