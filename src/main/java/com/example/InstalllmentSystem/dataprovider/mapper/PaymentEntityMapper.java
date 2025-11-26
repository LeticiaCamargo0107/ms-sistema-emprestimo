package com.example.InstalllmentSystem.dataprovider.mapper;

import com.example.InstalllmentSystem.core.domain.Payment;
import com.example.InstalllmentSystem.dataprovider.entity.PaymentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentEntityMapper {

    Payment toDomain (PaymentEntity paymentEntity);

    PaymentEntity toEntity (Payment payment);
}
