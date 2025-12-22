package com.example.InstallmentSystem.core.gateway;

import com.example.InstallmentSystem.core.domain.Payment;

public interface PaymentGateway {
    void payment(Payment payment);
}
