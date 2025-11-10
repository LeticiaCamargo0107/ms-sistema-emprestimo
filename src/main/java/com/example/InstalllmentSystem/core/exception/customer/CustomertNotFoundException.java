package com.example.InstalllmentSystem.core.exception.customer;

import org.apache.coyote.BadRequestException;

public class CustomertNotFoundException extends BadRequestException {

    public CustomertNotFoundException(String id) {
        super("Customer not found by id " + id);
    }
}
