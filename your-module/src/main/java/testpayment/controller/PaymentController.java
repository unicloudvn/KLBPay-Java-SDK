package testpayment.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import vn.unicloud.sdk.payment.KPayClient;
import vn.unicloud.sdk.payment.KPayPacker;
import vn.unicloud.sdk.payment.client.ResponseBase;
import vn.unicloud.sdk.payment.config.PaymentConfigurationProperties;
import vn.unicloud.sdk.payment.transaction.request.CreateTransactionRequest;
import vn.unicloud.sdk.payment.transaction.response.CreateTransactionResponse;


@RestController
@Slf4j
@RequiredArgsConstructor
public class PaymentController implements IPaymentController {


    KPayClient kPayClient;


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
