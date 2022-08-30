package com.meesho.notificationservice;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import static com.meesho.notificationservice.constants.Constants.READ_TIMEOUT;
import static com.meesho.notificationservice.constants.Constants.CONNECT_TIMEOUT;

@SpringBootApplication
public class NotificationServiceApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setReadTimeout(READ_TIMEOUT);
        httpRequestFactory.setConnectTimeout(CONNECT_TIMEOUT);
        LOGGER.info("Rest Template has been initialised!");
        return new RestTemplate(httpRequestFactory);
    }
}
