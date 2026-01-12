package com.example.InstallmentSystem.core.usercase.payment;

import com.example.InstallmentSystem.core.domain.Payment;
import com.example.InstallmentSystem.core.gateway.ContractGateway;
import com.example.InstallmentSystem.core.gateway.PaymentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class FindAllPaymentUseCase {

    private final PaymentGateway paymentGateway;

    public Page<Payment> execute(Pageable pageable) {
        return paymentGateway.findAll(pageable);
    }
}
