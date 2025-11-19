package com.example.InstalllmentSystem.core.gateway;

import com.example.InstalllmentSystem.core.domain.Customer;

public interface CustomerGateway {

    Customer save(Customer customer);

    void deleteById(String id);

    boolean existById (String id);

    Customer findById(String id);

    Customer update(Customer customer);
}
