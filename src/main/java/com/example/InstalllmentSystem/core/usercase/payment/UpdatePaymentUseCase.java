package com.example.InstalllmentSystem.core.usercase.payment;

import com.example.InstalllmentSystem.core.domain.Payment;
import com.example.InstalllmentSystem.core.domain.enumeration.PaymentMethod;
import com.example.InstalllmentSystem.core.domain.enumeration.PaymentStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class UpdatePaymentUseCase {

    public Payment execute(Payment payment){
        var payment1 = Payment.builder()
                .id(payment.getId())
                .status(PaymentStatus.EXECUTED)
                .paidAt(LocalDateTime.now())
                .amount(BigDecimal.valueOf(123456789))
                .build();

        var payment2 = Payment.builder()
                .id(payment.getId())
                .status(PaymentStatus.EXECUTED)
                .paidAt(LocalDateTime.now())
                .amount(BigDecimal.valueOf(44343))
                .build();

        var payment3 = Payment.builder()
                .id(payment.getId())
                .status(PaymentStatus.EXECUTED)
                .paidAt(LocalDateTime.now())
                .amount(BigDecimal.valueOf(9999))
                .build();

        List<Payment> list = List.of(payment1, payment2, payment3);
        for (Payment id : list){
            if (id.getId().equals(payment.getId())) {
                System.out.printf("Update for id %s, change amount: R$ %.2f\n", payment.getId(), payment.getAmount());
                id.setAmount(payment.getAmount());
                return id;
            }
        }
        return payment1;
    }
}
