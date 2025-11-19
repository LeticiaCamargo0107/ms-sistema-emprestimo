package com.example.InstalllmentSystem.core.gateway;

import com.example.InstalllmentSystem.core.domain.Customer;
import com.example.InstalllmentSystem.core.domain.Payment;

public interface PaymentGateway {

    Payment save(Payment payment);

    void deleteById(String id);

    boolean existById (String id);

    Payment findById(String id);

    Payment update(Payment payment);
}
