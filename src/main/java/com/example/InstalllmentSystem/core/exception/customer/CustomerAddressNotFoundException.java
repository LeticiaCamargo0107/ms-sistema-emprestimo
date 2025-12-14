package com.example.InstalllmentSystem.core.exception.customer;

import org.apache.coyote.BadRequestException;

public class CustomerAddressNotFoundException extends BadRequestException {

    public CustomerAddressNotFoundException(String zipcode) {
        super("Address not found by zip code" + zipcode);
    }
}
