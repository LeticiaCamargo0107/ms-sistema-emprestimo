package com.example.InstalllmentSystem.core.usercase.customer;

import com.example.InstalllmentSystem.core.domain.Customer;
import com.example.InstalllmentSystem.core.domain.enumeration.CustomerStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetByNameCustomerUseCase {

    public Customer execute(String name) {
        var customer1 = Customer.builder()
                .name("Roberto Carlos")
                .id("bgrsdfbgthys4534")
                .status(CustomerStatus.BLOCKED)
                .build();

        var customer2 = Customer.builder()
                .name("Antonieta")
                .id("bgrsdfbgthys4534")
                .status(CustomerStatus.BLOCKED)
                .build();

        var customer3 = Customer.builder()
                .name("Axl Rose")
                .id("bgrsdfbgthys4534")
                .status(CustomerStatus.BLOCKED)
                .build();

        List<Customer> listCustomers = List.of(customer1, customer2, customer3);
        System.out.printf("Get for name: %s\n", name);

        for (Customer customer : listCustomers) {
            if (customer.getName().equals(name)) {
                return customer;
            }
        }
        return null;
    }
}
