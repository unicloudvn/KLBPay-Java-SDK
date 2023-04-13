package testpayment.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import vn.unicloud.sdk.payment.KPayClient;
import vn.unicloud.sdk.payment.client.ResponseBase;
import vn.unicloud.sdk.payment.client.ThirdPartyClient;
import vn.unicloud.sdk.payment.config.PaymentConfigurationProperties;
import vn.unicloud.sdk.payment.transaction.request.CreateTransactionRequest;
import vn.unicloud.sdk.payment.transaction.response.CreateTransactionResponse;


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

        return response.getUrl();
    }


    @Override
    public ResponseEntity<ResponseBase<String>> checkTransaction(CreateTransactionRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseBase<String>> cancelTransaction(CreateTransactionRequest request) {
        return null;
    }
}
