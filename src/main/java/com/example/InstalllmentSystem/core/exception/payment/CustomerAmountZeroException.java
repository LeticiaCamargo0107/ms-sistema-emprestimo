package com.example.InstalllmentSystem.core.exception.payment;

import org.apache.coyote.BadRequestException;

public class CustomerAmountZeroException extends BadRequestException {

    public CustomerAmountZeroException() {
        super("Payment must be granter than zero");
    }
}
