package com.example.InstallmentSystem.dataprovider.repository;

import com.example.InstallmentSystem.dataprovider.entity.CustomerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<CustomerEntity, String> {
}
