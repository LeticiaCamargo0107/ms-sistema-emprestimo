package com.example.InstalllmentSystem.entrypoint.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;


@RestController
@RequestMapping("/v1/payment")
public class PaymentController {

    @GetMapping("/amount/{amount}")
    public BigDecimal getByAmount (BigDecimal amount){
        return amount;
    }
}
