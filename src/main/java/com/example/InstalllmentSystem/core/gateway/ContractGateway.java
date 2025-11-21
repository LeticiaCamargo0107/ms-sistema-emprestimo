package com.example.InstalllmentSystem.core.gateway;

import com.example.InstalllmentSystem.core.domain.Contract;

public interface ContractGateway {

    Contract save(Contract contract);

    void deleteById(String id);

    boolean existById (String id);

    Contract findById(String id);

    Contract update(Contract contract);
}
