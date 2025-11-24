package com.example.InstalllmentSystem.dataprovider.gateway;

import com.example.InstalllmentSystem.core.domain.Contract;
import com.example.InstalllmentSystem.core.gateway.ContractGateway;
import com.example.InstalllmentSystem.dataprovider.mapper.ContractEntityMapper;
import com.example.InstalllmentSystem.dataprovider.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class ContractGatewayImpl implements ContractGateway {

    private final ContractRepository contractRepository;
    private final ContractEntityMapper contractMapper;

    @Override
    public Contract save(Contract contract) {

        var convert = contractMapper.toEntity(contract);
        var save = contractRepository.save(convert);

        return contractMapper.toDomain(save);
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
    public Contract findById(String id) {
        var find = contractRepository.findById(id);

        return contractMapper.toDomain(find.orElse(null));
    }

    @Override
    public Contract update(Contract contract) {

        var convert = contractMapper.toEntity(contract);
        var save = contractRepository.save(convert);

        return contractMapper.toDomain(save);
    }

    @Override
    public List<Contract> findAll() {

        var entities = contractRepository.findAll();
        return entities.stream().map(contractMapper::toDomain).toList();
    }
}
