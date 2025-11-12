package com.example.InstalllmentSystem.entrypoint.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nonnull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContractDTO {

        @Nonnull
        private Integer operationPeriod;

        @Nonnull
        private BigDecimal requestedAmount;

        @Nonnull
        private String idCustomer;
}
