package com.example.InstalllmentSystem.dataprovider.repository;

import com.example.InstalllmentSystem.dataprovider.entity.PaymentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends MongoRepository<PaymentEntity, String> {

}
