package com.example.InstalllmentSystem.entrypoint.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContractDTO {

        @Schema(description = "Number of months to make loan payments", example = "5")
        @NotNull(message = "Operation Period can't be null")
        private Integer operationPeriod;

        @Schema(description = "value requested by the customer", example = "1500")
        @NotNull(message = "Requested Amount can't be null")
        private BigDecimal requestedAmount;

        @Schema(description = "ID related to the customer", example = "12345")
        @NotNull(message = "Id Customer can't be null")
        private String customerId;
}
