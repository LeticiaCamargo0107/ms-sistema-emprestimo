package com.example.InstalllmentSystem.dataprovider.mapper;

import com.example.InstalllmentSystem.core.domain.Payment;
import com.example.InstalllmentSystem.dataprovider.entity.PaymentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    Payment toDomain (PaymentEntity paymentEntity);

    PaymentEntity toDTO (Payment payment);
}
