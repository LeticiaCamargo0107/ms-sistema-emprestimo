package com.example.InstalllmentSystem.core.domain;

import com.example.InstalllmentSystem.core.domain.enumeration.CustomerStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Getter
public class Customer {

    private String name;
    private String id;
    private String document;
    private LocalDate birthDate;
    private CustomerStatus status;

}