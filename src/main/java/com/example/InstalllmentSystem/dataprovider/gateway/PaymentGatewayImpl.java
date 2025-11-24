package com.example.InstalllmentSystem.dataprovider.gateway;

import com.example.InstalllmentSystem.core.domain.Payment;
import com.example.InstalllmentSystem.core.gateway.PaymentGateway;
import com.example.InstalllmentSystem.dataprovider.mapper.PaymentEntityMapper;
import com.example.InstalllmentSystem.dataprovider.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class PaymentGatewayImpl implements PaymentGateway {

    private final PaymentRepository paymentRepository;
    private final PaymentEntityMapper paymentMapper;

    @Override
    public Payment save(Payment payment) {

        var convert = paymentMapper.toEntity(payment);
        var save = paymentRepository.save(convert);

        return paymentMapper.toDomain(save);
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

        return paymentMapper.toDomain(find.orElse(null));
    }

    @Override
    public Payment update(Payment payment) {

        var convert = paymentMapper.toEntity(payment);
        var save = paymentRepository.save(convert);

        return paymentMapper.toDomain(save);
    }

    @Override
    public List<Payment> findAll() {
        var entities = paymentRepository.findAll();
        return entities.stream().map(paymentMapper::toDomain).toList();
    }
}
