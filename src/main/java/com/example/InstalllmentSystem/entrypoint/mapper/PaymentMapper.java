package com.example.InstalllmentSystem.entrypoint.mapper;

import com.example.InstalllmentSystem.core.domain.Payment;
import com.example.InstalllmentSystem.entrypoint.DTOs.PaymentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(source = "payMethod", target = "payMethod")
    Payment methodToDomain (PaymentDTO paymentDTO);

    @Mapping(source = "payMethod", target = "payMethod")
    PaymentDTO methodToDTO (Payment payment);

    @Mapping(source = "amount", target = "amount")
    Payment amountToDomain (PaymentDTO paymentDTO);

    @Mapping(source = "amount", target = "amount")
    PaymentDTO amountToDTO (Payment payment);

}
