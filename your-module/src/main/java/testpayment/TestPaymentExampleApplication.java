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
        config.setHost("https://api-staging.kienlongbank.co/pay-mirror");
        config.setClientId("84964d87-9846-4035-9dd6-f997f7933f41");
        config.setSecretKey("KP50qwbWF+rktXv6Gr+QuRHIBmrqWXke+Yuu2tFD/KVc9Xkj9FHeyx8sIWeG+WTU1I/kMrZJz9NbUCgxrYbDLoUD+tTtyybdfR9Vzo3CGrG+lLoiUixiRdez2zD+UL0lpi48HGz+WJ42ilIO+B8ZkUdKYY71bUqik1Sa/ygm9obLKaye/GsQq4/88HIOAkjWwMJH24N+5Kp1KELZx99FHd84FdvILX2sphfT5IEw3vOJxAglqDHXIjN0neRrVdoBKbnBUs8FqzLuFOE0e216eQqHs7pFQI15yauSqI+DAgS2Fx0ZA2Lchn5193NYpuHmpIy8qTGJebm4v9rF9/NsEg==");
        config.setEncryptKey("CA51E201A56FFA44DC3D7C22A00724BD51FFEFD53F57963B1D925E65012BAE94");

//        config.setHost("https://api-staging.kienlongbank.co/pay");
//        config.setClientId("9a739ba0-7804-4281-818b-d7fe8a1253e1");
//        config.setSecretKey("KTg9nG/fhKQcr0B686nzgbK4TIvoxpdvVtzKv+htOh35wN8a4eD+E4zT9FuoWOePqCpV9a46neehCNZigrqMEHkGul+AvWUBDVghVzmzJwlFaablTHDPhTQGz1/TAEDbbKEyuYaOZEeIjtSD3+z/h6ObI28RImF9bDYaWVNetO0RV9UtlWTfyY5S9qxdOJ0p10zmdi0Lrqpw4b7/sV5J0lyxgGVs5qG8YimSuPgQnkK+2jZrZ9w4neyNb0l0Mn25MZMyng8gj2vS7m205gaUXLmGxC45mrUbgjDKRg0a2BsXTADUGjDeZm2G09+skSO+oF7MgjmcIXYZXsAUu8qufw==");
//        config.setEncryptKey("D877B4959ED17A3752482C37F8183BAFA1D796F8D92E593F26369AD7F578039F");

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