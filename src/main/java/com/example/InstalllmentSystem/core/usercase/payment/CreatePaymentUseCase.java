package com.example.InstalllmentSystem.core.usercase.payment;

import com.example.InstalllmentSystem.core.domain.Payment;
import com.example.InstalllmentSystem.core.domain.enumeration.PaymentStatus;
import com.example.InstalllmentSystem.core.exception.payment.PaymentAmountZeroException;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;


@Component
public class CreatePaymentUseCase {

    public Payment execute(Payment payment) throws PaymentAmountZeroException {

        String conversionString = String.valueOf(payment.getAmount());
        Integer conversion = Integer.valueOf(conversionString);

        if (conversion <= 0) {
            throw new PaymentAmountZeroException();
        }
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
