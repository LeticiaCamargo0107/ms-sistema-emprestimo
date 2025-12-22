package com.example.InstallmentSystem.core.exception.customer;

import org.apache.coyote.BadRequestException;

public class CustomerBirthDateException extends BadRequestException {

    public CustomerBirthDateException () {
        super("Age must be greater than 18 to create a customer for contract");
    }
}
