package com.example.InstallmentSystem.core.domain;

import com.example.InstallmentSystem.core.domain.enumeration.PaymentMethod;
import com.example.InstallmentSystem.core.exception.payment.PaymentMethodNotFoundException;
import com.example.InstallmentSystem.core.gateway.PaymentMethodGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class PaymentMethodFactory {

    private final List<PaymentMethodGateway> paymentMethodGateway;

    public PaymentMethodGateway supply(PaymentMethod paymentMethod) throws PaymentMethodNotFoundException {
        return paymentMethodGateway.stream()
                .filter(g -> g.supports(paymentMethod))
                .findFirst()
                .orElseThrow(PaymentMethodNotFoundException::new);

    }
}
