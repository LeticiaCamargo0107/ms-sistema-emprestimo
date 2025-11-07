package com.example.InstalllmentSystem.core.usercase.contract;

import com.example.InstalllmentSystem.core.domain.Contract;
import com.example.InstalllmentSystem.core.domain.enumeration.ContractStatus;
import com.example.InstalllmentSystem.core.exception.contract.ContractRequestAmountZeroException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class UpdateContractUseCase {

    public Contract execute(Contract contract) throws ContractRequestAmountZeroException {

        var contract1 = Contract.builder()
                .id("bvinvdsldvnhg")
                .startDate(LocalDate.of(1500, 12, 3))
                .requestedAmount(BigDecimal.valueOf(0000.0099999))
                .daysOverDue(87)
                .status(ContractStatus.ACTIVE)
                .totalAmount(BigDecimal.valueOf(2000))
                .customer(contract.getCustomer())
                .build();

        String conversionString = String.valueOf(contract.getRequestedAmount());
        Integer conversionInteger = Integer.valueOf(conversionString);

        if (conversionInteger <= 0) {
            throw new ContractRequestAmountZeroException();
        }
        contract1.setRequestedAmount(contract.getRequestedAmount());
        return contract1;
    }
}
