package com.example.InstallmentSystem.core.usercase.payment;

import com.example.InstallmentSystem.core.domain.Payment;
import com.example.InstallmentSystem.core.domain.PaymentMethodFactory;
import com.example.InstallmentSystem.core.domain.enumeration.PaymentStatus;
import com.example.InstallmentSystem.core.exception.payment.PaymentAmountZeroException;
import com.example.InstallmentSystem.core.exception.payment.PaymentMethodNotFoundException;
import com.example.InstallmentSystem.core.gateway.ContractGateway;
import com.example.InstallmentSystem.core.gateway.PaymentGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Component
public class CreatePaymentUseCase {

    private final PaymentGateway paymentGateway;
    private final PaymentMethodFactory methodFactory;

    public Payment execute(Payment payment) throws PaymentAmountZeroException, PaymentMethodNotFoundException {

        if (payment.getPayMethod() == null) {
            log.error("The method must be PIX, debit card, credit card or split");
            throw new PaymentMethodNotFoundException();
        }

        if (payment.getAmount() == null || payment.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            log.error("Amount must be greater than zero");
            throw new PaymentAmountZeroException();
        }

        payment.setStatus(PaymentStatus.EXECUTED);
        payment.setPaidAt(LocalDateTime.now());
        methodFactory.supply(payment.getPayMethod()).process(payment);

        return paymentGateway.save(payment);
    }
}
