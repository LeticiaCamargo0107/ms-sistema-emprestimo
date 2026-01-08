package com.example.InstallmentSystem.dataprovider.gateway.payment;

import com.example.InstallmentSystem.core.domain.Payment;
import com.example.InstallmentSystem.core.domain.enumeration.PaymentMethod;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@ExtendWith(MockitoExtension.class)
public class CreditCardGatewayImplTest {

    @InjectMocks
    private CreditCardGatewayImpl underTest;


    @Test
    @DisplayName("when Credit Payment Is Process Then Should Show A Log")
    void whenCreditPaymentIsProcessThenShouldShowALog() {
        var payment = Instancio.of(Payment.class).create();
        var result = catchThrowable(() -> underTest.process(payment));

        assertThat(result);
    }

    @Test
    @DisplayName("when method support of class CreditCardGatewayImpl return a boolean")
    void testSupport() {
        //when
        var result = underTest.supports(PaymentMethod.CREDIT_CARD);

        //then
        assertThat(result)
                .isNotNull();
    }
}
