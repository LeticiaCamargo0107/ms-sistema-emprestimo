package com.example.InstallmentSystem.core.usercase.payment;

import com.example.InstallmentSystem.core.domain.Payment;
import com.example.InstallmentSystem.core.domain.PaymentMethodFactory;
import com.example.InstallmentSystem.core.exception.payment.PaymentAmountZeroException;
import com.example.InstallmentSystem.core.exception.payment.PaymentMethodNotFoundException;
import com.example.InstallmentSystem.core.gateway.GenericGateway;
import com.example.InstallmentSystem.core.gateway.PaymentMethodGateway;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CreatePaymentUseCaseTest {

    @InjectMocks
    private CreatePaymentUseCase underTest;

    @Mock
    private GenericGateway<Payment> paymentGateway;

    @Mock
    private PaymentMethodFactory methodFactory;

    @Mock
    private PaymentMethodGateway gateway;

    @Test
    void whenPayMethodIsNullOrDifferentThatEnumThenShouldThrowPaymentMethodNotFoundException() {
        // Given
        var payment = Instancio.of(Payment.class)
                .set(Select.field("payMethod"), null)
                .create();

        // When
        var result = catchThrowable(() -> underTest.execute(payment));

        // Then
        assertThat(result).isInstanceOf(PaymentMethodNotFoundException.class);
    }

    @ParameterizedTest
    @MethodSource("whenAmountIsLessThanOrZeroShouldThrowPaymentAmountZeroExceptionProvider")
    @DisplayName("when Amount Is Less Than Or Zero Should Throw PaymentAmountZeroException")
    void whenAmountIsLessThanOrZeroShouldThrowPaymentAmountZeroException(BigDecimal amount) {
        // Given
        var payment = Instancio.of(Payment.class)
                .set(Select.field("amount"), amount)
                .create();

        // When
        var result = catchThrowable(() -> underTest.execute(payment));

        // Then
        then(paymentGateway).shouldHaveNoInteractions();
        assertThat(result).isInstanceOf(PaymentAmountZeroException.class);
    }

    static Object[] whenAmountIsLessThanOrZeroShouldThrowPaymentAmountZeroExceptionProvider() {
        return new Object[] {
                BigDecimal.ZERO,
                BigDecimal.valueOf(-1),
                null
        };
    }


    @Test
    @DisplayName("when Payment Is Valid Then Should Create Payment Successfully")
    void whenPaymentIsValidThenShouldCreatePaymentSuccessfully() throws PaymentMethodNotFoundException, PaymentAmountZeroException {
        // Given
        var payment = Instancio.of(Payment.class).create();
        given(paymentGateway.save(payment)).willReturn(payment);
        given(methodFactory.supply(payment.getPayMethod())).willReturn(gateway);
        // When
        var result = underTest.execute(payment);

        // Then
        assertThat(result)
                .isNotNull()
                .isEqualTo(payment);
    }
}