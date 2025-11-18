package com.example.InstalllmentSystem.core.exception.contract;

import org.apache.coyote.BadRequestException;

public class ContractPeriodZeroException extends BadRequestException {

    public ContractPeriodZeroException () {
        super("The loan period must be greater than 2.");
    }
}
