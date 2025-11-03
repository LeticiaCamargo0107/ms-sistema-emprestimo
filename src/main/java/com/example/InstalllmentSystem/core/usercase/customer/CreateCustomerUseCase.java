package com.example.InstalllmentSystem.core.usercase.customer;

import com.example.InstalllmentSystem.core.domain.Customer;
import com.example.InstalllmentSystem.core.domain.enumeration.CustomerStatus;
import org.springframework.stereotype.Component;
import java.time.LocalDate;


@Component
public class CreateCustomerUseCase {

    public Customer execute(String id, String name, LocalDate birthdate, String document) {
        return Customer.builder()
                .id(id)
                .name(name)
                .birthDate(birthdate)
                .status(CustomerStatus.ACTIVE)
                .document(document)
                .build();
    }
}
