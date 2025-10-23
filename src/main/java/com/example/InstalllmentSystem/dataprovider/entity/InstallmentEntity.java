package com.example.InstalllmentSystem.dataprovider.entity;

import com.example.InstalllmentSystem.core.domain.enumeration.InstallmentStatus;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class InstallmentEntity {

    @MongoId
    private String id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dueDate;

    private BigDecimal amount;

    private InstallmentStatus status;

}
