package com.example.InstalllmentSystem.dataprovider.entity;

import com.example.InstalllmentSystem.core.domain.enumeration.InstallmentStatus;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@Document (collection = "installment")
public class InstallmentEntity {

    @MongoId
    private String id;
    private LocalDateTime dueDate;
    private BigDecimal amount;
    private InstallmentStatus status;

}
