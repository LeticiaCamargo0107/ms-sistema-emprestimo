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

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public Payment save(Payment payment) {

        var convert = paymentMapper.toEntity(payment);
        var save = paymentRepository.save(convert);
        var toDomain = paymentMapper.toDomain(save);

        return toDomain;
    }

    @Override
    public void deleteById(String id) {

        paymentRepository.deleteById(id);
    }

    @Override
    public boolean existById(String id) {

        return paymentRepository.existsById(id);
    }

    @Override
    public Payment findById(String id) {
        var find = paymentRepository.findById(id);
        var convert = paymentMapper.toDomain(find.orElse(null));

        return convert;
    }

    @Override
    public Payment update(Payment payment) {

        var convert = paymentMapper.toEntity(payment);
        var save = paymentRepository.save(convert);
        var convertToDomain = paymentMapper.toDomain(save);

        return convertToDomain;
    }
}
