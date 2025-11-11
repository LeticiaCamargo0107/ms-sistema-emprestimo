package com.example.InstalllmentSystem.core.domain;

import com.example.InstalllmentSystem.core.domain.enumeration.PaymentMethod;
import com.example.InstalllmentSystem.core.domain.enumeration.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nonnull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Payment {

    private String id;

    @Nonnull
    private PaymentMethod payMethod;

    @Nonnull
    private BigDecimal amount;

    private LocalDateTime paidAt;

    private PaymentStatus status;
}
