package com.example.InstalllmentSystem.core.domain;

import com.example.InstalllmentSystem.core.domain.enumeration.CustomerStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer {

    private String id;

    @Nonnull
    private String name;

    @Nonnull
    @Size(min = 11, max = 11)
    private String document;

    @Nonnull
    private LocalDate birthDate;

    private CustomerStatus status;

}