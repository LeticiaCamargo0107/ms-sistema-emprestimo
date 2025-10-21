package com.example.InstalllmentSystem.core.domain;

import com.example.InstalllmentSystem.core.domain.enumeration.InstallmentStatus;
import lombok.Builder;
import lombok.Getter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class Installment {

    private String id;
    private LocalDateTime dueDate;
    private BigDecimal amount;
    private InstallmentStatus status;

}
