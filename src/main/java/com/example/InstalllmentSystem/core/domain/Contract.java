package com.example.InstalllmentSystem.core.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
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

    public Contract (String id, BigDecimal requestedAmount, Customer customer, Integer operationPeriod, BigDecimal monthlyCetRate, BigDecimal totalAmount, LocalDate startDate, LocalDate endDate, Integer daysOverDuo, ContractStatus status) {
        this.id = id;
        this.requestedAmount = requestedAmount;
        this.customer = customer;
        this.operationPeriod = operationPeriod;
        this.monthlyCetRate = monthlyCetRate;
        this.totalAmount = totalAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.daysOverDuo = daysOverDuo;
        this.status = status;
    }
}