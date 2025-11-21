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

    public Customer execute(Customer customer) throws CustomerDocumentNotFoundException {

        if (!customerGateway.existById(customer.getId())) {
            throw new CustomerDocumentNotFoundException();
        }

        Customer updateCustomer = customerGateway.findById(customer.getId());

        if (customer.getDocument().equals(updateCustomer.getDocument())) {
            throw new CustomerDocumentNotFoundException();
        }

        updateCustomer.setName(customer.getName());
        System.out.printf("Update name to %s\n", customer.getName());
        return updateCustomer;
    }
}
