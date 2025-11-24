package com.example.InstalllmentSystem.core.usercase.customer;

import com.example.InstalllmentSystem.core.domain.Customer;
import com.example.InstalllmentSystem.core.exception.customer.CustomerDocumentNotFoundException;
import com.example.InstalllmentSystem.core.exception.customer.CustomertNotFoundException;
import com.example.InstalllmentSystem.core.gateway.CustomerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class UpdateCustomerUseCase {

    private final CustomerGateway customerGateway;
    private final GetByIdCustomerUseCase getByIdCustomerUseCase;

    public Customer execute(String id, Customer customer) throws CustomerDocumentNotFoundException, CustomertNotFoundException {

        var saved = getByIdCustomerUseCase.execute(id);

        if (customer.getDocument().equals(saved.getDocument())) {
            throw new CustomerDocumentNotFoundException();
        }

        saved.setName(customer.getName());
        System.out.printf("Update name to %s\n", customer.getName());

        return customerGateway.update(saved);
    }
}
