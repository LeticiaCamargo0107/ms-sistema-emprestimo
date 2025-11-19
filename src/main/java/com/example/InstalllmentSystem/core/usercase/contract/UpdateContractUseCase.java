package com.example.InstalllmentSystem.core.usercase.contract;

import com.example.InstalllmentSystem.core.domain.Contract;
import com.example.InstalllmentSystem.core.exception.contract.ContractNotFoundException;
import com.example.InstalllmentSystem.core.exception.contract.ContractRequestAmountZeroException;
import com.example.InstalllmentSystem.core.gateway.ContractGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class UpdateContractUseCase {

    private final ContractGateway contractGateway;

    public Contract execute(String id, Contract contract) throws ContractRequestAmountZeroException, ContractNotFoundException {

        if (contractGateway.existById(id)) {
            throw new ContractNotFoundException(contract.getId());
        }

        if (contract.getRequestedAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ContractRequestAmountZeroException();
        }

        Contract updateContract = contractGateway.findById(id);
        updateContract.setRequestedAmount(contract.getRequestedAmount());

        return contractGateway.update(updateContract);
    }
}
