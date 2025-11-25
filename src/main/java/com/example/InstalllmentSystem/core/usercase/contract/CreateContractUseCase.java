package com.example.InstalllmentSystem.core.usercase.contract;

import com.example.InstalllmentSystem.core.domain.Contract;
import com.example.InstalllmentSystem.core.domain.enumeration.ContractStatus;
import com.example.InstalllmentSystem.core.exception.contract.ContractPeriodZeroException;
import com.example.InstalllmentSystem.core.exception.contract.ContractRequestAmountZeroException;
import com.example.InstalllmentSystem.core.gateway.ContractGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.example.InstalllmentSystem.core.util.ContractUtils.getInstallmentAmount;
import static com.example.InstalllmentSystem.core.util.ContractUtils.getMonthlyCetRate;
import static com.example.InstalllmentSystem.core.util.ContractUtils.getMultiply;

@RequiredArgsConstructor
@Component
public class CreateContractUseCase {

    private final ContractGateway contractGateway;

    public Contract execute(Contract contract) throws ContractPeriodZeroException, ContractRequestAmountZeroException {

        if (contract.getRequestedAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ContractRequestAmountZeroException();
        }

        if (contract.getOperationPeriod() <= 0) {
            throw new ContractPeriodZeroException();
        }

        var monthlyCetRate = getMonthlyCetRate();
        var totalAmount = getMultiply(contract, monthlyCetRate);
        var installmentAmount = getInstallmentAmount(contract);

        contract.setMonthlyCetRate(monthlyCetRate);
        contract.setTotalAmount(totalAmount);
        contract.setStartDate(LocalDate.now());
        contract.setEndDate(LocalDate.now().plusMonths(contract.getOperationPeriod()));
        contract.setDaysOverDue(0);
        contract.setStatus(ContractStatus.ACTIVE);
        contract.setRemainingAmount(BigDecimal.ZERO);

        return contractGateway.save(contract);
    }


}
