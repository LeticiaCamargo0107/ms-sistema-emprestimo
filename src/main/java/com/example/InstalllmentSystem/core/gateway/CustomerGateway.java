package com.example.InstalllmentSystem.core.gateway;

import com.example.InstalllmentSystem.core.domain.Customer;
import com.example.InstalllmentSystem.core.exception.customer.CustomerAddressNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerGateway {

    Customer save(Customer customer) throws CustomerAddressNotFoundException;

    void deleteById(String id);

    boolean existById (String id);

    Customer findById(String id);

    Page<Customer> findAll(Pageable pageable);
}
