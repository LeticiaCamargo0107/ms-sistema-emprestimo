package com.example.InstalllmentSystem.core.usercase.customer;

import com.example.InstalllmentSystem.core.domain.Customer;
import com.example.InstalllmentSystem.core.domain.enumeration.CustomerStatus;
import com.example.InstalllmentSystem.core.exception.customer.CustomerAddressNotFoundException;
import com.example.InstalllmentSystem.core.exception.customer.CustomerBirthDateException;
import com.example.InstalllmentSystem.core.gateway.CustomerGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.example.InstalllmentSystem.core.util.CustomerUtils.calculateAge;

@Slf4j
@RequiredArgsConstructor
@Component
public class CreateCustomerUseCase {

    private final CustomerGateway customerGateway;

    public Customer execute(Customer customer) throws CustomerBirthDateException, CustomerAddressNotFoundException {

        if (calculateAge(customer) < 18) {
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
