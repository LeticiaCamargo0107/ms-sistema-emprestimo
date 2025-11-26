package com.example.InstalllmentSystem.core.gateway;

import com.example.InstalllmentSystem.core.domain.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ContractGateway {

    Contract save(Contract contract);

    void deleteById(String id);

    boolean existById (String id);

    Contract findById(String id);

    Page<Contract> findAll(Pageable pageable);
}
