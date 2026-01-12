package com.example.InstallmentSystem.entrypoint.controller;

import com.example.InstallmentSystem.core.domain.Payment;
import com.example.InstallmentSystem.core.domain.ProcessPaymentFacade;
import com.example.InstallmentSystem.core.exception.contract.ContractNotFoundException;
import com.example.InstallmentSystem.core.exception.payment.PaymentAmountZeroException;
import com.example.InstallmentSystem.core.exception.payment.PaymentMethodNotFoundException;
import com.example.InstallmentSystem.core.exception.payment.PaymentNotFoundException;
import com.example.InstallmentSystem.core.usercase.payment.CreatePaymentUseCase;
import com.example.InstallmentSystem.core.usercase.payment.DeleteByIdPaymentUseCase;
import com.example.InstallmentSystem.core.usercase.payment.FindAllPaymentUseCase;
import com.example.InstallmentSystem.core.usercase.payment.GetByIdPaymentUseCase;
import com.example.InstallmentSystem.core.usercase.payment.UpdatePaymentUseCase;
import com.example.InstallmentSystem.entrypoint.dto.PaymentDTO;
import com.example.InstallmentSystem.entrypoint.mapper.PaymentMapper;
import com.example.InstallmentSystem.entrypoint.swagger.PaymentControllerAPI;
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
    private final ProcessPaymentFacade processPaymentFacade;
    private final PaymentMapper paymentMapper;

    @GetMapping("/{id}")
    public Payment getById(@PathVariable String id) throws PaymentNotFoundException, ContractNotFoundException {

        return getByIdPaymentUseCase.execute(id);
    }

    @GetMapping
    public Page<Payment> findAll(@PageableDefault Pageable pageable) {

        return findAllPaymentUseCase.execute(pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Payment create(@RequestBody @Valid PaymentDTO paymentDTO) throws PaymentAmountZeroException, PaymentMethodNotFoundException {

        var payment = paymentMapper.toDomain(paymentDTO);
        return processPaymentFacade.orchestrator(payment);
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