# KLBPay-Java-SDK
## **Ví dụ cơ bản**
### **Tạo giao dịch**:
```java
CreateTransactionResponse response = kPayClient.createTransaction(request);
```


Tạo MainClass như sau
```java
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
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
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
        request.setBankAccountId("");
        PaymentConfigurationProperties config = new PaymentConfigurationProperties();
        config.setHost("https://api-staging.kienlongbank.co/pay-mirror");
        config.setClientId("84964d87-9846-4035-9dd6-f997f7933f41");
        config.setSecretKey("KP50qwbWF+rktXv6Gr+QuRHIBmrqWXke+Yuu2tFD/KVc9Xkj9FHeyx8sIWeG+WTU1I/kMrZJz9NbUCgxrYbDLoUD+tTtyybdfR9Vzo3CGrG+lLoiUixiRdez2zD+UL0lpi48HGz+WJ42ilIO+B8ZkUdKYY71bUqik1Sa/ygm9obLKaye/GsQq4/88HIOAkjWwMJH24N+5Kp1KELZx99FHd84FdvILX2sphfT5IEw3vOJxAglqDHXIjN0neRrVdoBKbnBUs8FqzLuFOE0e216eQqHs7pFQI15yauSqI+DAgS2Fx0ZA2Lchn5193NYpuHmpIy8qTGJebm4v9rF9/NsEg==");
        config.setEncryptKey("CA51E201A56FFA44DC3D7C22A00724BD51FFEFD53F57963B1D925E65012BAE94");
        config.setAcceptTimeDiff(2000);
        RestTemplate restTemplate = new RestTemplate();
        ThirdPartyClient client = new ThirdPartyClient(restTemplate);
        KPayClient kPayClient = new KPayClient(config, client);
        CreateTransactionResponse response = kPayClient.createTransaction(request);
        System.out.println(response.getUrl());
    }

}
```

Chạy thử dự án
Response sẽ trả về URL Thanh toán
Copy URL vào trình duyệt Bấm vào để mở thanh toán 

### **Kiểm tra giao dịch** (tương tự):
```java
QueryTransactionResponse response = kPayClient.checkTransaction(request);
```

### **Hủy giao dịch** (tương tự):

```java
CancelTransactionResponse response = kPayClient.cancelTransaction(request);


```
