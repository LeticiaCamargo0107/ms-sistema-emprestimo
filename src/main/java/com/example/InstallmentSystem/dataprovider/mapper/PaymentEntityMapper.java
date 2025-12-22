package com.example.InstallmentSystem.dataprovider.mapper;

import com.example.InstallmentSystem.core.domain.Payment;
import com.example.InstallmentSystem.dataprovider.entity.PaymentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentEntityMapper {

    Payment toDomain (PaymentEntity paymentEntity);

    PaymentEntity toEntity (Payment payment);
}
