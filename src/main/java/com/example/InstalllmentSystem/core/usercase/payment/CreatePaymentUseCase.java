package com.example.InstalllmentSystem.core.usercase.payment;

import com.example.InstalllmentSystem.core.domain.Payment;
import com.example.InstalllmentSystem.core.domain.enumeration.PaymentStatus;
import com.example.InstalllmentSystem.core.exception.payment.PaymentAmountZeroException;
import com.example.InstalllmentSystem.core.gateway.PaymentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class CreatePaymentUseCase {

    private final PaymentGateway paymentGateway;

    public Payment execute(Payment payment) throws PaymentAmountZeroException {

        if (payment.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new PaymentAmountZeroException();
        }

        payment.setStatus(PaymentStatus.EXECUTED);
        payment.setPaidAt(LocalDateTime.now());

        System.out.println("Creating payment");
        return paymentGateway.save(payment);
    }
}
