package com.example.InstalllmentSystem.core.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor
public class Customer {

    private String name;
    private String id;
    private String document;
    private LocalDate birthDate;
    private CustomerStatus status;

    public Customer (String name, String id, String document, LocalDate birthDate, CustomerStatus status) {
        this.name = name;
        this.id = id;
        this.document = document;
        this.birthDate = birthDate;
        this.status = status;
    }
}