package com.example.InstallmentSystem.entrypoint.controller;

import com.example.InstallmentSystem.core.domain.Customer;
import com.example.InstallmentSystem.core.exception.customer.CustomerAddressNotFoundException;
import com.example.InstallmentSystem.core.exception.customer.CustomerBirthDateException;
import com.example.InstallmentSystem.core.exception.customer.CustomerNotFoundException;
import com.example.InstallmentSystem.core.usercase.customer.CreateCustomerUseCase;
import com.example.InstallmentSystem.core.usercase.customer.DeleteCustomerUseCase;
import com.example.InstallmentSystem.core.usercase.customer.FindAllCustomerUseCase;
import com.example.InstallmentSystem.core.usercase.customer.GetByIdCustomerUseCase;
import com.example.InstallmentSystem.core.usercase.customer.UpdateCustomerUseCase;
import com.example.InstallmentSystem.entrypoint.dto.CustomerDTO;
import com.example.InstallmentSystem.entrypoint.mapper.CustomerMapper;
import com.example.InstallmentSystem.entrypoint.swagger.CustomerControllerAPI;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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


@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/customers")
public class CustomerController implements CustomerControllerAPI {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;
    private final FindAllCustomerUseCase findCustomerUseCase;
    private final GetByIdCustomerUseCase getByNameCustomerUseCase;
    private final CustomerMapper customerMapper;

    @GetMapping("/{id}")
    public Customer getById(@PathVariable String id) throws CustomerNotFoundException {

        return getByNameCustomerUseCase.execute(id);
    }

    @GetMapping
    public Page<Customer> findAll(@PageableDefault Pageable pageable) {

        return findCustomerUseCase.execute(pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Customer create(@RequestBody @Valid CustomerDTO customerDTO) throws CustomerBirthDateException, CustomerAddressNotFoundException {

        var customer = customerMapper.toDomain(customerDTO);
        return createCustomerUseCase.execute(customer);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) throws CustomerNotFoundException {

        deleteCustomerUseCase.execute(id);
    }

    @PutMapping("/{id}")
    public Customer update(@PathVariable String id, @RequestBody @Valid CustomerDTO customerDTO) throws CustomerNotFoundException, CustomerAddressNotFoundException {

        var customer = customerMapper.toDomain(customerDTO);
        return updateCustomerUseCase.execute(id, customer);
    }
}