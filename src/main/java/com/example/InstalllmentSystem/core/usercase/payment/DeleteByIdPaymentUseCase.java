package com.example.InstalllmentSystem.core.usercase.payment;

import com.example.InstalllmentSystem.core.domain.Payment;
import com.example.InstalllmentSystem.core.domain.enumeration.PaymentStatus;
import com.example.InstalllmentSystem.core.exception.payment.PaymentIdNotFoundException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class DeleteByIdPaymentUseCase {

    public void execute(String id) throws PaymentIdNotFoundException {
        var payment1 = Payment.builder()
                .id("1234")
                .paidAt(LocalDateTime.now())
                .amount(BigDecimal.valueOf(212.89))
                .status(PaymentStatus.CANCELED)
                .build();

        if (payment1.getId().equals(id)) {
            System.out.printf("Delete by id: %s\n", id);
            return;
        }
        throw new PaymentIdNotFoundException(id);
    }
}
