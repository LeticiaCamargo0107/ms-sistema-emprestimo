package com.example.InstalllmentSystem.core.exception.contract;

import org.apache.coyote.BadRequestException;

public class ContractCustomerNullException extends BadRequestException {

    public ContractCustomerNullException() {
        super("Can't create Contract without a Customer (customer null in createContract");
    }
}
