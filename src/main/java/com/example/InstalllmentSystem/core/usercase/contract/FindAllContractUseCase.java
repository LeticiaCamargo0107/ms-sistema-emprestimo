package com.example.InstalllmentSystem.core.usercase.contract;

import com.example.InstalllmentSystem.core.domain.Contract;
import com.example.InstalllmentSystem.core.domain.enumeration.ContractStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class FindAllContractUseCase {

    public List<Contract> execute() {
        System.out.println("Find all of Contract");

        var contract1 = Contract.builder()
                .id("1234")
                .startDate(LocalDate.of(2020, 10, 20))
                .totalAmount(BigDecimal.valueOf(10000))
                .daysOverDue(19)
                .build();

        var contract2 = Contract.builder()
                .id("abc12567")
                .startDate(LocalDate.of(1980, 9, 14))
                .requestedAmount(BigDecimal.valueOf(2340))
                .daysOverDue(0)
                .status(ContractStatus.ACTIVE)
                .build();

        var contract3 = Contract.builder()
                .id("404fnf")
                .startDate(LocalDate.of(1999, 12, 30))
                .totalAmount(BigDecimal.valueOf(100000))
                .daysOverDue(0)
                .endDate(LocalDate.now())
                .status(ContractStatus.LIQUIDATED)
                .build();

        return List.of(contract1, contract2, contract3);
    }
}
