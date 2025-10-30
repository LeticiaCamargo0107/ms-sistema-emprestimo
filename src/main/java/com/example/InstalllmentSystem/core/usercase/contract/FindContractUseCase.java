package com.example.InstalllmentSystem.core.usercase.contract;

import com.example.InstalllmentSystem.core.domain.Contract;
import com.example.InstalllmentSystem.core.domain.Customer;
import com.example.InstalllmentSystem.core.domain.enumeration.ContractStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class FindContractUseCase {
    public Contract execute(String id){

        var contract1 = Contract.builder()
                .id("id")
                .build();

        var contract2 = Contract.builder()
                .id("hahahahaha")
                .build();

        var contract3 = Contract.builder()
                .id("blebleble")
                .build();

        List<Contract> list = List.of(contract1, contract2, contract3);
        return null;
    }
}

