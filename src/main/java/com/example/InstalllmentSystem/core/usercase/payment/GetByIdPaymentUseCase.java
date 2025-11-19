package com.example.InstalllmentSystem.core.usercase.payment;

import com.example.InstalllmentSystem.core.domain.Payment;
import com.example.InstalllmentSystem.core.exception.payment.PaymentAmountNotFoundException;
import com.example.InstalllmentSystem.core.gateway.PaymentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetByIdPaymentUseCase {

    private final PaymentGateway paymentGateway;

    public Payment execute(String id) throws PaymentAmountNotFoundException {

        if (paymentGateway.existById(id)) {
            System.out.printf("Get for id: R$ %.2f\n", id);
            return paymentGateway.findById(id);
        }

        throw new PaymentAmountNotFoundException();
    }
}
