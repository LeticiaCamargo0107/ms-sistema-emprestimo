package com.example.InstalllmentSystem.entrypoint.DTOs;

import com.example.InstalllmentSystem.core.domain.enumeration.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nonnull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentDTO {

    @Nonnull
    private PaymentMethod payMethod;

    @Nonnull
    private BigDecimal amount;
}