package com.example.InstalllmentSystem.core.domain;

import com.example.InstalllmentSystem.core.domain.enumeration.ContractStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.swing.plaf.basic.BasicIconFactory;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
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

    private BigDecimal installmentAmount;
}