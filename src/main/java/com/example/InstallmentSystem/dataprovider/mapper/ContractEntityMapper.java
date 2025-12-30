package com.example.InstallmentSystem.dataprovider.mapper;
import com.example.InstallmentSystem.core.domain.Contract;
import com.example.InstallmentSystem.dataprovider.entity.ContractEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContractEntityMapper {

    Contract toDomain (ContractEntity contractEntity);

    ContractEntity toEntity (Contract contract);
}
