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
import static org.assertj.core.api.Assertions.assertThatCode;

@ExtendWith(MockitoExtension.class)
public class SlipGatewayImplTest {

    @InjectMocks
    private SlipGatewayImpl underTest;

    @Test
    @DisplayName("when Slip Payment Is Process Then Should Show A Log")
    void whenSlipPaymentIsProcessThenShouldShowALog() {
        //Given
        var payment = Instancio.of(Payment.class).create();

        //When/Then
        assertThatCode(() -> underTest.process(payment)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("when method support of class SlipGatewayImpl return true, should support successfully")
    void testSupport() {
        //Given/When
        var value = true;
        var result = underTest.supports(PaymentMethod.SLIP);

        //then
        assertThat(result)
                .isNotNull()
                .isEqualTo(value);
    }

    @Test
    @DisplayName("when method support of class SlipGatewayImpl return false, should not support successfully")
    void testSupportReturnFalse() {
        //Given/When
        var value = false;
        var result = underTest.supports(PaymentMethod.CREDIT_CARD);

        //then
        assertThat(result)
                .isNotNull()
                .isEqualTo(value);
    }
}
