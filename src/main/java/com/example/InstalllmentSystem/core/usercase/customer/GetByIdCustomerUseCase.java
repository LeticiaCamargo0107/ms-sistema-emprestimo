package com.example.InstalllmentSystem.core.usercase.customer;

import com.example.InstalllmentSystem.core.domain.Customer;
import com.example.InstalllmentSystem.core.exception.customer.CustomertNotFoundException;
import com.example.InstalllmentSystem.core.gateway.GenericGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class GetByIdCustomerUseCase {

    private final GenericGateway<Customer> customerGateway;

    public Customer execute(String id) throws CustomertNotFoundException {

        if (!customerGateway.existById(id)) {
            log.error("Customer not found by id");
            throw new CustomertNotFoundException(id);
        }
        return customerGateway.findById(id);
    }
}
