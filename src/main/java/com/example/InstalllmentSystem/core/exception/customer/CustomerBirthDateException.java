package com.example.InstalllmentSystem.core.exception.customer;

import org.apache.coyote.BadRequestException;

public class CustomerBirthDateException extends BadRequestException {

    public CustomerBirthDateException () {
        super("Age must be granter than 18 to create a customer for contract");
    }
}
