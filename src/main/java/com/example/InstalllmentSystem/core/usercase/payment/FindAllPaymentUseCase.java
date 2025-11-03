package com.example.InstalllmentSystem.core.usercase.payment;

import com.example.InstalllmentSystem.core.domain.Payment;
import com.example.InstalllmentSystem.core.domain.enumeration.PaymentMethod;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class FindAllPaymentUseCase {
    public List<Payment> execute(){
        var payment1 = Payment.builder()
                .id("er5hcdtg4573kiukgt5")
                .amount(BigDecimal.valueOf(418418))
                .paidAt(LocalDateTime.now())
                .payMethod(PaymentMethod.PIX)
                .build();

        var payment2 = Payment.builder()
                .id("furururur3")
                .amount(BigDecimal.valueOf(1000000000))
                .paidAt(LocalDateTime.now())
                .payMethod(PaymentMethod.CREDIT_CARD)
                .build();

        var payment3 = Payment.builder()
                .id("43jbhvl230gn")
                .amount(BigDecimal.valueOf(434))
                .paidAt(LocalDateTime.now())
                .payMethod(PaymentMethod.SLIP)
                .build();

        System.out.println("Find all of payment");
        return List.of(payment1, payment2, payment3);
    }
}
