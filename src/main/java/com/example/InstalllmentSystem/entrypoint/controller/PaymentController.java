package com.example.InstalllmentSystem.entrypoint.controller;

import com.example.InstalllmentSystem.core.domain.Payment;
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
import java.util.List;


@RestController
@RequestMapping("/v1/payments")
public class PaymentController {

    @GetMapping("/{amount}")
    public BigDecimal getByAmount(@PathVariable BigDecimal amount) {
        System.out.printf("Get for amount: R$ %.2f\n", amount);
        return amount;
    }

    @GetMapping
    public List<Payment> findAllPayments() {
        System.out.println("Find all of payment");
        return List.of();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Payment createPayment() {
        System.out.println("Creating payment");
        return null;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public Payment deleteById(@PathVariable String id) {
        System.out.printf("Delete by id: %s\n", id);
        return null;
    }

    @PutMapping("/{id}/{amount}")
    public Payment updateById(@PathVariable String id, @PathVariable BigDecimal amount) {
        System.out.printf("Update for id %s, change amount: R$ %.2f\n", id, amount);
        return null;
    }

}