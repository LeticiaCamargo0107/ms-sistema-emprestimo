package com.example.InstallmentSystem.core.usercase.customer;

import com.example.InstallmentSystem.core.exception.customer.CustomerNotFoundException;
import com.example.InstallmentSystem.core.gateway.CustomerGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteCustomerUseCase {

    private final CustomerGateway customerGateway;
    public void execute(String id) throws CustomerNotFoundException {

        if (!customerGateway.existById(id)) {
            log.error("Customer not found by id");
            throw new CustomerNotFoundException(id);
        }
        customerGateway.deleteById(id);
    }
}
