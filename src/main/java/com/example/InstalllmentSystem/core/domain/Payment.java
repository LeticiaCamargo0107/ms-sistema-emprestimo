package com.example.InstalllmentSystem.core.domain;

import com.example.InstalllmentSystem.core.domain.enumeration.PaymentMethod;
import com.example.InstalllmentSystem.core.domain.enumeration.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Payment {

    private String id;
    private PaymentMethod payMethod;
    private BigDecimal amount;
    private LocalDateTime paidAt;
    private PaymentStatus status;
}
