package com.example.InstalllmentSystem.core.gateway;

import com.example.InstalllmentSystem.core.domain.Contract;

import java.util.List;

public interface ContractGateway {

    Contract save(Contract contract);

    void deleteById(String id);

    boolean existById (String id);

    Contract findById(String id);

    Contract update(Contract contract);

    List<Contract> findAll();
}
