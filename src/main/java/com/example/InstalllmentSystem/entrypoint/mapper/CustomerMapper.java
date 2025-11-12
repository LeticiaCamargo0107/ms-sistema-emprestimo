package com.example.InstalllmentSystem.entrypoint.mapper;

import com.example.InstalllmentSystem.core.domain.Customer;
import com.example.InstalllmentSystem.entrypoint.DTOs.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(source = "name", target = "name")
    Customer nameToDomain (CustomerDTO customerDTO);

    @Mapping(source = "name", target = "name")
    CustomerDTO nameToDTO (Customer customer);

    @Mapping(source = "birthDate", target = "birthDate")
    Customer birthDateToDomain (CustomerDTO customerDTO);

    @Mapping(source = "birthDate", target = "BirthDate")
    CustomerDTO birthDateToDTO (Customer customer);

    @Mapping(source = "document", target = "document")
    Customer DocumentToDomain (CustomerDTO customerDTO);

    @Mapping(source = "birthDate", target = "BirthDate")
    CustomerDTO DocumentToDTO (Customer customer);
}
