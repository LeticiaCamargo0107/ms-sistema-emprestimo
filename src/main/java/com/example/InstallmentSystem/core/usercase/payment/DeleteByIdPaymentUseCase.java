package com.example.InstallmentSystem.core.usercase.payment;

import com.example.InstallmentSystem.core.domain.Payment;
import com.example.InstallmentSystem.core.exception.payment.PaymentNotFoundException;
import com.example.InstallmentSystem.core.gateway.GenericGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteByIdPaymentUseCase {

    private final GenericGateway<Payment> paymentGateway;

    public void execute(String id) throws PaymentNotFoundException {

        if (!paymentGateway.existById(id)) {
            log.error("Payment not found by id");
            throw new PaymentNotFoundException(id);
        }
        paymentGateway.deleteById(id);
    }
}
