package com.example.InstalllmentSystem.core.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Payment {

    private String id;
    private Installment installment;
    private PaymentMethod method;
    private BigDecimal amountPaid;
    private BigDecimal remaindingAmount;
    private LocalDate datePayment;
}
