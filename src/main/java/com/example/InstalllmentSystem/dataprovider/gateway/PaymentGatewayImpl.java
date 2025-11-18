package com.example.InstalllmentSystem.dataprovider.gateway;

import com.example.InstalllmentSystem.core.domain.Payment;
import com.example.InstalllmentSystem.core.gateway.PaymentGateway;
import com.example.InstalllmentSystem.dataprovider.mapper.PaymentMapper;
import com.example.InstalllmentSystem.dataprovider.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PaymentGatewayImpl implements PaymentGateway {

    private final PaymentMapper paymentMapper;
    private final PaymentRepository paymentRepository;


    @Override
    public Payment save(Payment payment) {

        var convert = paymentMapper.toEntity(payment);
        var save = paymentRepository.save(convert);
        var toDomain = paymentMapper.toDomain(save);

        return toDomain;
    }
}
