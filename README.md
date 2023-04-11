# KLBPay-Java-SDK

Thư viện người dùng tích hợp KLBPay vào hệ thống thanh toán của Merchant

# Java

Github: [https://github.com/unicloudvn/KLBPay-Java-SDK.git](https://github.com/unicloudvn/KLBPay-Java-SDK.git)

## **Cài đặt và sử dụng**

### **Requirements**

Phiên bản Spring Boot Java: từ 2.7.4 trở lên.
JDK 11
Gradle 7.2
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
private String HOST = '<HOST_URL>'; //'https://api-staging.kienlongbank.co/pay'
private String CLIENT_ID = '<YOUR_CLIENT_ID>';
private String SECRET_KEY = '<YOUR_SECRET_KEY>';
private String ENCRYPT_KEY = '<YOUR_ENCRYPT_KEY>';
private String ACCEPT_TIME_DIFF = '<YOUR_ACCEPT_TIME_DIFF>';
```

### **Configure**

```java

```


### **Callback thanh toán** (Webhook):
## **Author**

[dev@unicloud.com.vn]()
