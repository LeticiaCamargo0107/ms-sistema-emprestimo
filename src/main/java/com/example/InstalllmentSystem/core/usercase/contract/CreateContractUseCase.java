package com.example.InstalllmentSystem.core.usercase.contract;

import com.example.InstalllmentSystem.core.domain.Contract;
import com.example.InstalllmentSystem.core.domain.Customer;
import com.example.InstalllmentSystem.core.domain.enumeration.ContractStatus;
import com.example.InstalllmentSystem.core.exception.contract.ContractCustomerNullException;
import com.example.InstalllmentSystem.core.exception.contract.ContractPeriodZeroException;
import com.example.InstalllmentSystem.core.exception.contract.ContractRequestAmountZeroException;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class CreateContractUseCase {

    public Contract execute(Contract contract) throws ContractPeriodZeroException, ContractRequestAmountZeroException, ContractCustomerNullException {

        BigDecimal monthlyCetRate = BigDecimal.valueOf(1.05);
        BigDecimal totalAmount = contract.getTotalAmount().multiply(monthlyCetRate);

        if (contract.getOperationPeriod() <= 0) {
            throw new ContractPeriodZeroException();
        }

        if (contract.getCustomer() == null) {
            throw new ContractCustomerNullException();
        }

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
