package com.example.InstalllmentSystem.core.exception.contract;

import org.apache.coyote.BadRequestException;

public class ContractNotFoundException extends BadRequestException {

    public ContractNotFoundException() {
        super("Contract not found");
    }
}
