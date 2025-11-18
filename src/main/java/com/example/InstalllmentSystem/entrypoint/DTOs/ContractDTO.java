package com.example.InstalllmentSystem.entrypoint.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContractDTO {

        @NotNull(message = "Operation Period can't be null")
        private Integer operationPeriod;

        @NotNull(message = "Requested Amount can't be null")
        private BigDecimal requestedAmount;

        @NotNull(message = "Id Customer can't be null")
        private String customerId;
}
