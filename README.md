# KLBPay-Java-SDK
## **Ví dụ cơ bản**
config trong file PaymentConfigurationProperties trong SDK Creditial của bạn
```java
@Data
@Configuration
@ConfigurationProperties(prefix = "klb.payment", ignoreUnknownFields = false)
public class PaymentConfigurationProperties {

    @JsonProperty("host")
    private String host = "https://api-staging.kienlongbank.co/pay-mirror";

    @JsonProperty("client-id")
    private String clientId = "84964d87-9846-4035-9dd6-f997f7933f41";

    @JsonProperty("secret-key")
    private String secretKey = "KP50qwbWF+rktXv6Gr+QuRHIBmrqWXke+Yuu2tFD/KVc9Xkj9FHeyx8sIWeG+WTU1I/kMrZJz9NbUCgxrYbDLoUD+tTtyybdfR9Vzo3CGrG+lLoiUixiRdez2zD+UL0lpi48HGz+WJ42ilIO+B8ZkUdKYY71bUqik1Sa/ygm9obLKaye/GsQq4/88HIOAkjWwMJH24N+5Kp1KELZx99FHd84FdvILX2sphfT5IEw3vOJxAglqDHXIjN0neRrVdoBKbnBUs8FqzLuFOE0e216eQqHs7pFQI15yauSqI+DAgS2Fx0ZA2Lchn5193NYpuHmpIy8qTGJebm4v9rF9/NsEg==";

    @JsonProperty("encrypt-key")
    private String encryptKey = "CA51E201A56FFA44DC3D7C22A00724BD51FFEFD53F57963B1D925E65012BAE94";

    @JsonProperty("accept-time-diff")
    private long acceptTimeDiff = 1800; // Second
}
```

### **Tạo giao dịch**:
```java
CreateTransactionResponse response = kPayClient.createTransaction(request);
```


Tạo Controller như sau
```java
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.unicloud.sdk.payment.client.ResponseBase;
import vn.unicloud.sdk.payment.transaction.request.CreateTransactionRequest;

import javax.validation.Valid;

@Tag(name = "Payment Controller", description = "Thao tác với Payment")
@RequestMapping(value = "/api/payment")
public interface IPaymentController {

    /**
     * @param request
     * @return
     */
    @Operation(
            summary = "1. Tạo một giao dịch mới",
            description = "- Tạo một giao dịch mới, trả về QR code"
    )
    @PostMapping("/v1/create")
    String createTransaction(
            @Valid @RequestBody CreateTransactionRequest request

    );
}
```
```java

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
}
```
Chạy thử dự án
http://localhost:8080/swagger-ui/index.html

Giap diện API UI

![img_1.png](images/img_1.png)

Bấm vào phần Tạo giao dịch

![img_2.png](images/img_2.png)
Nhập json dưới đây để test giao dịch

```json
{
  "refTransactionId": "626569",
  "amount": 10000,
  "description": "Noi dung thanh toan",
  "timeout": 10000,
  "title": "Thanh toán hóa đơn tiền điện",
  "language": "VI",
  "customerInfo": {
    "fullName": "NGUYEN VAN XO",
    "email": "xonv@klbpay.vn",
    "phone": "0934998386",
    "address": "22 Lang Ha"
  },
  "successUrl": "https://www.google.com/",
  "failUrl": "https://dictionary.cambridge.org/vi/dictionary/english-vietnamese/fail",
  "redirectAfter": 5,
  "bankAccountId": ""
}
```

Bấm Excute 
Response sẽ trả về URL Thanh toán
![img.png](images/img.png)
Copy URL vào trình duyệt Bấm vào để mở thanh toán 
![img.png](img3.png)
### **Kiểm tra giao dịch** (tương tự):
```java
QueryTransactionResponse response = kPayClient.checkTransaction(request);
```

### **Hủy giao dịch** (tương tự):

```java
CancelTransactionResponse response = kPayClient.cancelTransaction(request);


```
