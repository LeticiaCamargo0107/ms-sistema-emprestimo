package com.example.InstalllmentSystem.core.exception.contract;

import org.apache.coyote.BadRequestException;

public class ContractRequestAmountZeroException extends BadRequestException {

    public ContractRequestAmountZeroException () {
        super("RequestedAmount must be greater than zero");
    }
}
