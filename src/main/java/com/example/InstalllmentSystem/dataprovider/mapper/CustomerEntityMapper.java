package com.example.InstalllmentSystem.dataprovider.mapper;

import com.example.InstalllmentSystem.core.domain.Customer;
import com.example.InstalllmentSystem.dataprovider.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerEntityMapper {

    @Mapping(source = "entity.address.zipcode", target = "zipcode")
    Customer toDomain (CustomerEntity entity);

    CustomerEntity toEntity (Customer customer);
}
