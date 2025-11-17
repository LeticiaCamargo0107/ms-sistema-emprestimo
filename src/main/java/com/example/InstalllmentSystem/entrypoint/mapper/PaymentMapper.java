package com.example.InstalllmentSystem.entrypoint.mapper;

import com.example.InstalllmentSystem.core.domain.Payment;
import com.example.InstalllmentSystem.entrypoint.DTOs.PaymentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    Payment toDomain (PaymentDTO paymentDTO);

    PaymentDTO toDTO (Payment payment);





}
