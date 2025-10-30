
package com.example.InstalllmentSystem.entrypoint.controller;

import com.example.InstalllmentSystem.core.domain.Customer;
import com.example.InstalllmentSystem.core.domain.enumeration.CustomerStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {

    @GetMapping("/{name}")
    public Customer getByName(@PathVariable String name) {

        var customer = Customer.builder()
                .name("Antonieta")
                .id("bgrsdfbgthys4534")
                .status(CustomerStatus.BLOCKED)
                .build();

        System.out.printf("Get for name: %s\n", name);

        if (customer.getName().equals(name)) {
            return customer;
        }
        return null;
    }

    @GetMapping
    public List<Customer> findAllCustomers() {

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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Customer createCustomers(@RequestBody Customer customer) {

        var customer1 = Customer.builder()
                .id(customer.getId())
                .name(customer.getName())
                .document(customer.getDocument())
                .status(CustomerStatus.ACTIVE)
                .birthDate(customer.getBirthDate())
                .build();

        System.out.println("Creating user");
        return customer1;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public Customer deleteById(@PathVariable String id) {

        var customer1 = Customer.builder()
                .id("hahaha")
                .name("Lucinda")
                .birthDate(LocalDate.of(1955,4,11))
                .build();

        if (customer1.getId().equals(id)) {
            System.out.printf("Delete by id: %s\n", id);
            return customer1;
        }
        return null;
    }

    @PutMapping
    public Customer updateByName(@RequestBody Customer customer) {

        var customer1 = Customer.builder()
                .name(customer.getName())
                .birthDate(customer.getBirthDate())
                .status(CustomerStatus.ACTIVE)
                .build();

        System.out.printf("Update name to %s\n", customer.getName());
        return customer1;
    }
}