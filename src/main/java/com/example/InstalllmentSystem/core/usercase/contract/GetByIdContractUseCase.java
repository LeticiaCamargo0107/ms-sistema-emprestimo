package com.example.InstalllmentSystem.core.usercase.contract;

import com.example.InstalllmentSystem.core.domain.Contract;
import com.example.InstalllmentSystem.core.exception.contract.ContractNotFoundException;
import com.example.InstalllmentSystem.core.gateway.ContractGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetByIdContractUseCase {

    private final ContractGateway contractGateway;

    public Contract execute(String id) throws ContractNotFoundException {

        if (contractGateway.existById(id)) {

            return contractGateway.findById(id);
        }
        throw new ContractNotFoundException(id);
    }
}

