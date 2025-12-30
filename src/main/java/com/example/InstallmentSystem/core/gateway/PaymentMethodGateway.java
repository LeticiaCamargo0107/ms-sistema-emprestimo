package com.example.InstallmentSystem.core.gateway;

import com.example.InstallmentSystem.core.domain.Payment;
import com.example.InstallmentSystem.core.domain.enumeration.PaymentMethod;

public interface PaymentMethodGateway {
    void process (Payment payment);

    boolean supports(PaymentMethod method);
}
