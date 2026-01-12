package com.example.InstallmentSystem.core.usercase.contract;

import com.example.InstallmentSystem.core.domain.Contract;
import com.example.InstallmentSystem.core.exception.contract.ContractNotFoundException;
import com.example.InstallmentSystem.core.exception.contract.ContractPeriodZeroException;
import com.example.InstallmentSystem.core.exception.contract.ContractRequestAmountZeroException;
import com.example.InstallmentSystem.core.gateway.ContractGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.example.InstallmentSystem.core.util.ContractUtils.calculateTotalAmount;
import static com.example.InstallmentSystem.core.util.ContractUtils.getInstallmentAmount;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateContractUseCase {

    private final ContractGateway contractGateway;
    private final GetByIdContractUseCase getByIdContractUseCase;

    public Contract execute(String id, Contract contract) throws ContractRequestAmountZeroException, ContractNotFoundException, ContractPeriodZeroException {

        if (contract.getRequestedAmount() == null || contract.getRequestedAmount().compareTo(BigDecimal.ZERO) <= 0) {
            log.error("Requested Amount must be greater than zero");
            throw new ContractRequestAmountZeroException();
        }

        if (contract.getOperationPeriod() == null || contract.getOperationPeriod() <= 0) {
            log.error("Operation Period must be granter than zero");
            throw new ContractPeriodZeroException();
        }

        var saved = getByIdContractUseCase.execute(id);

        var totalAmount = calculateTotalAmount(contract, saved.getMonthlyCetRate());
        var installmentAmount = getInstallmentAmount(contract);

        saved.setRequestedAmount(contract.getRequestedAmount());
        saved.setOperationPeriod(contract.getOperationPeriod());
        saved.setTotalAmount(totalAmount);
        saved.setInstallmentAmount(installmentAmount);

        return contractGateway.save(saved);
    }
}
