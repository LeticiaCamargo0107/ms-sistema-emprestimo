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
    private final GetByIdPaymentUseCase getByIdPaymentUseCase;

    public Payment execute(String id, Payment payment) throws PaymentNotFoundException, PaymentAmountZeroException {

        if (payment.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new PaymentAmountZeroException();
        }

        var saved = getByIdPaymentUseCase.execute(id);
        System.out.printf("Update for pay %s, change amount: R$ %.2f\n", payment.getId(), payment.getAmount());
        saved.setAmount(payment.getAmount());

        return paymentGateway.update(saved);
    }
}
