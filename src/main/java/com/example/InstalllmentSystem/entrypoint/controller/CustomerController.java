
package com.example.InstalllmentSystem.entrypoint.controller;

import com.example.InstalllmentSystem.core.domain.Customer;
import com.example.InstalllmentSystem.core.usercase.customer.CreateCustomerUseCase;
import com.example.InstalllmentSystem.core.usercase.customer.DeleteCustomertUseCase;
import com.example.InstalllmentSystem.core.usercase.customer.FindCustomerUseCase;
import com.example.InstalllmentSystem.core.usercase.customer.GetByNameCustomerUseCase;
import com.example.InstalllmentSystem.core.usercase.customer.UpdateCustomerUseCase;
import lombok.RequiredArgsConstructor;
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

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/customers")
public class CustomerController {

    final private CreateCustomerUseCase createCustomerUseCase;
    final private DeleteCustomertUseCase deleteCustomerUseCase;
    final private UpdateCustomerUseCase updateCustomerUseCase;
    final private FindCustomerUseCase findCustomerUseCase;
    final private GetByNameCustomerUseCase getByNameCustomerUseCase;

    @GetMapping("/{name}")
    public Customer getByName(@PathVariable String name) {

        return getByNameCustomerUseCase.execute(name);
    }

    @GetMapping
    public List<Customer> findAll() {

        return findCustomerUseCase.execute();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Customer create(@RequestBody Customer customer) {

        return createCustomerUseCase.execute(customer.getId(), customer.getName(), customer.getBirthDate(), customer.getDocument());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public Customer deleteById(@PathVariable String id) {

        return deleteCustomerUseCase.execute(id);
    }

    @PutMapping
    public Customer update(@RequestBody Customer customer) {

        return updateCustomerUseCase.execute(customer);
    }
}