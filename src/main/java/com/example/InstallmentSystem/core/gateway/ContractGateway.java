package com.example.InstallmentSystem.core.gateway;

import com.example.InstallmentSystem.core.domain.Contract;
import com.example.InstallmentSystem.core.exception.contract.ContractNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContractGateway {

    Contract save(Contract contract);

    void deleteById(String id);

    boolean existById (String id);

    Contract findById(String id) throws ContractNotFoundException;

    Page<Contract> findAll(Pageable pageable);
}
