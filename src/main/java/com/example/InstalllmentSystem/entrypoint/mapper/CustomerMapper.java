package com.example.InstalllmentSystem.entrypoint.mapper;

import com.example.InstalllmentSystem.core.domain.Customer;
import com.example.InstalllmentSystem.entrypoint.DTOs.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toDomain (CustomerDTO customerDTO);

    CustomerDTO toDTO (Customer customer);


}
