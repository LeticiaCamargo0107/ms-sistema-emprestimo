package com.example.InstalllmentSystem.entrypoint.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO {

    @NotNull(message = "Name can't be null")
    private String name;

    @NotNull(message = "Document can't be null")
    @Size(min = 11, max = 11)
    private String document;

    @NotNull(message = "Birthday date can't be null")
    private LocalDate birthDate;
}
