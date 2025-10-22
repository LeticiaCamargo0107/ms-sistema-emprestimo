package com.example.InstalllmentSystem.dataprovider.entity;

import com.example.InstalllmentSystem.core.domain.enumeration.CustomerStatus;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;

@Builder
@Getter
@Document (collection = "customer")
public class CustomerEntity {

    @MongoId
    private String id;
    private String name;
    private String document;
    private LocalDate birthDate;
    private CustomerStatus status;

}