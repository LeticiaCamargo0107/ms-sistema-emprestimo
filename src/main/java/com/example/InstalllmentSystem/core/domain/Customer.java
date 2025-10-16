package com.example.InstalllmentSystem.core.domain;
import lombok.Getter;
import java.time.LocalDate;

@Getter
public class Customer {
    private String name;
    private String id;
    private String Document;
    private LocalDate birthDate;
    private CustomerStatus status;
}