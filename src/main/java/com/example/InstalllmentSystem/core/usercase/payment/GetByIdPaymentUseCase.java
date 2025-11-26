package com.example.InstalllmentSystem.core.usercase.payment;

import com.example.InstalllmentSystem.core.domain.Payment;
import com.example.InstalllmentSystem.core.exception.payment.PaymentNotFoundException;
import com.example.InstalllmentSystem.core.gateway.PaymentGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetByIdPaymentUseCase {

    private final PaymentGateway paymentGateway;

    public Payment execute(String id) throws PaymentNotFoundException {

        if (paymentGateway.existById(id)) {
            log.error("Payment not found by id");
            throw new PaymentNotFoundException(id);
        }
        return paymentGateway.findById(id);
    }
}
