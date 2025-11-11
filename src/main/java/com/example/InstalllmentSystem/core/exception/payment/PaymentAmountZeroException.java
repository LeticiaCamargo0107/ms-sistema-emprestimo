package com.example.InstalllmentSystem.core.exception.payment;

import org.apache.coyote.BadRequestException;

public class PaymentAmountZeroException extends BadRequestException {

    public PaymentAmountZeroException() {
        super("Payment must be greater than zero");
    }
}
