package com.example.InstalllmentSystem.core.gateway;

import com.example.InstalllmentSystem.core.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerGateway {

    Customer save(Customer customer);

    void deleteById(String id);

    boolean existById (String id);

    Customer findById(String id);

    Page<Customer> findAll(Pageable pageable);
}
