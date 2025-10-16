package com.example.InstalllmentSystem.core.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Installment {
    private String id;
    private LocalDate dataVencimento;
    private BigDecimal totalAmount;
    private Contract contract;
    private Double juro;
    private InstallmentStatus  status;

}
