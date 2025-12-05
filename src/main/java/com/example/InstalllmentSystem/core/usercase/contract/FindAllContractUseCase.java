package com.example.InstalllmentSystem.core.usercase.contract;

import com.example.InstalllmentSystem.core.domain.Contract;
import com.example.InstalllmentSystem.core.gateway.GenericGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class FindAllContractUseCase {

    private final GenericGateway<Contract> contractGateway;

    public Page<Contract> execute(Pageable pageable) {

        return contractGateway.findAll(pageable);
    }
}
