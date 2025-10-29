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
    public Contract getById(@RequestBody String id) {
        var contract1 = Contract.builder()
                .id("1234")
                .status(ContractStatus.ACTIVE)
                .daysOverDue(49)
                .monthlyCetRate(BigDecimal.valueOf(4.8))
                .build();

        System.out.printf("Get for id: %s", id);

        if(contract1.getId().equals(id)) {
            return contract1;
        }
        else {
            return null;
        }
    }

    @GetMapping
    public List<Contract> findAllContracts() {
        System.out.println("Find all of Contract");

        var contract1 = Contract.builder()
                .id("1234")
                .startDate(LocalDate.of(2020, 10,20))
                .totalAmount(BigDecimal.valueOf(10000))
                .daysOverDue(19)
                .build();

        var contract2 = Contract.builder()
                .id("abc12567")
                .startDate(LocalDate.of(1980, 9,14))
                .requestedAmount(BigDecimal.valueOf(2340))
                .daysOverDue(0)
                .status(ContractStatus.ACTIVE)
                .build();

        var contract3 = Contract.builder()
                .id("404fnf")
                .startDate(LocalDate.of(1999,12,30))
                .totalAmount(BigDecimal.valueOf(100000))
                .daysOverDue(0)
                .endDate(LocalDate.now())
                .status(ContractStatus.LIQUIDATED)
                .build();

        return List.of(contract1, contract2, contract3);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Contract createContracts(@RequestBody Contract contract) {
        var contract1 = Contract.builder()
                .status(ContractStatus.ACTIVE)
                .startDate(LocalDate.now())
                .monthlyCetRate(contract.getMonthlyCetRate())
                .requestedAmount(contract.getRequestedAmount())
                .build();
        System.out.println("Creating contract");
        return contract;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public Contract deleteById(@PathVariable String id) {
        System.out.printf("Delete by id: %s", id);
        return null;
    }

    @PutMapping("/{before-amount}/{after-amount}")
    public Contract updateAmount(@PathVariable ("before-amount") BigDecimal before, @PathVariable ("after-amount") BigDecimal after) {
        System.out.printf("Update R$ %.2f to R$ %.2f", before, after);
        return null;
    }


}