# KLBPay-Java-SDK

Thư viện người dùng tích hợp KLBPay vào hệ thống thanh toán của Merchant

# Java

Github: [https://github.com/unicloudvn/KLBPay-Java-SDK.git](https://github.com/unicloudvn/KLBPay-Java-SDK.git)

## **Cài đặt và sử dụng**

### **Requirements**

Phiên bản Spring Boot Java: từ 2.7.4+

JDK 11+

Gradle 7.2+

## **Cách sử dụng:**

### **Imports**
Thêm dòng dưới mavenCentral() để sử dụng GitHubs SDK packages
```java
    repositories{
        mavenCentral()
        maven{
        name="GitHubPackages"
        url=uri("https://maven.pkg.github.com/unicloudvn/KLBPay-Java-SDK")
            credentials{
                username=project.findProperty("user")?:System.getenv("USERNAME")
                password=project.findProperty("token")?:System.getenv("TOKEN")
            }
        }
    }
```
Thêm file gradle.properties vào project của bạn với nội dung

```java
    user=hoangkhanhson2000 
    token=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
```
implementation để sử dụng thư viện SDK
```java
dependencies{
        implementation'vn.unicloud:payment-sdk:1.0.3'
}
```
Sau đó kiểm tra External Libraies đã được cài đặt

![img.png](images/img.png)

### **Constants**

```java
    @JsonProperty("host")
    private String host = "https://example.com";

    @JsonProperty("client-id")
    private String clientId = '<HOST_URL>'; //'https://api-staging.kienlongbank.co/pay'

    @JsonProperty("secret-key") 
    private String secretKey = '<YOUR_CLIENT_ID>';

    @JsonProperty("encrypt-key")
    private String encryptKey = '<YOUR_SECRET_KEY>';

    @JsonProperty("accept-time-diff")
    private long acceptTimeDiff = '<YOUR_ACCEPT_TIME_DIFF>';
```

## **Author**

[dev@unicloud.com.vn]()
