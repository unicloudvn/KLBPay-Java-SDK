package vn.unicloud.sdk.payment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.unicloud.sdk.payment.client.ThirdPartyClient;
import vn.unicloud.sdk.payment.config.PaymentConfigurationProperties;
import vn.unicloud.sdk.payment.security.PackedMessage;
import vn.unicloud.sdk.payment.transaction.request.CancelTransactionRequest;
import vn.unicloud.sdk.payment.transaction.request.CreateTransactionRequest;
import vn.unicloud.sdk.payment.transaction.request.QueryTransactionRequest;
import vn.unicloud.sdk.payment.transaction.response.CancelTransactionResponse;
import vn.unicloud.sdk.payment.transaction.response.CreateTransactionResponse;
import vn.unicloud.sdk.payment.transaction.response.QueryTransactionResponse;

@Slf4j
@Component
public class KPayClient {

    private static final String CREATE_TRANSACTION_PATH = "/api/payment/v1/create";
    private static final String CANCEL_TRANSACTION_PATH = "/api/payment/v1/cancel";
    private static final String CHECK_TRANSACTION_PATH = "/api/payment/v1/check";
    private final ThirdPartyClient client;
    private final String host;
    private final KPayPacker kPayPacker;

    public KPayClient(PaymentConfigurationProperties configuration, ThirdPartyClient client) {
        kPayPacker = new KPayPacker(
            configuration.getClientId(),
            configuration.getEncryptKey(),
            configuration.getSecretKey(),
            configuration.getAcceptTimeDiff()
        );
        this.host = configuration.getHost();
        this.client = client;
    }

    public <T> T execute(String path, Object request, Class<T> responseType) {
        PackedMessage packedRequest = this.kPayPacker.encode(request);
        PackedMessage packedResponse = client.callAPI(this.host, path, packedRequest);
        return this.kPayPacker.decode(packedResponse, responseType);
    }

    public CreateTransactionResponse createTransaction(CreateTransactionRequest request) {
        return this.execute(CREATE_TRANSACTION_PATH, request, CreateTransactionResponse.class);
    }

    public CancelTransactionResponse cancelTransaction(CancelTransactionRequest request) {
        return this.execute(CANCEL_TRANSACTION_PATH, request, CancelTransactionResponse.class);
    }

    public QueryTransactionResponse checkTransaction(QueryTransactionRequest request) {
        return this.execute(CHECK_TRANSACTION_PATH, request, QueryTransactionResponse.class);
    }

}
