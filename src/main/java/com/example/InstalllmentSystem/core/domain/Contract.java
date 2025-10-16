package com.example.InstalllmentSystem.core.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Contract {
    private String id;
    private BigDecimal RequestedAmount;
    private Customer customer;
    private Integer OperationPeriod;
    private BigDecimal monthlyCetRate;
    private BigDecimal totalAmount;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer daysOverDuo;
    private ContractStatus status;
}