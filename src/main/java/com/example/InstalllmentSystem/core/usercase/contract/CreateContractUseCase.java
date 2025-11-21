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

@RequiredArgsConstructor
@Component
public class CreateContractUseCase {

    private final ContractGateway contractGateway;

    public Contract execute(Contract contract) throws ContractPeriodZeroException, ContractRequestAmountZeroException {

        if (contract.getRequestedAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ContractRequestAmountZeroException();
        }

        if (contract.getOperationPeriod() <= 2) {
            throw new ContractPeriodZeroException();
        }

        BigDecimal monthlyCetRate = getMonthlyCetRate();
        BigDecimal totalAmount = getMultiply(contract, monthlyCetRate);

        contract.setMonthlyCetRate(monthlyCetRate);
        contract.setTotalAmount(totalAmount);
        contract.setStartDate(LocalDate.now());
        contract.setEndDate(LocalDate.now().plusMonths(contract.getOperationPeriod()));
        contract.setDaysOverDue(0);
        contract.setStatus(ContractStatus.ACTIVE);
        contract.setRemainingAmount(BigDecimal.ZERO);

        return contractGateway.save(contract);
    }

    private static BigDecimal getMonthlyCetRate() {
        return BigDecimal.valueOf(1.05);
    }

    private static BigDecimal getMultiply(Contract contract, BigDecimal monthlyCetRate) {
        return contract.getRequestedAmount()
                .divide(BigDecimal.valueOf(100))
                .multiply(monthlyCetRate)
                .multiply(BigDecimal.valueOf(contract.getOperationPeriod()));
    }
}
