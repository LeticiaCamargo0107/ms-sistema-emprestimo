package com.example.InstalllmentSystem.entrypoint.controller;

import com.example.InstalllmentSystem.core.domain.Contract;
import com.example.InstalllmentSystem.core.domain.enumeration.ContractStatus;
import com.example.InstalllmentSystem.core.usercase.contract.CreateContractUseCase;
import com.example.InstalllmentSystem.core.usercase.contract.DeleteContractUseCase;
import com.example.InstalllmentSystem.core.usercase.contract.GetByIdContractUseCase;
import com.example.InstalllmentSystem.core.usercase.contract.UpdateContractUseCase;
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


@RestController
@RequestMapping("/v1/contracts")
@RequiredArgsConstructor
public class ContractController {

    final private CreateContractUseCase createContractUseCase;
    final private DeleteContractUseCase deleteContractUseCase;
    final private UpdateContractUseCase updateContractUseCase;
    final private GetByIdContractUseCase getByIdContractUseCase;
    final private GetByIdContractUseCase findAllContractUseCase;


    @GetMapping("/{id}")
    public Contract getById(@PathVariable String id) {
        return getByIdContractUseCase.execute(id);
    }

    @GetMapping
    public Contract findAllContracts() {
        return findAllContractUseCase.execute("ddd");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Contract createContracts(@RequestBody Contract contract) {
        return createContractUseCase.execute
                (contract.getId(), contract.getCustomer(), contract.getRequestedAmount(), contract.getOperationPeriod());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public Contract deleteById(@PathVariable String id) {
        return deleteContractUseCase.execute(id);
    }


    @PutMapping
    public Contract updateAmount(@RequestBody Contract contract) {


    }
}