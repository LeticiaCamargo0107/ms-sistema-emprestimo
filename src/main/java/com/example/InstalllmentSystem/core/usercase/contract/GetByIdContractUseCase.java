package com.example.InstalllmentSystem.core.usercase.contract;

import com.example.InstalllmentSystem.core.domain.Contract;
import com.example.InstalllmentSystem.core.exception.contract.ContractIdNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetByIdContractUseCase {

    public Contract execute(String id) throws ContractIdNotFoundException {

        var contract1 = Contract.builder()
                .id("id")
                .build();

        var contract2 = Contract.builder()
                .id("hahahahaha")
                .build();

        var contract3 = Contract.builder()
                .id("blebleble")
                .build();

        List<Contract> listContracts = List.of(contract1, contract2, contract3);

        for (Contract list : listContracts){
            if (list.getId().equals(id)){
                return list;
            }
        }
        throw new ContractIdNotFoundException(id);
    }
}

