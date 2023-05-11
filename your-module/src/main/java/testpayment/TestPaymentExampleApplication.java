package testpayment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import vn.unicloud.sdk.payment.KPayClient;
import vn.unicloud.sdk.payment.client.ThirdPartyClient;
import vn.unicloud.sdk.payment.config.PaymentConfigurationProperties;
import vn.unicloud.sdk.payment.transaction.model.CustomerInfo;
import vn.unicloud.sdk.payment.transaction.request.CreateTransactionRequest;
import vn.unicloud.sdk.payment.transaction.response.CreateTransactionResponse;

@SpringBootApplication
public class TestPaymentExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestPaymentExampleApplication.class, args);
        CreateTransactionRequest request = new CreateTransactionRequest();

        request.setRefTransactionId("212423235");
        request.setAmount(100000L);
        request.setDescription("tratien");
        request.setTimeout(100000L);
        request.setTitle("Tra Tien");
        request.setLanguage("VI");
        CustomerInfo customerInfo = new CustomerInfo("Hoang Khanh Son", "Son@gmail.com", "0971186216", "Binh Thanh");
        request.setCustomerInfo(customerInfo);
        request.setSuccessUrl("https://ks.tiktzuki.com/success.php");
        request.setFailUrl("https://ks.tiktzuki.com/fail.php");
        request.setRedirectAfter(5);
        request.setBankAccountNo("");

        PaymentConfigurationProperties config = new PaymentConfigurationProperties();

        //Cấu Hình Credential
        config.setHost("https://api-staging.kienlongbank.co/pay");
        config.setClientId("542812e7-bf7a-4f1c-bb8b-84f5dabbd43f");
        config.setSecretKey("boF/TDEKYKWnw9A09BvCLyKwdRQWFQfwB6b/aT66vNI0d7CyTK4b6O+Lw+7ar2y7pp11VNN5IwciGAsu/Z8webeh8bznsq+rCmjgAAB3MQlT6+5YD5mh1qYrSqTLzIMyGPPVJxN69eLHFP21NYFePrN1aM6JlLCUbjKO8uXEdTS8lOcDCZST44pCiRy5KKTCLtb/EIXgXtWL+jqgYEYFeLEx6MFklXNmpFePNwPlPANM5YSWsC8lWPxe6nHbP9FnNLbY6Sb8y+He93modWRlclAyXePKrmyZlDztZshNbpKHciWsrIei5lFlFhmsjx9NefdhVFz27+dVMeMRzck25A==");
        config.setEncryptKey("929B573F2E8C948F814BFD031B6817DD4D3CD4EF0AE17375F8B2EAD7EF4447D3");

        config.setAcceptTimeDiff(2000);

        RestTemplate restTemplate = new RestTemplate();
        ThirdPartyClient client = new ThirdPartyClient(restTemplate);
        KPayClient kPayClient = new KPayClient(config, client);
        //Khởi tạo Thanh Toán
        CreateTransactionResponse response = kPayClient.createTransaction(request);

        //In ra URL thanh toán
        System.out.println(response.getUrl());
    }

}
