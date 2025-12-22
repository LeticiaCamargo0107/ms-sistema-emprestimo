package com.example.InstallmentSystem.entrypoint.mapper;

import com.example.InstallmentSystem.core.domain.Payment;
import com.example.InstallmentSystem.entrypoint.dto.PaymentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    Payment toDomain(PaymentDTO paymentDTO);

    PaymentDTO toDTO (Payment payment);
}
