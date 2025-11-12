package com.example.InstalllmentSystem.entrypoint.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO {

    @Nonnull
    private String name;

    @Nonnull
    @Size(min = 11, max = 11)
    private String document;

    @Nonnull
    private LocalDate birthDate;
}
