package com.example.InstalllmentSystem.dataprovider.mapper;

import com.example.InstalllmentSystem.core.domain.Customer;
import com.example.InstalllmentSystem.dataprovider.entity.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerEntityMapper {

    Customer toDomain (CustomerEntity customerEntity);

    CustomerEntity toEntity (Customer customer);
}
