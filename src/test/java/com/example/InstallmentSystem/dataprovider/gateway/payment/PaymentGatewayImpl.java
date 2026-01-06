package com.example.InstallmentSystem.dataprovider.gateway.payment;

import com.example.InstallmentSystem.core.domain.Payment;
import com.example.InstallmentSystem.core.gateway.GenericGateway;
import com.example.InstallmentSystem.dataprovider.entity.PaymentEntity;
import com.example.InstallmentSystem.dataprovider.mapper.PaymentEntityMapper;
import com.example.InstallmentSystem.dataprovider.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;


@RequiredArgsConstructor
@Component
public class PaymentGatewayImpl implements GenericGateway<Payment> {

    private final PaymentRepository paymentRepository;
    private final PaymentEntityMapper paymentMapper;

    @Override
    public Payment save(Payment payment) {

        var entity = paymentMapper.toEntity(payment);
        var saved = paymentRepository.save(entity);

        return paymentMapper.toDomain(saved);
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
        var entity = paymentRepository.findById(id);
        return paymentMapper.toDomain(entity.orElse(null));
    }


    @Override
    public Page<Payment> findAll(Pageable pageable) {
        Page<PaymentEntity> entities = paymentRepository.findAll(pageable);
        List<Payment> contracts = entities.map(paymentMapper::toDomain).getContent();
        return new PageImpl<>(contracts, pageable, entities.getTotalElements());
    }
}
