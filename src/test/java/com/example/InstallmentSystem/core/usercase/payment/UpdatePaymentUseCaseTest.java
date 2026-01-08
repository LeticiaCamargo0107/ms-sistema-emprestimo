package com.example.InstallmentSystem.core.usercase.payment;

import com.example.InstallmentSystem.core.domain.Payment;
import com.example.InstallmentSystem.core.exception.payment.PaymentAmountZeroException;
import com.example.InstallmentSystem.core.exception.payment.PaymentNotFoundException;
import com.example.InstallmentSystem.core.gateway.GenericGateway;
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
public class UpdatePaymentUseCaseTest {

    @InjectMocks
    private UpdatePaymentUseCase underTest;

    @Mock
    private GetByIdPaymentUseCase getByIdPaymentUseCase;

    @Mock
    private GenericGateway<Payment> paymentGateway;

    @ParameterizedTest
    @MethodSource("WhenAmountIsLessThanOrZeroThenShouldThrowPaymentAmountZeroExceptionProvider")
    @DisplayName("When Amount Is Less Than Or Zero Then Should Throw PaymentAmountZeroException")
    void WhenAmountIsLessThanOrZeroThenShouldThrowPaymentAmountZeroException(BigDecimal amount) {
        //Given
        var id = "lalala";
        var payment = Instancio.of(Payment.class)
                .set(Select.field("amount"), amount)
                .create();

        // When
        var result = catchThrowable(() -> underTest.execute(id, payment));

        // Then
        assertThat(result).isInstanceOf(PaymentAmountZeroException.class);
    }

    static Object[] WhenAmountIsLessThanOrZeroThenShouldThrowPaymentAmountZeroExceptionProvider() {
        return new Object[] {
          BigDecimal.valueOf(-1),
          BigDecimal.ZERO,
          null
        };
    }


    @Test
    @DisplayName("When Payment Is Valid Should Update Payment Successfully")
    void WhenPaymentIsValidShouldUpdateContractSuccessfully() throws PaymentNotFoundException, PaymentAmountZeroException {
        // Given
        var id = "lalala";
        var payment = Instancio.create(Payment.class);

        given(paymentGateway.save(payment)).willReturn(payment);
        given(getByIdPaymentUseCase.execute(id)).willReturn(payment);

        // When
        var result = underTest.execute(id, payment);

        // Then
        then(paymentGateway).should().save(payment);

        assertThat(result)
                .isNotNull()
                .isEqualTo(payment);
    }
}
