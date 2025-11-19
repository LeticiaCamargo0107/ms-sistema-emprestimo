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

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public Customer save(Customer customer) {

        var convert = customerMapper.toEntity(customer);
        var save = customerRepository.save(convert);
        var toDomain = customerMapper.toDomain(save);

        return toDomain;
    }

    @Override
    public void deleteById(String id) {

        customerRepository.deleteById(id);
    }

    @Override
    public boolean existById(String id) {

        return customerRepository.existsById(id);
    }

    @Override
    public Customer findById(String id) {
        var find = customerRepository.findById(id);
        var convert = customerMapper.toDomain(find.orElse(null));

        return convert;
    }

    @Override
    public Customer update(Customer customer) {

        var convert = customerMapper.toEntity(customer);
        var save = customerRepository.save(convert);
        var convertToDomain = customerMapper.toDomain(save);

        return convertToDomain;
    }
}
