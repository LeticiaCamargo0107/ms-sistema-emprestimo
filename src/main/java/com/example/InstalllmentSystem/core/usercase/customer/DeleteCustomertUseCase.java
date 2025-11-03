package com.example.InstalllmentSystem.core.usercase.customer;

import com.example.InstalllmentSystem.core.domain.Customer;
import com.example.InstalllmentSystem.core.domain.enumeration.CustomerStatus;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class DeleteCustomertUseCase {

    public Customer execute(String id) {
        var customer1 = Customer.builder()
                .id("1234")
                .name("Lucinda")
                .birthDate(LocalDate.of(1955,4,11))
                .build();

        if (customer1.getId().equals(id)) {
            System.out.printf("Delete by id: %s\n", id);
            return customer1;
        }
        return null;
    }
}
