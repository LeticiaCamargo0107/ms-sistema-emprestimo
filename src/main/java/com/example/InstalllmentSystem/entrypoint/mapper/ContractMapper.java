package com.example.InstalllmentSystem.entrypoint.mapper;
import com.example.InstalllmentSystem.core.domain.Contract;
import com.example.InstalllmentSystem.entrypoint.dto.ContractDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContractMapper {

    @Mapping(source = "customerId", target = "customer.id")
    Contract toDomain (ContractDTO contractDTO);

    @Mapping(source = "customer.id", target = "customerId")
    ContractDTO toDTO (Contract contract);
}
