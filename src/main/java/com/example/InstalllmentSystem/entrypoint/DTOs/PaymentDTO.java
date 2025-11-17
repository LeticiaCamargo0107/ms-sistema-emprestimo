package com.example.InstalllmentSystem.entrypoint.DTOs;

import com.example.InstalllmentSystem.core.domain.enumeration.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentDTO {

    @NotNull(message = "Payment Method can't be null")
    private PaymentMethod payMethod;

    @NotNull(message = "Amount can't be null")
    private BigDecimal amount;
}