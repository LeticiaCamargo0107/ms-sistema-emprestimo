package com.example.InstalllmentSystem.core.domain;

import com.example.InstalllmentSystem.core.domain.enumeration.ContractStatus;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
public class Contract {

    private Integer operationPeriod;
    private BigDecimal requestedAmount;
    private String id;
    private Customer customer;
    private BigDecimal monthlyCetRate;
    private BigDecimal totalAmount;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer daysOverDuo;
    private ContractStatus status;
    private BigDecimal remainingAmount;

}