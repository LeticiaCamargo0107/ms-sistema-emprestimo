package com.example.InstallmentSystem.dataprovider.entity;

import com.example.InstallmentSystem.core.domain.enumeration.ContractStatus;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@Document(collection = "contract")
public class ContractEntity {

    @MongoId
    private String id;

    private Integer operationPeriod;

    private BigDecimal requestedAmount;

    private CustomerEntity customer;

    private BigDecimal monthlyCetRate;

    private BigDecimal totalAmount;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;

    private Integer daysOverDue;

    private ContractStatus status;

    private BigDecimal remainingAmount;

    List<InstallmentEntity> installments;

}