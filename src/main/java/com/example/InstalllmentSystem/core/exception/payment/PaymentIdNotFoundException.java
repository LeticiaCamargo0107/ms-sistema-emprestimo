package com.example.InstalllmentSystem.core.exception.payment;

import org.apache.coyote.BadRequestException;

public class PaymentIdNotFoundException extends BadRequestException {

    public PaymentIdNotFoundException(String id) {
        super("Not found id " + id);
    }
}
