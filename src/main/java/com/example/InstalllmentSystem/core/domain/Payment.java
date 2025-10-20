package com.example.InstalllmentSystem.core.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
public class Payment {

    private String id;
    private Installment installment;
    private PaymentMethod method;
    private BigDecimal amountPaid;
    private BigDecimal remaindingAmount;
    private LocalDate datePayment;

    public Payment (String id, Installment installment, PaymentMethod method, BigDecimal amountPaid, BigDecimal remainingAmount, LocalDate datePayment) {
        this.id = id;
        this.installment = installment;
        this.method = method;
        this.amountPaid = amountPaid;
        this.remaindingAmount = remainingAmount;
        this.datePayment = LocalDate.now();
    }
}
