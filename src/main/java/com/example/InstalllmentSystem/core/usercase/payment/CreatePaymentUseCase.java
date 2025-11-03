package com.example.InstalllmentSystem.core.usercase.payment;

import com.example.InstalllmentSystem.core.domain.Payment;
import com.example.InstalllmentSystem.core.domain.enumeration.PaymentMethod;
import com.example.InstalllmentSystem.core.domain.enumeration.PaymentStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Component
public class CreatePaymentUseCase {

    public Payment execute(Payment payment) {
        var payment1 = Payment.builder()
                .id("7yu80fb377szx129")
                .status(PaymentStatus.EXECUTED)
                .paidAt(LocalDateTime.now())
                .amount(payment.getAmount())
                .build();

        System.out.println("Creating payment");
        return payment1;
    }
}
