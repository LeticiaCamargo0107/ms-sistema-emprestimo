package com.example.InstalllmentSystem.core.exception.payment;

import org.apache.coyote.BadRequestException;

public class PaymentNotFoundException extends BadRequestException {

    public PaymentNotFoundException() {
        super("Payment not found in listPayments");
    }
}
