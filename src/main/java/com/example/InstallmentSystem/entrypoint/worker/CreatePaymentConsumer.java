package com.example.InstallmentSystem.entrypoint.worker;

import com.example.InstallmentSystem.core.exception.payment.PaymentAmountZeroException;
import com.example.InstallmentSystem.core.usercase.payment.CreatePaymentUseCase;
import com.example.InstallmentSystem.entrypoint.dto.PaymentDTO;
import com.example.InstallmentSystem.entrypoint.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class CreatePaymentConsumer {

    private final PaymentMapper paymentMapper;
    private final CreatePaymentUseCase createPaymentUseCase;

    @Bean
    Consumer<Message<PaymentDTO>> createPaymentEvent() {
        return this::receive;
    }

    public void receive(Message<PaymentDTO> message) {
        try {
            var payment = paymentMapper.toDomain(message.getPayload());
            createPaymentUseCase.execute(payment);
        } catch (PaymentAmountZeroException e) {
            throw new RuntimeException(e);
        }
    }
}
