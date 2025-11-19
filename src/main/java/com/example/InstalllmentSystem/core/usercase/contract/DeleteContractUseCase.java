package com.example.InstalllmentSystem.core.usercase.contract;

import com.example.InstalllmentSystem.core.exception.contract.ContractNotFoundException;
import com.example.InstalllmentSystem.core.gateway.ContractGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class DeleteContractUseCase {

    private final ContractGateway contractGateway;

    public void execute(String id) throws ContractNotFoundException {

        if (contractGateway.existById(id)) {
            System.out.printf("Delete by id: %s\n", id);
            return;
        }
        throw new ContractNotFoundException(id);
    }
}

