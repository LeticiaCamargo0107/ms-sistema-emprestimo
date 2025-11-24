package com.example.InstalllmentSystem.core.usercase.customer;

import com.example.InstalllmentSystem.core.exception.customer.CustomertNotFoundException;
import com.example.InstalllmentSystem.core.gateway.CustomerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteCustomerUseCase {

    private final CustomerGateway customerGateway;
    public void execute(String id) throws CustomertNotFoundException {

        if (!customerGateway.existById(id)) {
            throw new CustomertNotFoundException(id);
        }
        customerGateway.deleteById(id);
        System.out.printf("Delete by id: %s\n", id);
    }
}
