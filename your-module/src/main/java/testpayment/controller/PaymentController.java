package testpayment.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import vn.unicloud.sdk.payment.KPayClient;
import vn.unicloud.sdk.payment.client.ThirdPartyClient;
import vn.unicloud.sdk.payment.config.PaymentConfigurationProperties;
import vn.unicloud.sdk.payment.transaction.request.CancelTransactionRequest;
import vn.unicloud.sdk.payment.transaction.request.CreateTransactionRequest;
import vn.unicloud.sdk.payment.transaction.request.QueryTransactionRequest;
import vn.unicloud.sdk.payment.transaction.response.CancelTransactionResponse;
import vn.unicloud.sdk.payment.transaction.response.CreateTransactionResponse;
import vn.unicloud.sdk.payment.transaction.response.QueryTransactionResponse;


@RestController
@Slf4j
@RequiredArgsConstructor
public class PaymentController implements IPaymentController {


    RestTemplate restTemplate = new RestTemplate();
    ThirdPartyClient client = new ThirdPartyClient(restTemplate);
    PaymentConfigurationProperties properties = new PaymentConfigurationProperties();


    KPayClient kPayClient = new KPayClient(properties, client);


    @Override
    public String createTransaction(CreateTransactionRequest request) {

        CreateTransactionResponse response = kPayClient.createTransaction(request);

        return " URL: " + response.getUrl() + " TransactionId: " + response.getTransactionId();
    }

    @Override
    public String checkTransaction(QueryTransactionRequest request) {
        QueryTransactionResponse response = kPayClient.checkTransaction(request);

        return " Status: " + response.getStatus() + " RefId: " + response.getRefTransactionId() + " Amount: " + response.getAmount();
    }

    @Override
    public String cancelTransaction(CancelTransactionRequest request) {
        CancelTransactionResponse response = kPayClient.cancelTransaction(request);

        return " Cancel Transaction: "+response.isSuccess();
    }


}
