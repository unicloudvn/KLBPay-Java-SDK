# KLBPay-Java-SDK

Thư viện người dùng tích hợp KLBPay vào hệ thống thanh toán của Merchant

# Java

Github: [https://github.com/unicloudvn/KLBPay-Java-SDK.git](https://github.com/unicloudvn/KLBPay-Java-SDK.git)

## **Cài đặt và sử dụng**

### **Requirements**

Spring Boot Java 2.7.4+

JDK 11+

Gradle 7.2+

## **Cách sử dụng:**
### **Cài đặt import:**
Bước 1. Thêm phần này ở file build.gradle
```
repositories {
    mavenCentral()
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/unicloudvn/KLBPay-Java-SDK")
        credentials {
            username = project.findProperty("user") ?: System.getenv("USERNAME")
            password = project.findProperty("token") ?: System.getenv("TOKEN")
        }
    }
}
```
Bước 2. Tạo file gradle.properties có nội dung như sau
```
user=hoangkhanhson2000 
token=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
```
Bước 3. Thêm dependencies và load lại gradle
```
dependencies {
    implementation'vn.unicloud:payment-sdk:1.0.3'
}
```
Như vậy đã sử dụng được SDK rồi
### **Cài đặt thủ công:**

Bước 1. Tải thư mục src của sdk vào dự án dưới dạng module

![images/img1.png](images/img1.png)   

Bước 2. Cấu hình module payment-sdk trong file build.gradle
```build.gradle
        plugins {
            id 'org.springframework.boot' version "${springBootVersion}"
            id 'io.spring.dependency-management' version "${dependencyManagementVersion}"
            id 'java'
        }

        group = 'vn.unicloud'
        version = '0.0.1-SNAPSHOT'
        sourceCompatibility = '11'
        
        repositories {
            mavenCentral()
        }

        dependencies {
            implementation 'org.springframework.boot:spring-boot-starter-web'
            implementation 'org.springframework.boot:spring-boot-starter'
            compileOnly 'org.projectlombok:lombok'
            annotationProcessor 'org.projectlombok:lombok'
            implementation 'commons-codec:commons-codec:1.15'
            implementation 'com.google.code.gson:gson:2.10'
        }
        bootJar {
            enabled = false
        }
```
Bước 3: Cấu hình trong module của bạn trong build.gradle
```build.gradle

    plugins {
        id 'org.springframework.boot' version "${springBootVersion}"
        id 'io.spring.dependency-management' version "${dependencyManagementVersion}"
        id 'java'
}

    group = 'your-group'
    version = 'your-version'

    repositories {
    mavenCentral()
}
    bootJar {
        enabled = false
}
    dependencies {
        //implement module
        implementation project(':payment-sdk')
}
```
Bước 4: trong thư mục root của bạn ta cấu hình file setting.gradle
```setting.gradle
rootProject.name = 'firstdemo'
include 'payment-sdk'
include 'your-module'
```
và thư mục build.gradle

```build.gradle
subprojects {
    ext {
        springBootVersion = '2.7.4'
        dependencyManagementVersion = '1.1.0'
    }
}
```

Như vậy bạn đã có thể sử dụng thư viện SDK một cách thủ công rồi


Chạy Gradle Build

Sau đó kiểm tra External Libraies đã được cài đặt

![img.png](images/imageimport.png)

### **Constants**

```java

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "klb.payment", ignoreUnknownFields = false)
public class PaymentConfigurationProperties {

    @JsonProperty("host")
    private String host = "https://example.com"; 

    @JsonProperty("client-id")
    private String clientId = '<YOUR_CLIENT_ID>'; 

    @JsonProperty("secret-key") 
    private String secretKey = '<YOUR_SECRET_KEY>';

    @JsonProperty("encrypt-key")
    private String encryptKey = '<YOUR_ENCRYPT_KEY>';

    @JsonProperty("accept-time-diff")
    private long acceptTimeDiff = '<YOUR_ACCEPT_TIME_DIFF>';
}
```
## **Ví dụ cơ bản**
Bạn có thể xem demo ở đây: [https://github.com/unicloudvn/KLBPay-Java-SDK/tree/demo](https://github.com/unicloudvn/KLBPay-Java-SDK/tree/demo)

## **Author**

[dev@unicloud.com.vn]()
