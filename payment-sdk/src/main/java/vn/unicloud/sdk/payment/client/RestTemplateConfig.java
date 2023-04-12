package vn.unicloud.sdk.payment.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Log4j2
@RequiredArgsConstructor
public class RestTemplateConfig {

    @Bean("payRestTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}