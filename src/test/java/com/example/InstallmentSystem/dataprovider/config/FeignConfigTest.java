package com.example.InstallmentSystem.dataprovider.config;

import feign.Client;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.net.ssl.SSLContext;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class FeignConfigTest {

    @InjectMocks
    private FeignConfig underTest;

    @Test
    void testReturnFeignClient() throws Exception {
        var sslContext = Instancio.create(SSLContext.class);
        var result = underTest.feignClient();

        assertThat(result)
                .isNotNull()
                .isEqualTo(new Client.Default(sslContext.getSocketFactory(),
                        (hostname, session) -> true
                ));
    }
}
