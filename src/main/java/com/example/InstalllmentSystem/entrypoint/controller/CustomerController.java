
package com.example.InstalllmentSystem.entrypoint.controller;

import com.example.InstalllmentSystem.core.domain.Customer;
import com.example.InstalllmentSystem.core.exception.customer.CustomerBirthDateException;
import com.example.InstalllmentSystem.core.exception.customer.CustomerDocumentNotFoundException;
import com.example.InstalllmentSystem.core.exception.customer.CustomertNotFoundException;
import com.example.InstalllmentSystem.core.usercase.customer.CreateCustomerUseCase;
import com.example.InstalllmentSystem.core.usercase.customer.DeleteCustomerUseCase;
import com.example.InstalllmentSystem.core.usercase.customer.FindCustomerUseCase;
import com.example.InstalllmentSystem.core.usercase.customer.GetByIdCustomerUseCase;
import com.example.InstalllmentSystem.core.usercase.customer.UpdateCustomerUseCase;
import com.example.InstalllmentSystem.entrypoint.DTOs.CustomerDTO;
import com.example.InstalllmentSystem.entrypoint.mapper.CustomerMapper;
import jakarta.validation.Valid;
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

    private final CreateCustomerUseCase createCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;
    private final FindCustomerUseCase findCustomerUseCase;
    private final GetByIdCustomerUseCase getByNameCustomerUseCase;
    private final CustomerMapper customerMapper;

    @GetMapping("/{name}")
    public Customer getByName(@PathVariable String name) throws CustomertNotFoundException {

        return getByNameCustomerUseCase.execute(name);
    }

    @GetMapping
    public List<Customer> findAll() throws CustomertNotFoundException {

        return findCustomerUseCase.execute();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Customer create(@RequestBody @Valid CustomerDTO customerDTO) throws CustomerBirthDateException {

        var customer = customerMapper.toDomain(customerDTO);
        return createCustomerUseCase.execute(customer);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) throws CustomertNotFoundException {

        deleteCustomerUseCase.execute(id);
    }

    @PutMapping
    public Customer update(@RequestBody @Valid CustomerDTO customerDTO) throws CustomerDocumentNotFoundException {

        var customer = customerMapper.toDomain(customerDTO);
        return updateCustomerUseCase.execute(customer);
    }
}