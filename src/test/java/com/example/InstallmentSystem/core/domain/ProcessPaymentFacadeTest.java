package com.example.InstallmentSystem.core.domain;

import com.example.InstallmentSystem.core.exception.payment.PaymentAmountZeroException;
import com.example.InstallmentSystem.core.exception.payment.PaymentMethodNotFoundException;
import com.example.InstallmentSystem.core.usercase.payment.CreatePaymentUseCase;
import com.example.InstallmentSystem.core.usercase.payment.NotifyPaymentUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ProcessPaymentFacade {

    private final NotifyPaymentUseCase notifyPaymentUseCase;
    private final CreatePaymentUseCase createPaymentUseCase;

    public Payment orchestrator(Payment payment) throws PaymentMethodNotFoundException, PaymentAmountZeroException {
        var notify = notifyPaymentUseCase.execute(payment);
        return createPaymentUseCase.execute(notify);
    }
}
