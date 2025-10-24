package com.example.InstalllmentSystem.dataprovider.repository;

import com.example.InstalllmentSystem.dataprovider.entity.ContractEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends MongoRepository<ContractEntity, String> {

}
