package com.example.InstallmentSystem.core.usercase.payment;

import com.example.InstallmentSystem.core.domain.Payment;
import com.example.InstallmentSystem.core.gateway.NotifyGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class NotifyPaymentUseCase {

    private final NotifyGateway notifyGateway;

    public Payment execute (Payment payment) {
        payment.setNotify(notifyGateway.createNotify(payment));
        return payment;
    }
}
