package com.example.InstalllmentSystem.entrypoint.controller;

import com.example.InstalllmentSystem.core.domain.Payment;
import com.example.InstalllmentSystem.core.domain.enumeration.PaymentMethod;
import com.example.InstalllmentSystem.core.domain.enumeration.PaymentStatus;
import com.example.InstalllmentSystem.core.usercase.contract.GetByIdContractUseCase;
import com.example.InstalllmentSystem.core.usercase.contract.UpdateContractUseCase;
import com.example.InstalllmentSystem.core.usercase.payment.CreatePaymentUseCase;
import com.example.InstalllmentSystem.core.usercase.payment.DeletePaymentUseCase;
import lombok.RequiredArgsConstructor;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/payments")
public class PaymentController {

    final private CreatePaymentUseCase createPaymentUseCase;
    final private DeletePaymentUseCase deletePaymentUseCase;
    final private UpdateContractUseCase updateContractUseCasePaymentUseCase;
    final private GetByIdContractUseCase findPaymentUseCase;


    @GetMapping("/{amount}")
    public Payment getByAmount(@PathVariable BigDecimal amount) {

        var payment1 = Payment.builder()
                .id("fcdgvhkr333")
                .amount(BigDecimal.valueOf(19074))
                .paidAt(LocalDateTime.now())
                .payMethod(PaymentMethod.PIX)
                .build();

        if (payment1.getAmount().equals(amount)) {
            System.out.printf("Get for amount: R$ %.2f\n", amount);
            return payment1;
        }
        return null;
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
    public Payment createPayment(@RequestBody Payment payment) {

        var payment1 = Payment.builder()
                .id("7yu80fb377szx129")
                .status(PaymentStatus.EXECUTED)
                .paidAt(LocalDateTime.now())
                .amount(payment.getAmount())
                .build();

        System.out.println("Creating payment");
        return payment1;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public Payment deleteById(@PathVariable String id) {

        var payment1 = Payment.builder()
                .id("1234")
                .paidAt(LocalDateTime.now())
                .amount(BigDecimal.valueOf(212.89))
                .build();

        if (payment1.getId().equals(id)) {
            System.out.printf("Delete by id: %s\n", id);
            return payment1;
        }

        return null;
    }

    @PutMapping
    public Payment updateById(@RequestBody Payment payment) {

        var payment1 = Payment.builder()
                .id(payment.getId())
                .status(PaymentStatus.EXECUTED)
                .paidAt(LocalDateTime.now())
                .amount(payment.getAmount())
                .build();


        System.out.printf("Update for id %s, change amount: R$ %.2f\n", payment.getId(), payment.getAmount());
        return payment1;
    }

}