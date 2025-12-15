package com.example.InstalllmentSystem.core.usercase.customer;

import com.example.InstalllmentSystem.core.exception.customer.CustomertNotFoundException;
import com.example.InstalllmentSystem.core.gateway.CustomerGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteCustomerUseCase {

    private final CustomerGateway customerGateway;
    public void execute(String id) throws CustomertNotFoundException {

        if (!customerGateway.existById(id)) {
            log.error("Customer not found by id");
            throw new CustomertNotFoundException(id);
        }
        customerGateway.deleteById(id);
    }
}
