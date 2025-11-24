package com.example.InstalllmentSystem.core.gateway;

import com.example.InstalllmentSystem.core.domain.Payment;

import java.util.List;

public interface PaymentGateway {

    Payment save(Payment payment);

    void deleteById(String id);

    boolean existById (String id);

    Payment findById(String id);

    Payment update(Payment payment);

    List<Payment> findAll();
}
