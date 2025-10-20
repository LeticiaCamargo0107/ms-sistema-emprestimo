package com.example.InstalllmentSystem.core.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
public class Installment {

    private String id;
    private LocalDate expirationDate;
    private BigDecimal installmentAmount;
    private Contract contract;
    private InstallmentStatus  status;

    public Installment (String id, LocalDate expirationDate, BigDecimal installmentAmount, Contract contract, InstallmentStatus status) {
        this.id = id;
        this.expirationDate = expirationDate;
        this.contract = contract;
        this.status = status;
    }
}
