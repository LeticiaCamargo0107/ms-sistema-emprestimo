package com.example.InstalllmentSystem.entrypoint.mapper;
import com.example.InstalllmentSystem.core.domain.Contract;
import com.example.InstalllmentSystem.entrypoint.DTOs.ContractDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContractMapper {

    @Mapping(source = "customerId", target = "customer.id")
    Contract toDomain (ContractDTO contracDTO);

    @Mapping(source = "customerId", target = "customer.id")
    ContractDTO toDTO (Contract contract);
}
