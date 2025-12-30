package com.example.InstallmentSystem.dataprovider.config;

import feign.Client;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;

@Configuration
public class FeignConfig {

    @Bean
    public Client feignClient() throws Exception {
        SSLContext sslContext = new SSLContextBuilder()
                .loadTrustMaterial(null, (certificate, authType) -> true)
                .build();

        return new Client.Default(
                sslContext.getSocketFactory(),
                (hostname, session) -> true
        );
    }

}
