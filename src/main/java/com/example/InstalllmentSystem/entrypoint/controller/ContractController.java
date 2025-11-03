package com.example.InstalllmentSystem.entrypoint.controller;

import com.example.InstalllmentSystem.core.domain.Contract;
import com.example.InstalllmentSystem.core.domain.enumeration.ContractStatus;
import com.example.InstalllmentSystem.core.usercase.contract.CreateContractUseCase;
import com.example.InstalllmentSystem.core.usercase.contract.DeleteContractUseCase;
import com.example.InstalllmentSystem.core.usercase.contract.FindAllContractUseCase;
import com.example.InstalllmentSystem.core.usercase.contract.GetByIdContractUseCase;
import com.example.InstalllmentSystem.core.usercase.contract.UpdateContractUseCase;
import com.example.InstalllmentSystem.core.usercase.customer.CreateCustomerUseCase;
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
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/contracts")
public class ContractController {

    private final GetByIdContractUseCase getByIdContractUseCase;
    private final FindAllContractUseCase findAllContractUseCase;
    private final CreateContractUseCase createContractUseCase;
    private final DeleteContractUseCase deleteContractUseCase;
    private final UpdateContractUseCase updateContractUseCase;

    @GetMapping("/{id}")
    public Contract getById(@PathVariable String id) {

        return getByIdContractUseCase.execute(id);
    }

    @GetMapping
    public List<Contract> findAll() {

        return findAllContractUseCase.execute();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Contract create(@RequestBody Contract contract) {

        return createContractUseCase.execute(contract);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {

        deleteContractUseCase.execute(id);
    }

    @PutMapping
    public Contract update(@RequestBody Contract contract) {

        return updateContractUseCase.execute(contract);
    }
}