package com.example.InstallmentSystem.dataprovider.gateway.payment;

import com.example.InstallmentSystem.core.domain.Payment;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class EmailNotifyGatewayImplTest {

    @InjectMocks
    private EmailNotifyGatewayImpl underTest;

    @Test
    void testCreateNotify() {
        //given
        var payment = Instancio.of(Payment.class).create();
        //when
        var result = underTest.createNotify(payment);
        //then
        assertThat(result)
                .isNotNull();
    }
}
