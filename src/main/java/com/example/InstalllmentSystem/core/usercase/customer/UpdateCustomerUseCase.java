package com.example.InstalllmentSystem.core.usercase.customer;

import com.example.InstalllmentSystem.core.domain.Customer;
import com.example.InstalllmentSystem.core.exception.customer.CustomerDocumentNotFoundException;
import com.example.InstalllmentSystem.core.gateway.CustomerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class UpdateCustomerUseCase {

    private final CustomerGateway customerGateway;

    public Customer execute(String document, Customer customer) throws CustomerDocumentNotFoundException {

        if (customer.getDocument() != document) {
            throw new CustomerDocumentNotFoundException();
        }

        Customer updateCustomer = customerGateway.findById(customer.getId());
        updateCustomer.setName(customer.getName());
        System.out.printf("Update name to %s\n", customer.getName());
        return updateCustomer;
    }
}
