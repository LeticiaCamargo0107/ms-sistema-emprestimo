package com.example.InstallmentSystem.core.gateway;

import com.example.InstallmentSystem.core.domain.Payment;

public interface NotifyGateway {
    String createNotify (Payment payment);
}
