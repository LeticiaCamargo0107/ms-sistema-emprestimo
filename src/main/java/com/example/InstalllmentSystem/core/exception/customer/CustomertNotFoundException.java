package com.example.InstalllmentSystem.core.exception.customer;

import org.apache.coyote.BadRequestException;

public class CustomertNotFoundException extends BadRequestException {

    public CustomertNotFoundException() {
        super("Contract not found");
    }
}
