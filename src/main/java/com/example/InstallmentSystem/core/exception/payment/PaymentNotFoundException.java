package com.example.InstallmentSystem.core.exception.payment;

import org.apache.coyote.BadRequestException;

public class PaymentNotFoundException extends BadRequestException {

    public PaymentNotFoundException(String id) {
        super("Payment not found by id " + id);
    }
}
