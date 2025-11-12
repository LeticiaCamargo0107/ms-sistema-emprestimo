package com.example.InstalllmentSystem.core.domain;

import com.example.InstalllmentSystem.core.domain.enumeration.ContractStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nonnull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public class Contract {

    private String id;

    private Integer operationPeriod;

    private BigDecimal requestedAmount;

    private Customer customer;

    private BigDecimal monthlyCetRate;

    private BigDecimal totalAmount;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer daysOverDue;

    private ContractStatus status;

    private BigDecimal remainingAmount;

}