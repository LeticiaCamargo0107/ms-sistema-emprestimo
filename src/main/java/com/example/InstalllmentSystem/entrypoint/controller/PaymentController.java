package com.example.InstalllmentSystem.entrypoint.controller;

import com.example.InstalllmentSystem.core.domain.Payment;
import com.example.InstalllmentSystem.core.usercase.payment.CreatePaymentUseCase;
import com.example.InstalllmentSystem.core.usercase.payment.DeleteByIdPaymentUseCase;
import com.example.InstalllmentSystem.core.usercase.payment.FindAllPaymentUseCase;
import com.example.InstalllmentSystem.core.usercase.payment.GetByAmountPaymentUseCase;
import com.example.InstalllmentSystem.core.usercase.payment.UpdatePaymentUseCase;
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
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/payments")
public class PaymentController {

    final private CreatePaymentUseCase createPaymentUseCase;
    final private DeleteByIdPaymentUseCase deleteByIdPaymentUseCase;
    final private UpdatePaymentUseCase updatePaymentUseCase;
    final private GetByAmountPaymentUseCase getByAmountPaymentUseCase;
    final private FindAllPaymentUseCase findAllPaymentUseCase;


    @GetMapping("/{amount}")
    public Payment getByAmount(@PathVariable BigDecimal amount) {

        return getByAmountPaymentUseCase.execute(amount);
    }

    @GetMapping
    public List<Payment> findAll() {

        return findAllPaymentUseCase.execute();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Payment create(@RequestBody Payment payment) {

        return createPaymentUseCase.execute(payment);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public Payment deleteById(@PathVariable String id) {

        return deleteByIdPaymentUseCase.execute(id);
    }

    @PutMapping
    public Payment update(@RequestBody Payment payment) {

        return updatePaymentUseCase.execute(payment);
    }

}