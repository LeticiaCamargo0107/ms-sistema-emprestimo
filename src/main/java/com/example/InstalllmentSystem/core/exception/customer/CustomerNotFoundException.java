package com.example.InstalllmentSystem.core.exception.customer;

import org.apache.coyote.BadRequestException;

public class CustomerNotFoundException extends BadRequestException {

    public CustomerNotFoundException(String id) {
        super("Customer not found by id " + id);
    }
}
