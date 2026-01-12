package com.example.InstallmentSystem.dataprovider.gateway;

import com.example.InstallmentSystem.core.domain.Contract;
import com.example.InstallmentSystem.core.exception.contract.ContractNotFoundException;
import com.example.InstallmentSystem.core.gateway.ContractGateway;
import com.example.InstallmentSystem.dataprovider.entity.ContractEntity;
import com.example.InstallmentSystem.dataprovider.mapper.ContractEntityMapper;
import com.example.InstallmentSystem.dataprovider.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class ContractGatewayImpl implements ContractGateway {

    private final ContractRepository contractRepository;
    private final ContractEntityMapper contractMapper;

    @Override
    public Contract save(Contract contract) {

        var entity = contractMapper.toEntity(contract);
        var saved = contractRepository.save(entity);

        return contractMapper.toDomain(saved);
    }

    @Override
    public void deleteById(String id) {

        contractRepository.deleteById(id);
    }

    @Override
    public boolean existById(String id) {

        return contractRepository.existsById(id);
    }

    @Override
    public Contract findById(String id) throws ContractNotFoundException {
        var entity = contractRepository.findById(id);
        return contractMapper.toDomain(entity.orElse(null));
    }


    @Override
    public Page<Contract> findAll(Pageable pageable) {
        Page<ContractEntity> entities = contractRepository.findAll(pageable);
        List<Contract> contracts = entities.map(contractMapper::toDomain).getContent();
        return new PageImpl<>(contracts, pageable, entities.getTotalElements());
    }
}
