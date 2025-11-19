package com.example.InstalllmentSystem.dataprovider.gateway;

import com.example.InstalllmentSystem.core.domain.Customer;
import com.example.InstalllmentSystem.core.gateway.CustomerGateway;
import com.example.InstalllmentSystem.dataprovider.mapper.CustomerMapper;
import com.example.InstalllmentSystem.dataprovider.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerGatewayImpl implements CustomerGateway {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer) {
        var convert = customerMapper.toEntity(customer);
        var save = customerRepository.save(convert);
        var toDomain = customerMapper.toDomain(save);

        return toDomain;
    }
}
