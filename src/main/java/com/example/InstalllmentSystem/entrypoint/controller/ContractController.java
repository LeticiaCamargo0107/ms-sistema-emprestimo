package com.example.InstalllmentSystem.entrypoint.controller;

import com.example.InstalllmentSystem.core.domain.Contract;
import com.example.InstalllmentSystem.core.exception.contract.ContractNotFoundException;
import com.example.InstalllmentSystem.core.exception.contract.ContractPeriodZeroException;
import com.example.InstalllmentSystem.core.exception.contract.ContractRequestAmountZeroException;
import com.example.InstalllmentSystem.core.usercase.contract.CreateContractUseCase;
import com.example.InstalllmentSystem.core.usercase.contract.DeleteContractUseCase;
import com.example.InstalllmentSystem.core.usercase.contract.FindAllContractUseCase;
import com.example.InstalllmentSystem.core.usercase.contract.GetByIdContractUseCase;
import com.example.InstalllmentSystem.core.usercase.contract.UpdateContractUseCase;
import com.example.InstalllmentSystem.entrypoint.DTOs.ContractDTO;
import com.example.InstalllmentSystem.entrypoint.mapper.ContractMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final ContractMapper contractMapper;

    @GetMapping("/{id}")
    public Contract getById(@PathVariable String id) throws ContractNotFoundException {

        return getByIdContractUseCase.execute(id);
    }

    @GetMapping
    public Page<Contract> findAll(Pageable pageable) {

        return findAllContractUseCase.execute(pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Contract create(@RequestBody @Valid ContractDTO contractDTO) throws ContractPeriodZeroException, ContractRequestAmountZeroException {
        var contract = contractMapper.toDomain(contractDTO);
        return createContractUseCase.execute(contract);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) throws ContractNotFoundException {

        deleteContractUseCase.execute(id);
    }

    @PutMapping("/{id}")
    public Contract update(@PathVariable String id, @RequestBody ContractDTO contractDTO) throws ContractRequestAmountZeroException, ContractNotFoundException {

        var contract = contractMapper.toDomain(contractDTO);
        return updateContractUseCase.execute(id, contract);
    }
}