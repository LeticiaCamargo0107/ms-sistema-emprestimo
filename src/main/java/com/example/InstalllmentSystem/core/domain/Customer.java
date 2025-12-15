package com.example.InstalllmentSystem.core.domain;

import com.example.InstalllmentSystem.core.domain.enumeration.CustomerStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class Customer {

    private String id;

    private String name;

    private String document;

    private LocalDate birthDate;

    private CustomerStatus status;

    private String zipcode;
}