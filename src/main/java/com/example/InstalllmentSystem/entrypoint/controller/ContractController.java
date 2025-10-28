package com.example.InstalllmentSystem.entrypoint.controller;

import com.example.InstalllmentSystem.core.domain.Contract;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
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