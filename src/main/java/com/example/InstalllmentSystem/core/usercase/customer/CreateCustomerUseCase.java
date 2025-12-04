package com.example.InstalllmentSystem.core.usercase.customer;

import com.example.InstalllmentSystem.core.domain.Customer;
import com.example.InstalllmentSystem.core.domain.enumeration.CustomerStatus;
import com.example.InstalllmentSystem.core.exception.customer.CustomerBirthDateException;
import com.example.InstalllmentSystem.core.gateway.GenericGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CreateCustomerUseCase {

    private final GenericGateway<Customer> customerGateway;

    public Customer execute(Customer customer) throws CustomerBirthDateException {

        int year = customer.getBirthDate().getYear();
        if (year < 18) {
            log.error("Age of customer must be greater than zero");
            throw new CustomerBirthDateException();
        }

        customer.setStatus(CustomerStatus.ACTIVE);
        return customerGateway.save(customer);
    }
}
