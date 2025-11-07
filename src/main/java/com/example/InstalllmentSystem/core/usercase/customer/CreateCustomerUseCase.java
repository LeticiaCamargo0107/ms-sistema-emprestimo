package com.example.InstalllmentSystem.core.usercase.customer;

import com.example.InstalllmentSystem.core.domain.Customer;
import com.example.InstalllmentSystem.core.domain.enumeration.CustomerStatus;
import com.example.InstalllmentSystem.core.exception.customer.CustomerBirthDateException;
import org.springframework.stereotype.Component;
import java.time.LocalDate;


@Component
public class CreateCustomerUseCase {

    public Customer execute(Customer customer) throws CustomerBirthDateException {

        int year = customer.getBirthDate().getYear();
        if (year < 18) {
            throw new CustomerBirthDateException();
        }

        return Customer.builder()
                .id(customer.getId())
                .name(customer.getName())
                .birthDate(customer.getBirthDate())
                .status(CustomerStatus.ACTIVE)
                .document(customer.getDocument())
                .build();
    }

}
