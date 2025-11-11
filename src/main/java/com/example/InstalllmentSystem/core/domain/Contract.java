package com.example.InstalllmentSystem.core.domain;

import com.example.InstalllmentSystem.core.domain.enumeration.ContractStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Contract {

    private String id;

    @Nonnull
    @Min(2)
    private Integer operationPeriod;

    @Nonnull
    @Min(100)
    private BigDecimal requestedAmount;

    @Nonnull
    private Customer customer;

    private BigDecimal monthlyCetRate;

    private BigDecimal totalAmount;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer daysOverDue;

    private ContractStatus status;

    private BigDecimal remainingAmount;

}