package vn.unicloud.sdk.payment.client;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.unicloud.sdk.payment.exception.PayResponseCode;
import vn.unicloud.sdk.payment.exception.PaymentException;
import vn.unicloud.sdk.payment.security.HeaderBase;
import vn.unicloud.sdk.payment.security.PackedMessage;

import java.util.Objects;

@Component
@Log4j2
@RequiredArgsConstructor
public class RestTemplateConfig {

    @Bean("payRestTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}