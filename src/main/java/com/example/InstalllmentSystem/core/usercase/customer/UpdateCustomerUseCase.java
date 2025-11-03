package com.example.InstalllmentSystem.core.usercase.customer;

import com.example.InstalllmentSystem.core.domain.Customer;
import com.example.InstalllmentSystem.core.domain.enumeration.CustomerStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UpdateCustomerUseCase {
    public Customer execute(Customer customer){

        var customer1 = Customer.builder()
                .name(customer.getName())
                .birthDate(customer.getBirthDate())
                .status(CustomerStatus.ACTIVE)
                .build();

        System.out.printf("Update name to %s\n", customer.getName());
        return customer1;
    }
}
