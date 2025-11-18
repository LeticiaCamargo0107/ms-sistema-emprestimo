package com.example.InstalllmentSystem.core.gateway;

import com.example.InstalllmentSystem.core.domain.Payment;

public interface PaymentGateway {

    Payment save (Payment payment);
}
