package com.example.InstalllmentSystem.core.usercase.customer;

import com.example.InstalllmentSystem.core.domain.Customer;
import com.example.InstalllmentSystem.core.domain.enumeration.CustomerStatus;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;

@Component
public class FindCustomerUseCase {
    public List<Customer> execute(){
        var customer1 = Customer.builder()
                .name("Roberto Calos")
                .id("jrw958302hrwo390u57")
                .status(CustomerStatus.BLOCKED)
                .birthDate(LocalDate.of(2000, 9,21))
                .build();

        var customer2 = Customer.builder()
                .name("Claudia Arraia")
                .id("aceoslridktufjyyghyy0192837456")
                .status(CustomerStatus.ACTIVE)
                .build();

        var customer3 = Customer.builder()
                .name("Júlio")
                .id("vnçjdfavnbfuçd764368584")
                .status(CustomerStatus.INACTIVE)
                .birthDate(LocalDate.of(1900,3,14))
                .build();

        System.out.println("Find all of Customers");
        return List.of(customer1, customer2, customer3);
    }
}
