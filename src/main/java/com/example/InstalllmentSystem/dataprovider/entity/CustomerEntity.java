package com.example.InstalllmentSystem.dataprovider.entity;

import com.example.InstalllmentSystem.core.domain.enumeration.CustomerStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Builder
@Getter
@Document(collection = "customer")
public class CustomerEntity {

    @MongoId
    private String id;

    private String name;

    private String document;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;

    private CustomerStatus status;

    @Setter
    private CustomerAddress address;

    @Builder
    @Getter
    public static class CustomerAddress {

        private String zipcode;

        private String logradouro;

        private String complemento;

        private String unidade;

        private String bairro;

        private String localidade;

        private String uf;
    }
}
