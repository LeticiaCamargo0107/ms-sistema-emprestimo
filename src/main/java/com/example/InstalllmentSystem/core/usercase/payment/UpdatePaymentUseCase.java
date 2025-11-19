package com.example.InstalllmentSystem.core.usercase.payment;

import com.example.InstalllmentSystem.core.domain.Payment;
import com.example.InstalllmentSystem.core.exception.payment.PaymentAmountZeroException;
import com.example.InstalllmentSystem.core.exception.payment.PaymentNotFoundException;
import com.example.InstalllmentSystem.core.gateway.PaymentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class UpdatePaymentUseCase {

    private final PaymentGateway paymentGateway;

    public Payment execute(Payment payment) throws PaymentNotFoundException, PaymentAmountZeroException {

        if (payment.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new PaymentAmountZeroException();
        }

        if (paymentGateway.existById(payment.getId())) {
            Payment updatePayment = paymentGateway.findById(payment.getId());
            System.out.printf("Update for pay %s, change amount: R$ %.2f\n", payment.getId(), payment.getAmount());
            updatePayment.setAmount(payment.getAmount());

            return updatePayment;
        }

        throw new PaymentNotFoundException(payment.getId());
    }
}
