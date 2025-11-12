package com.example.InstalllmentSystem.entrypoint.mapper;
import com.example.InstalllmentSystem.core.domain.Contract;
import com.example.InstalllmentSystem.entrypoint.DTOs.ContractDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ConcractMapper {

    @Mapping(source = "requestedAmount", target = "requestedAmount")
    Contract requestedAmountToDomain (ContractDTO contracDTO);

    @Mapping(source = "requestedAmount", target = "requestedAmount")
    ContractDTO requestedAmountToDTO (Contract contrac);

    @Mapping(source = "operationPeriod", target = "operationPeriod")
    Contract periodToDomain (ContractDTO contracDTO);

    @Mapping(source = "operationPeriod", target = "operationPeriod")
    ContractDTO periodToDTO (Contract contrac);

    @Mapping(source = "IdCustomer", target = "id.customer")
    Contract customerToDomain (ContractDTO contracDTO);

    @Mapping(source = "IdCustomer", target = "id.customer")
    ContractDTO customerToDTO (Contract contract);
}
