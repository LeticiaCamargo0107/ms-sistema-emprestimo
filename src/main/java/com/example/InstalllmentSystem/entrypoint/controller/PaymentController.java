package com.example.InstalllmentSystem.entrypoint.controller;

import com.example.InstalllmentSystem.core.domain.Payment;
import com.example.InstalllmentSystem.core.domain.enumeration.PaymentMethod;
import com.example.InstalllmentSystem.core.domain.enumeration.PaymentStatus;
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
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/v1/payments")
public class PaymentController {

    @GetMapping()
    public Payment getByAmount(@RequestBody BigDecimal amount) {

        var payment1 = Payment.builder()
                .id("fcdgvhkr333")
                .amount(BigDecimal.valueOf(19074))
                .paidAt(LocalDateTime.now())
                .payMethod(PaymentMethod.PIX)
                .build();

        System.out.printf("Get for amount: R$ %.2f\n", amount);
        if (payment1.getAmount() == amount) {
            return payment1;
        }
        else {
            return null;
        }
    }

    @GetMapping
    public List<Payment> findAllPayments() {

        var payment1 = Payment.builder()
                .id("er5hcdtg4573kiukgt5")
                .amount(BigDecimal.valueOf(418418))
                .paidAt(LocalDateTime.now())
                .payMethod(PaymentMethod.PIX)
                .build();

        var payment2 = Payment.builder()
                .id("furururur3")
                .amount(BigDecimal.valueOf(1000000000))
                .paidAt(LocalDateTime.now())
                .payMethod(PaymentMethod.CREDIT_CARD)
                .build();

        var payment3 = Payment.builder()
                .id("43jbhvl230gn")
                .amount(BigDecimal.valueOf(434))
                .paidAt(LocalDateTime.now())
                .payMethod(PaymentMethod.SLIP)
                .build();

        System.out.println("Find all of payment");
        return List.of(payment1, payment2, payment3);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Payment createPayment(@RequestBody BigDecimal amount) {

        var payment1 = Payment.builder()
                .id("7yu80fb377szx129")
                .status(PaymentStatus.EXECUTED)
                .paidAt(LocalDateTime.now())
                .amount(amount)
                .build();

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