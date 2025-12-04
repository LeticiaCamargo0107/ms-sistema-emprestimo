package com.example.InstalllmentSystem.core.usercase.customer;

import com.example.InstalllmentSystem.core.domain.Customer;
import com.example.InstalllmentSystem.core.gateway.GenericGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindAllCustomerUseCase {

    private final GenericGateway<Customer> customerGateway;

    public Page<Customer> execute(Pageable pageable) {
        return customerGateway.findAll(pageable);
    }
}
