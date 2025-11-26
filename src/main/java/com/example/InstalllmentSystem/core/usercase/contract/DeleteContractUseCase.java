package com.example.InstalllmentSystem.core.usercase.contract;

import com.example.InstalllmentSystem.core.exception.contract.ContractNotFoundException;
import com.example.InstalllmentSystem.core.gateway.ContractGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteContractUseCase {

    private final ContractGateway contractGateway;

    public void execute(String id) throws ContractNotFoundException {

        if (!contractGateway.existById(id)) {
            log.error("Contract not found by id");
            throw new ContractNotFoundException(id);
        }
        contractGateway.deleteById(id);
    }
}

