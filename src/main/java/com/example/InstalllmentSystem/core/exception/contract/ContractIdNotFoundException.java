package com.example.InstalllmentSystem.core.exception.contract;

import org.apache.coyote.BadRequestException;

public class ContractIdNotFoundException extends BadRequestException {

    public ContractIdNotFoundException(String id) {
        super("Can't found contract by id " + id);
    }
}
