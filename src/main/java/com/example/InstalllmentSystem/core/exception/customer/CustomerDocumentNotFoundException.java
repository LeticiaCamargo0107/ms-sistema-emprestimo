package com.example.InstalllmentSystem.core.exception.customer;

import org.apache.coyote.BadRequestException;

public class CustomerDocumentNotFoundException extends BadRequestException {

    public CustomerDocumentNotFoundException() {
        super("Can't find a customer with this document");
    }
}
