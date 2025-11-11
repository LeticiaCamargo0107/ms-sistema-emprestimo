package com.example.InstalllmentSystem.core.usercase.customer;

import com.example.InstalllmentSystem.core.domain.Customer;
import com.example.InstalllmentSystem.core.domain.enumeration.CustomerStatus;
import com.example.InstalllmentSystem.core.exception.customer.CustomerDocumentNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UpdateCustomerUseCase {

    public Customer execute(Customer customer) throws CustomerDocumentNotFoundException {

        var customer1 = Customer.builder()
                .name("Lele")
                .birthDate(LocalDate.of(2004, 3, 16))
                .status(CustomerStatus.ACTIVE)
                .build();

        if (customer.getDocument() != customer1.getDocument()) {
            throw new CustomerDocumentNotFoundException();
        }

        customer1.setName(customer.getName());
        System.out.printf("Update name to %s\n", customer.getName());
        return customer1;
    }
}
