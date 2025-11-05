package com.example.InstalllmentSystem.core.exception.payment;

import org.apache.coyote.BadRequestException;

public class PaymentAmountNotFoundException extends BadRequestException {

    public PaymentAmountNotFoundException() {
        super("Amount not found");
    }
}
