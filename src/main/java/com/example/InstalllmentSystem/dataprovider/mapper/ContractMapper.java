package com.example.InstalllmentSystem.dataprovider.mapper;
import com.example.InstalllmentSystem.core.domain.Contract;
import com.example.InstalllmentSystem.dataprovider.entity.ContractEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContractMapper {

    Contract toDomain (ContractEntity contractEntity);

    ContractEntity toEntity (Contract contract);
}
