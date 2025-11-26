package com.example.InstalllmentSystem.core.usercase.contract;

import com.example.InstalllmentSystem.core.domain.Contract;
import com.example.InstalllmentSystem.core.exception.contract.ContractNotFoundException;
import com.example.InstalllmentSystem.core.exception.contract.ContractRequestAmountZeroException;
import com.example.InstalllmentSystem.core.gateway.ContractGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.example.InstalllmentSystem.core.util.ContractUtils.getInstallmentAmount;
import static com.example.InstalllmentSystem.core.util.ContractUtils.getMultiply;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateContractUseCase {

    private final ContractGateway contractGateway;
    private final GetByIdContractUseCase getByIdContractUseCase;

    public Contract execute(String id, Contract contract) throws ContractRequestAmountZeroException, ContractNotFoundException {

        if (contract.getRequestedAmount().compareTo(BigDecimal.ZERO) <= 0) {
            log.error("Requested Amount must be greater than zero");
            throw new ContractRequestAmountZeroException();
        }

        var saved = getByIdContractUseCase.execute(id);

        var totalAmount = getMultiply(contract, contract.getMonthlyCetRate());
        var installmentAmount = getInstallmentAmount(contract);

        saved.setRequestedAmount(contract.getRequestedAmount());
        saved.setOperationPeriod(contract.getOperationPeriod());
        saved.setTotalAmount(totalAmount);
        saved.setInstallmentAmount(installmentAmount);

        return contractGateway.save(saved);
    }
}
