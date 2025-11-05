package com.example.InstalllmentSystem.core.exception.customer;

import org.apache.coyote.BadRequestException;

public class CustomertIdNotFoundException extends BadRequestException {

    public CustomertIdNotFoundException(String id) {
        super("Not found id " + id);
    }
}
