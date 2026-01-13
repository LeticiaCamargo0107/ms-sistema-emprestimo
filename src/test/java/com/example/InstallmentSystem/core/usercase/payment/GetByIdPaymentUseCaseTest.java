package com.example.InstallmentSystem.core.usercase.payment;

import com.example.InstallmentSystem.core.exception.payment.PaymentNotFoundException;
import com.example.InstallmentSystem.core.gateway.PaymentGateway;
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

@ExtendWith(MockitoExtension.class)
public class GetByIdPaymentUseCaseTest {

    @InjectMocks
    private GetByIdPaymentUseCase underTest;

    @Mock
    private PaymentGateway paymentGateway;


    @ParameterizedTest
    @MethodSource("whenPaymentDoesNotExistByIdThenShouldThrowPaymentNotFoundExceptionProvider")
    @DisplayName("when Payment Does Not Exist By Id Then Should Throw PaymentNotFoundException")
    void whenPaymentDoesNotExistByIdThenShouldThrowPaymentNotFoundException (String id) {
        // Given
        given(paymentGateway.existById(id)).willReturn(false);

        // When
        var result = catchThrowable(() -> underTest.execute(id));

        // Then
        assertThat(result).isInstanceOf(PaymentNotFoundException.class);
    }

    static Object[] whenPaymentDoesNotExistByIdThenShouldThrowPaymentNotFoundExceptionProvider() {
        return new Object[] {
                "vbfdrvgb",
                null
        };
    }


    @Test
    @DisplayName("when Payment Is Valid Then Should Get Payment By Id Successfully")
    void whenPaymentIsValidThenShouldGetPaymentByIdSuccessfully() {
        // Given
        var id = "lalala";

        given(paymentGateway.existById(id)).willReturn(true);

        // When/Then
        assertThatCode(() -> underTest.execute(id)).doesNotThrowAnyException();
    }
}
