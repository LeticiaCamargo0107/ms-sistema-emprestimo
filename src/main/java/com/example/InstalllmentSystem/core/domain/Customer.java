package com.example.InstalllmentSystem.core.domain;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDate;

@Builder
@Getter
public class Customer {

    private String name;
    private String id;
    private String Document;
    private LocalDate birthDate;
    private CustomerStatus status;
}