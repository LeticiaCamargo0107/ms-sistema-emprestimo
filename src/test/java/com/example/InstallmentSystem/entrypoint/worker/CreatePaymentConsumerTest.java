package com.example.InstallmentSystem.entrypoint.worker;

import com.example.InstallmentSystem.core.domain.Payment;
import com.example.InstallmentSystem.core.exception.payment.PaymentAmountZeroException;
import com.example.InstallmentSystem.core.exception.payment.PaymentMethodNotFoundException;
import com.example.InstallmentSystem.core.usercase.payment.CreatePaymentUseCase;
import com.example.InstallmentSystem.entrypoint.dto.PaymentDTO;
import com.example.InstallmentSystem.entrypoint.mapper.PaymentMapper;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.Message;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class CreatePaymentConsumerTest {

    @InjectMocks
    private CreatePaymentConsumer underTest;

    @Mock
    private PaymentMapper paymentMapper;

    @Mock
    private CreatePaymentUseCase createPaymentUseCase;

    @Mock
    private Message<PaymentDTO> message;

    @Test
    void testReturnCreatePaymentEvent() {
        //Given/When/Then
        assertThatCode(() -> underTest.createPaymentEvent())
                .doesNotThrowAnyException();
    }

    @Test
    void testReturnReceive() throws PaymentMethodNotFoundException, PaymentAmountZeroException {
        //given
        var paymentDTO = Instancio.of(PaymentDTO.class).create();
        var payment = Instancio.of(Payment.class).create();

        given(message.getPayload()).willReturn(paymentDTO);
        given(paymentMapper.toDomain(message.getPayload())).willReturn(payment);
        given(createPaymentUseCase.execute(payment)).willReturn(payment);

        //when/then
        assertThatCode(() -> underTest.receive(message)).doesNotThrowAnyException();

    }

    @Test
    void testReturnThrowReceive() throws PaymentMethodNotFoundException, PaymentAmountZeroException {
        //given
        var paymentDTO = Instancio.create(PaymentDTO.class);
        var payment = Instancio.of(Payment.class)
                .set(Select.field("payMethod"), null)
                .create();

        given(message.getPayload()).willReturn(paymentDTO);
        given(paymentMapper.toDomain(message.getPayload())).willReturn(payment);
        given(createPaymentUseCase.execute(payment)).willThrow(PaymentMethodNotFoundException.class);

        //when/then
        var result = catchThrowable(() -> underTest.receive(message));
        assertThat(result)
                .isInstanceOf(RuntimeException.class);

    }
}
