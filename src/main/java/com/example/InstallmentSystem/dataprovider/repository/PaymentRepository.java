package com.example.InstallmentSystem.dataprovider.repository;

import com.example.InstallmentSystem.dataprovider.entity.PaymentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends MongoRepository<PaymentEntity, String> {

}
