package com.example.InstalllmentSystem.core.usercase.payment;

import com.example.InstalllmentSystem.core.domain.Payment;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class DeleteByIdPaymentUseCase {
    public Payment execute(String id){
        var payment1 = Payment.builder()
                .id("1234")
                .paidAt(LocalDateTime.now())
                .amount(BigDecimal.valueOf(212.89))
                .build();

        if (payment1.getId().equals(id)) {
            System.out.printf("Delete by id: %s\n", id);
            return payment1;
        }

        return null;
    }
}
