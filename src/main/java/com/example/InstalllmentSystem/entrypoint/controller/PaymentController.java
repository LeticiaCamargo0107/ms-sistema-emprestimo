package com.example.InstalllmentSystem.entrypoint.controller;

import com.example.InstalllmentSystem.core.domain.Payment;
import com.example.InstalllmentSystem.core.exception.payment.PaymentAmountZeroException;
import com.example.InstalllmentSystem.core.exception.payment.PaymentNotFoundException;
import com.example.InstalllmentSystem.core.usercase.payment.CreatePaymentUseCase;
import com.example.InstalllmentSystem.core.usercase.payment.DeleteByIdPaymentUseCase;
import com.example.InstalllmentSystem.core.usercase.payment.FindAllPaymentUseCase;
import com.example.InstalllmentSystem.core.usercase.payment.GetByIdPaymentUseCase;
import com.example.InstalllmentSystem.core.usercase.payment.UpdatePaymentUseCase;
import com.example.InstalllmentSystem.entrypoint.dto.PaymentDTO;
import com.example.InstalllmentSystem.entrypoint.mapper.PaymentMapper;
import com.example.InstalllmentSystem.entrypoint.swagger.PaymentControllerAPI;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/payments")
public class PaymentController implements PaymentControllerAPI {

    private final CreatePaymentUseCase createPaymentUseCase;
    private final DeleteByIdPaymentUseCase deleteByIdPaymentUseCase;
    private final UpdatePaymentUseCase updatePaymentUseCase;
    private final GetByIdPaymentUseCase getByIdPaymentUseCase;
    private final FindAllPaymentUseCase findAllPaymentUseCase;
    private final PaymentMapper paymentMapper;

    @GetMapping("/{id}")
    public Payment getById(@PathVariable String id) throws PaymentNotFoundException {

        return getByIdPaymentUseCase.execute(id);
    }

    @GetMapping
    public Page<Payment> findAll(@PageableDefault Pageable pageable) {

        return findAllPaymentUseCase.execute(pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Payment create(@RequestBody @Valid PaymentDTO paymentDTO) throws PaymentAmountZeroException {

        var payment = paymentMapper.toDomain(paymentDTO);
        return createPaymentUseCase.execute(payment);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) throws PaymentNotFoundException {

        deleteByIdPaymentUseCase.execute(id);
    }

    @PutMapping("/{id}")
    public Payment update(@PathVariable String id, @RequestBody @Valid PaymentDTO paymentDTO) throws PaymentNotFoundException, PaymentAmountZeroException {

        var payment = paymentMapper.toDomain(paymentDTO);
        return updatePaymentUseCase.execute(id, payment);
    }

}