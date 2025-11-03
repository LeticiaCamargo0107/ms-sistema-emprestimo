package com.example.InstalllmentSystem.core.usercase.contract;

import com.example.InstalllmentSystem.core.domain.Contract;
import com.example.InstalllmentSystem.core.domain.Customer;
import com.example.InstalllmentSystem.core.domain.enumeration.ContractStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class DeleteContractUseCase {

    public Contract execute(String id) {
        var contract1 = Contract.builder()
                .id("1234")
                .endDate(LocalDate.now())
                .requestedAmount(BigDecimal.valueOf(2340))
                .totalAmount(BigDecimal.valueOf(3000))
                .daysOverDue(0)
                .status(ContractStatus.ACTIVE)
                .build();

        if (contract1.getId().equals(id)) {
            System.out.printf("Delete by id: %s\n", id);
            return contract1;
        }
        return null;
    }
}

