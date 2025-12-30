package com.example.InstallmentSystem.core.domain;

import com.example.InstallmentSystem.core.domain.enumeration.CustomerStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
public class Customer {

    private String id;

    private String name;

    private String document;

    private LocalDate birthDate;

    private CustomerStatus status;

    private String zipcode;
}