package com.example.InstalllmentSystem.core.usercase.customer;

import com.example.InstalllmentSystem.core.domain.Customer;
import com.example.InstalllmentSystem.core.exception.customer.CustomertNotFoundException;
import com.example.InstalllmentSystem.core.gateway.CustomerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class GetByIdCustomerUseCase {

    private final CustomerGateway customerGateway;

    public Customer execute(String id) throws CustomertNotFoundException {

        if (!customerGateway.existById(id)) {
            throw new CustomertNotFoundException(id);
        }
        return customerGateway.findById(id);
    }
}
