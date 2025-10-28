
package com.example.InstalllmentSystem.entrypoint.controller;

import com.example.InstalllmentSystem.core.domain.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {

    @GetMapping("/{name}")
    public String getByName(@PathVariable String name) {
        System.out.printf("Get for name: %s\n", name);
        return name;
    }

    @GetMapping
    public List<Customer> findAll() {
        System.out.println("Find all of Customers\n");
        return List.of();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Customer createUser() {
        System.out.println("Creating user\n");
        return null;
    }

    @DeleteMapping("/{id}")
    public Customer deleteById(@PathVariable String id) {
        System.out.printf("Delete by id: %s\n", id);
        return null;
    }

    @PutMapping("/{before-name}/{after-name}")
    public Customer updateByName(@PathVariable ("before-name") String before, @PathVariable ("after-name") String after) {
        System.out.printf("Update %s to %s\n", before, after);
        return null;
    }
}