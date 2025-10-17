package com.example.InstalllmentSystem.core.domain;

import lombok.Builder;
import lombok.Getter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
public class Installment {

    private String id;
    private LocalDate expirationDate;
    private BigDecimal intallmentAmount;
    private Contract contract;
    private InstallmentStatus  status;
}
