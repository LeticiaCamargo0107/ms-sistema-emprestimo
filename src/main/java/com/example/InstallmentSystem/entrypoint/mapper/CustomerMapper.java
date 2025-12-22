package com.example.InstallmentSystem.entrypoint.mapper;

import com.example.InstallmentSystem.core.domain.Customer;
import com.example.InstallmentSystem.entrypoint.dto.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toDomain (CustomerDTO customerDTO);

    CustomerDTO toDTO (Customer customer);

}
