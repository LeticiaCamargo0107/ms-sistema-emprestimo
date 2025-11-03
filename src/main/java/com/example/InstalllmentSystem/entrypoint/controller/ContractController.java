package com.example.InstalllmentSystem.entrypoint.controller;

import com.example.InstalllmentSystem.core.domain.Contract;
import com.example.InstalllmentSystem.core.domain.enumeration.ContractStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/v1/contracts")
public class ContractController {

    @GetMapping("/{id}")
    public Contract getById(@PathVariable String id) {

        var contract1 = Contract.builder()
                .id("1234")
                .status(ContractStatus.ACTIVE)
                .daysOverDue(49)
                .monthlyCetRate(BigDecimal.valueOf(4.8))
                .build();

        if (contract1.getId().equals(id)) {
            System.out.printf("Get for id: %s\n", id);
            return contract1;
        }
        return null;
    }

    @GetMapping
    public List<Contract> findAll() {
        System.out.println("Find all of Contract");

        var contract1 = Contract.builder()
                .id("1234")
                .startDate(LocalDate.of(2020, 10, 20))
                .totalAmount(BigDecimal.valueOf(10000))
                .daysOverDue(19)
                .build();

        var contract2 = Contract.builder()
                .id("abc12567")
                .startDate(LocalDate.of(1980, 9, 14))
                .requestedAmount(BigDecimal.valueOf(2340))
                .daysOverDue(0)
                .status(ContractStatus.ACTIVE)
                .build();

        var contract3 = Contract.builder()
                .id("404fnf")
                .startDate(LocalDate.of(1999, 12, 30))
                .totalAmount(BigDecimal.valueOf(100000))
                .daysOverDue(0)
                .endDate(LocalDate.now())
                .status(ContractStatus.LIQUIDATED)
                .build();

        return List.of(contract1, contract2, contract3);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Contract create(@RequestBody Contract contract) {
        var contract1 = Contract.builder()
                .status(ContractStatus.ACTIVE)
                .startDate(LocalDate.now())
                .monthlyCetRate(contract.getMonthlyCetRate())
                .requestedAmount(contract.getRequestedAmount())
                .build();

        System.out.println("Creating contract");
        return contract1;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public Contract deleteById(@PathVariable String id) {

        var contract1 = Contract.builder()
                .id("wuwuwuwuw")
                .endDate(LocalDate.now())
                .requestedAmount(BigDecimal.valueOf(2340))
                .totalAmount(BigDecimal.valueOf(3000))
                .daysOverDue(0)
                .status(ContractStatus.ACTIVE)
                .build();

        if (contract1.getId().equals(id)) {
            System.out.printf("Delete by id: %s\n", id);
            return contract1;
        }

        return null;
    }

    @PutMapping
    public Contract update(@RequestBody Contract contract) {

        var contract1 = Contract.builder()
                .id("bvinvdsldvnhg")
                .startDate(LocalDate.of(1500, 12, 3))
                .requestedAmount(BigDecimal.valueOf(0000.0099999))
                .daysOverDue(87)
                .status(ContractStatus.ACTIVE)
                .totalAmount(contract.getTotalAmount())
                .customer(contract.getCustomer())
                .build();

        System.out.printf("Update amount to R$ %.2f\n", contract1.getTotalAmount());
        return null;
    }
}