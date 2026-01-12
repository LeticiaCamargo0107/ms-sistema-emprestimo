package com.example.InstallmentSystem.core.usercase.customer;

import com.example.InstallmentSystem.core.domain.Customer;
import com.example.InstallmentSystem.core.domain.enumeration.CustomerStatus;
import com.example.InstallmentSystem.core.exception.customer.CustomerAddressNotFoundException;
import com.example.InstallmentSystem.core.exception.customer.CustomerBirthDateException;
import com.example.InstallmentSystem.core.gateway.CustomerGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.example.InstallmentSystem.core.util.CustomerUtils.getAge;

@Slf4j
@RequiredArgsConstructor
@Component
public class CreateCustomerUseCase {

    private final CustomerGateway customerGateway;

    public Customer execute(Customer customer) throws CustomerBirthDateException, CustomerAddressNotFoundException {

        if (customer.getBirthDate() == null || getAge(customer) < 18) {
            log.error("Age of customer must be greater than zero");
            throw new CustomerBirthDateException();
        }

        if (customer.getZipcode() == null) {
            log.error("Address must not be null");
            throw new CustomerAddressNotFoundException(customer.getZipcode());
        }

        customer.setStatus(CustomerStatus.ACTIVE);
        return customerGateway.save(customer);
    }
}
