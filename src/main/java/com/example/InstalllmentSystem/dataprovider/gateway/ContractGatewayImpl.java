package com.example.InstalllmentSystem.dataprovider.gateway;

import com.example.InstalllmentSystem.core.domain.Contract;
import com.example.InstalllmentSystem.core.gateway.ContractGateway;
import com.example.InstalllmentSystem.dataprovider.mapper.ContractMapper;
import com.example.InstalllmentSystem.dataprovider.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ContractGatewayImpl implements ContractGateway {

    private final ContractRepository contractRepository;
    private final ContractMapper contractMapper;

    @Override
    public Contract save(Contract contract) {

        var convert = contractMapper.toEntity(contract);
        var save = contractRepository.save(convert);
        var toDomain = contractMapper.toDomain(save);

        return toDomain;
    }

//    @Override
//    public void deleteById(String id) {
//
//        var delete = contractRepository.deleteById(id);
//        var convert = contractMapper.toDomain(delete);
//
//    }
//
//    @Override
//    public List<Contract> findAll() {
//        var find = contractRepository.findAll();
//        var convert = contractMapper.toDomain(find);
//
//        return find;
//    }
//
    @Override
    public Contract findById(String id) {
        var find = contractRepository.findById(id);
        var convert = contractMapper.toDomain(find.orElse(null));

        return convert;
    }

    @Override
    public Contract update(Contract contract) {

        var convert = contractMapper.toEntity(contract);
        var save = contractRepository.insert(convert);
        var convertToDomain = contractMapper.toDomain(save);

        return convertToDomain;
    }
}
