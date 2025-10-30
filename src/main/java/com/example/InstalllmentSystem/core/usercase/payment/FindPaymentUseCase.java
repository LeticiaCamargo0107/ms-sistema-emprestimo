package com.example.InstalllmentSystem.core.usercase.payment;

import com.example.InstalllmentSystem.core.domain.Payment;
import com.example.InstalllmentSystem.core.domain.enumeration.PaymentMethod;
import com.example.InstalllmentSystem.core.domain.enumeration.PaymentStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class FindPaymentUseCase {
    public Payment execute(String id, PaymentStatus status, PaymentMethod method, LocalDateTime paidAt, BigDecimal amount){
        return Payment.builder()
                .id(id)
                .status(status)
                .payMethod(method)
                .paidAt(paidAt)
                .amount(amount)
                .build();
    }
}
