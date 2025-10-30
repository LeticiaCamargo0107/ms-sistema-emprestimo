package com.example.InstalllmentSystem.core.usercase.payment;

import com.example.InstalllmentSystem.core.domain.Payment;
import com.example.InstalllmentSystem.core.domain.enumeration.PaymentMethod;
import com.example.InstalllmentSystem.core.domain.enumeration.PaymentStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class UpdatePaymentUseCase {

    public Payment execute(String id, PaymentMethod method, BigDecimal amount){
        return Payment.builder()
                .id(id)
                .status(PaymentStatus.EXECUTED)
                .payMethod(method)
                .paidAt(LocalDateTime.now())
                .amount(amount)
                .build();
    }
}
