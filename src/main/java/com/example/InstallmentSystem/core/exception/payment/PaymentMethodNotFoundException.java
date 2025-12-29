package com.example.InstallmentSystem.core.exception.payment;

import org.apache.coyote.BadRequestException;

public class PaymentMethodNotFoundException extends BadRequestException {
    public PaymentMethodNotFoundException () {
        super("Payment method not found");
    }
}
