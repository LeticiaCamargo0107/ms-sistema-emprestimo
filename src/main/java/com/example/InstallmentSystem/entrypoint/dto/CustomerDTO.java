package com.example.InstallmentSystem.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO {

    @Schema(description = "Name of customer", example = "Maria")
    @NotNull(message = "Name can't be null")
    private String name;

    @Schema(description = "Document of customer", example = "CPF: 000.000.000-01")
    @NotNull(message = "Document can't be null")
    @Size(min = 11, max = 11)
    private String document;

    @Schema(description = "Birthday date of customer")
    @NotNull(message = "Birthday date can't be null")
    private LocalDate birthDate;

    @Schema(description = "Zipcode of address")
    @NotNull(message = "Zipcode must not be null")
    private String zipcode;
}
