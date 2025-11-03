package com.example.InstalllmentSystem.core.usercase.payment;

import com.example.InstalllmentSystem.core.domain.Payment;
import com.example.InstalllmentSystem.core.domain.enumeration.PaymentMethod;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class GetByAmountPaymentUseCase {
    public Payment execute(BigDecimal amount){

        var payment1 = Payment.builder()
                .id("fcdgvhkr333")
                .amount(BigDecimal.valueOf(19074))
                .paidAt(LocalDateTime.now())
                .payMethod(PaymentMethod.PIX)
                .build();

        var payment2 = Payment.builder()
                .id("fmkjghnnyhgrfz")
                .amount(BigDecimal.valueOf(0.0003))
                .paidAt(LocalDateTime.now())
                .payMethod(PaymentMethod.SLIP)
                .build();

        var payment3 = Payment.builder()
                .id("5jf93jg0o3k099")
                .amount(BigDecimal.valueOf(10000000))
                .paidAt(LocalDateTime.now())
                .payMethod(PaymentMethod.CREDIT_CARD)
                .build();

        List<Payment> list = List.of(payment1, payment2, payment3);
        for (Payment payment : list) {
            if (payment.getAmount().equals(amount)) {
                System.out.printf("Get for amount: R$ %.2f\n", amount);
                return payment;
            }
        }
        return null;
    }
}
