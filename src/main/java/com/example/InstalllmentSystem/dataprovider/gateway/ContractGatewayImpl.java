package com.example.InstalllmentSystem.dataprovider.gateway;

import com.example.InstalllmentSystem.core.domain.Contract;
import com.example.InstalllmentSystem.core.gateway.ContractGateway;
import com.example.InstalllmentSystem.dataprovider.mapper.ContractEntityMapper;
import com.example.InstalllmentSystem.dataprovider.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ContractGatewayImpl implements ContractGateway {

    private final ContractRepository contractRepository;
    private final ContractEntityMapper contractMapper;

    @Override
    public Contract save(Contract contract) {

        var convert = contractMapper.toEntity(contract);
        var save = contractRepository.save(convert);
        var toDomain = contractMapper.toDomain(save);

        return toDomain;
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
        var convert = contractMapper.toDomain(find.orElse(null));

        return convert;
    }

    @Override
    public Contract update(Contract contract) {

        var convert = contractMapper.toEntity(contract);
        var save = contractRepository.save(convert);
        var convertToDomain = contractMapper.toDomain(save);

        return convertToDomain;
    }
}
