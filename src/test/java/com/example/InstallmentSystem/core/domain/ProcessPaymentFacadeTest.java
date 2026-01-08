package com.example.InstallmentSystem.core.domain;

import com.example.InstallmentSystem.core.exception.payment.PaymentAmountZeroException;
import com.example.InstallmentSystem.core.exception.payment.PaymentMethodNotFoundException;
import com.example.InstallmentSystem.core.usercase.payment.CreatePaymentUseCase;
import com.example.InstallmentSystem.core.usercase.payment.NotifyPaymentUseCase;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ProcessPaymentFacadeTest {

    @InjectMocks
    private ProcessPaymentFacade underTest;

    @Mock
    private CreatePaymentUseCase createPaymentUseCase;

    @Mock
    private NotifyPaymentUseCase notifyPaymentUseCase;

    @Test
    @DisplayName("When method process of class PaymentFacadeProcess is valid, then should create a Payment successfully")
    void whenPaymentFacadeProcessIsValidThenShouldCreateAPaymentSuccessfully() throws PaymentMethodNotFoundException, PaymentAmountZeroException {
        //given
        var payment = Instancio.of(Payment.class).create();
        given(notifyPaymentUseCase.execute(payment)).willReturn(payment);
        given(createPaymentUseCase.execute(payment)).willReturn(payment);

        //when/then
        assertThatCode(() -> underTest.orchestrator(payment)).doesNotThrowAnyException();
    }
}
