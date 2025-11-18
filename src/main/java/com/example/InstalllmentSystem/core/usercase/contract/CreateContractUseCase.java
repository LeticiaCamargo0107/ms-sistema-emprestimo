package com.example.InstalllmentSystem.core.usercase.contract;

import com.example.InstalllmentSystem.core.domain.Contract;
import com.example.InstalllmentSystem.core.domain.enumeration.ContractStatus;
import com.example.InstalllmentSystem.core.exception.contract.ContractPeriodZeroException;
import com.example.InstalllmentSystem.core.exception.contract.ContractRequestAmountZeroException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class CreateContractUseCase {

    public Contract execute(Contract contract) throws ContractPeriodZeroException, ContractRequestAmountZeroException {

        if (contract.getRequestedAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ContractRequestAmountZeroException();
        }

        if (contract.getOperationPeriod() <= 2) {
            throw new ContractPeriodZeroException();
        }

        BigDecimal monthlyCetRate = BigDecimal.valueOf(1.05);
        BigDecimal totalAmount = contract.getTotalAmount().multiply(monthlyCetRate);

        return Contract.builder()
                .id(contract.getId())
                .startDate(LocalDate.now())
                .customer(contract.getCustomer())
                .monthlyCetRate(monthlyCetRate)
                .requestedAmount(contract.getRequestedAmount())
                .remainingAmount(totalAmount)
                .totalAmount(totalAmount)
                .status(ContractStatus.ACTIVE)
                .daysOverDue(0)
                .operationPeriod(contract.getOperationPeriod())
                .build();
    }
}
