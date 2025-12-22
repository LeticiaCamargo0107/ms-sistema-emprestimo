package com.example.InstallmentSystem.core.usercase.payment;

import com.example.InstallmentSystem.core.domain.Payment;
import com.example.InstallmentSystem.core.domain.enumeration.PaymentStatus;
import com.example.InstallmentSystem.core.exception.payment.PaymentAmountZeroException;
import com.example.InstallmentSystem.core.gateway.GenericGateway;
import com.example.InstallmentSystem.core.gateway.PaymentGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Component
public class CreatePaymentUseCase {

    private final PaymentGateway payment;
    private final GenericGateway<Payment> paymentGateway;

    public Payment execute(Payment payment) throws PaymentAmountZeroException {

        if (payment.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            log.error("Amount must be greater than zero");
            throw new PaymentAmountZeroException();
        }
        payment.setStatus(PaymentStatus.EXECUTED);
        payment.setPaidAt(LocalDateTime.now());

        return paymentGateway.save(payment);
    }
}
