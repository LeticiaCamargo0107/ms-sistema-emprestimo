package com.example.InstalllmentSystem.entrypoint.controller;

import com.example.InstalllmentSystem.core.domain.Contract;
import com.example.InstalllmentSystem.core.domain.Customer;
import com.example.InstalllmentSystem.core.domain.Installment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/v1/contracts")
public class ContractController {

    @GetMapping("/{id}")
    public String getByName(@PathVariable String id) {
        System.out.printf("Get for id: %s", id);
        return id;
    }

    @GetMapping
    public List<Contract> findAll() {
        System.out.println("Find all of Contract");
        return List.of();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Contract createContract() {
        System.out.println("Creating contract");
        return null;
    }

    @DeleteMapping("/{id}")
    public Contract deleteById(@PathVariable String id){
        System.out.printf("Delete by id: %s", id);
        return null;
    }

    @PutMapping("/{before-amount}/{after-amount}")
    public Contract updateAmount(@PathVariable ("before-amount") BigDecimal before, @PathVariable ("after-amount") BigDecimal after) {
        System.out.printf("Update R$ %.2f to R$ %.2f", before, after);
        return null;
    }


}