package com.example.InstallmentSystem.core.usercase.payment;

import com.example.InstallmentSystem.core.domain.Payment;
import com.example.InstallmentSystem.core.exception.payment.PaymentNotFoundException;
import com.example.InstallmentSystem.core.gateway.GenericGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class DeletePaymentUseCaseTest {

    @InjectMocks
    private DeleteByIdPaymentUseCase underTest;

    @Mock
    private GenericGateway<Payment> paymentGateway;


    @ParameterizedTest
    @MethodSource("whenPaymentDoesNotExistByIdThenShouldThrowPaymentNotFoundExceptionProvider")
    @DisplayName("when payment Does Not Exist By Id Then Should Throw PaymentNotFoundException")
    void whenPaymentDoesNotExistByIdThenShouldThrowPaymentNotFoundException (String id) {
        // Given
        given(paymentGateway.existById(id)).willReturn(false);

        // When
        var result = catchThrowable(() -> underTest.execute(id));
        // Then
        then(paymentGateway).should().existById(id);
        assertThat(result).isInstanceOf(PaymentNotFoundException.class);
    }

    static Object[] whenPaymentDoesNotExistByIdThenShouldThrowPaymentNotFoundExceptionProvider() {
        return new Object[] {
                "vbfdrvgb",
                null
        };
    }


    @Test
    @DisplayName("when Payment Is Valid Then Should Delete Payment Successfully")
    void whenPaymentIsValidThenShouldDeletePaymentSuccessfully() {
        // Given
        var id = "lalala";
        given(paymentGateway.existById(id)).willReturn(true);

        // When/Then
        assertThatCode(() -> underTest.execute(id)).doesNotThrowAnyException();
    }


}
