package com.example.InstalllmentSystem.core.usercase.payment;

import com.example.InstalllmentSystem.core.exception.payment.PaymentNotFoundException;
import com.example.InstalllmentSystem.core.gateway.PaymentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteByIdPaymentUseCase {

    private final PaymentGateway paymentGateway;

    public void execute(String id) throws PaymentNotFoundException {

        if (paymentGateway.existById(id)) {
            paymentGateway.deleteById(id);
            System.out.printf("Delete by id: %s\n", id);
            return;
        }
        throw new PaymentNotFoundException(id);
    }
}
