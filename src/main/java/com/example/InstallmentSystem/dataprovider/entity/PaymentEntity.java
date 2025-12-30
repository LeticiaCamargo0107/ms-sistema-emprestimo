package com.example.InstallmentSystem.dataprovider.entity;

import com.example.InstallmentSystem.core.domain.enumeration.PaymentMethod;
import com.example.InstallmentSystem.core.domain.enumeration.PaymentStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@Document(collection = "payment")
public class PaymentEntity {

    @MongoId
    private String id;

    private PaymentMethod payMethod;

    private BigDecimal amount;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime paidAt;

    private PaymentStatus status;

    @Setter
    private String notify;
}
