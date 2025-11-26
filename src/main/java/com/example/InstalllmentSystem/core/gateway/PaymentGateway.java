package com.example.InstalllmentSystem.core.gateway;

import com.example.InstalllmentSystem.core.domain.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PaymentGateway {

    Payment save(Payment payment);

    void deleteById(String id);

    boolean existById (String id);

    Payment findById(String id);

    Page<Payment> findAll(Pageable pageable);
}
