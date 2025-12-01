package com.example.InstalllmentSystem.entrypoint.DTOs;

import com.example.InstalllmentSystem.core.domain.enumeration.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentDTO {

    @Schema(description = "Payment method used by customer", example = "PIX")
    @NotNull(message = "Payment Method can't be null")
    private PaymentMethod payMethod;

    @Schema(description = "Amount of payment", example = "2000")
    @NotNull(message = "Amount can't be null")
    private BigDecimal amount;
}