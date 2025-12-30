package com.example.InstallmentSystem.core.usercase.contract;

import com.example.InstallmentSystem.core.domain.Contract;
import com.example.InstallmentSystem.core.exception.contract.ContractNotFoundException;
import com.example.InstallmentSystem.core.gateway.GenericGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class GetByIdContractUseCase {

    private final GenericGateway<Contract> contractGateway;

    public Contract execute(String id) throws ContractNotFoundException {

        if (!contractGateway.existById(id)) {
            log.error("Contract not found by id");
            throw new ContractNotFoundException(id);
        }
        return contractGateway.findById(id);

    }
}

