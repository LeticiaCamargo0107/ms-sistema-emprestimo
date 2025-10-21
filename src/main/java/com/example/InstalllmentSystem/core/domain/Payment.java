package com.example.InstalllmentSystem.core.domain;

import com.example.InstalllmentSystem.core.domain.enumeration.PaymentMethod;
import com.example.InstalllmentSystem.core.domain.enumeration.PaymentStatus;
import lombok.Builder;
import lombok.Getter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class Payment {

    private String id;
    private PaymentMethod payMethod;
    private BigDecimal amount;
    private LocalDateTime paidAt;
    private PaymentStatus status;
}
