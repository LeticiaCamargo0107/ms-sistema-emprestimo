package com.example.InstalllmentSystem.core.usercase.payment;

import com.example.InstalllmentSystem.core.domain.Payment;
import com.example.InstalllmentSystem.core.domain.enumeration.PaymentMethod;
import com.example.InstalllmentSystem.core.domain.enumeration.PaymentStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Component
public class CreatePaymentUseCase {

    public Payment execute(String id, BigDecimal amount, PaymentMethod method){
        return Payment.builder()
                .id(id)
                .amount(amount)
                .paidAt(LocalDateTime.now())
                .status(PaymentStatus.EXECUTED)
                .payMethod(method)
                .build();
    }
}
