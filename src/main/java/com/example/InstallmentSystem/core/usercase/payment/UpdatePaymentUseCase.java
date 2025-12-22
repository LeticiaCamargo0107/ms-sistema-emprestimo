package com.example.InstallmentSystem.core.usercase.payment;

import com.example.InstallmentSystem.core.domain.Payment;
import com.example.InstallmentSystem.core.exception.payment.PaymentAmountZeroException;
import com.example.InstallmentSystem.core.exception.payment.PaymentNotFoundException;
import com.example.InstallmentSystem.core.gateway.GenericGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdatePaymentUseCase {

    private final GenericGateway<Payment> paymentGateway;
    private final GetByIdPaymentUseCase getByIdPaymentUseCase;

    public Payment execute(String id, Payment payment) throws PaymentNotFoundException, PaymentAmountZeroException {

        if (payment.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            log.error("Amount must be greater than zero");
            throw new PaymentAmountZeroException();
        }
        var saved = getByIdPaymentUseCase.execute(id);
        saved.setAmount(payment.getAmount());

        return paymentGateway.save(saved);
    }
}
