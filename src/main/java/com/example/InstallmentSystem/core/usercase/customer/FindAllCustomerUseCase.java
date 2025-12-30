package com.example.InstallmentSystem.core.usercase.customer;

import com.example.InstallmentSystem.core.domain.Customer;
import com.example.InstallmentSystem.core.gateway.CustomerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindAllCustomerUseCase {

    private final CustomerGateway customerGateway;

    public Page<Customer> execute(Pageable pageable) {
        return customerGateway.findAll(pageable);
    }
}
