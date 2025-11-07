package com.example.InstalllmentSystem.core.usercase.payment;

import com.example.InstalllmentSystem.core.domain.Payment;
import com.example.InstalllmentSystem.core.domain.enumeration.PaymentMethod;
import com.example.InstalllmentSystem.core.domain.enumeration.PaymentStatus;
import com.example.InstalllmentSystem.core.exception.payment.PaymentAmountZeroException;
import com.example.InstalllmentSystem.core.exception.payment.PaymentIdNotFoundException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class UpdatePaymentUseCase {

    public Payment execute(Payment payment) throws PaymentIdNotFoundException, PaymentAmountZeroException {

        String conversionString = String.valueOf(payment.getAmount());
        Integer conversion = Integer.valueOf(conversionString);
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

        List<Payment> listPayments = List.of(payment1, payment2, payment3);
        for (Payment pay : listPayments){
            if (pay.getId().equals(payment.getId())) {
                if (conversion <= 0){
                    throw new PaymentAmountZeroException();
                }
                System.out.printf("Update for pay %s, change amount: R$ %.2f\n", payment.getId(), payment.getAmount());
                pay.setAmount(payment.getAmount());
                return pay;
            }
        }
        throw new PaymentIdNotFoundException(payment.getId());
    }
}
