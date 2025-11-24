package com.example.InstalllmentSystem.dataprovider.gateway;

import com.example.InstalllmentSystem.core.domain.Customer;
import com.example.InstalllmentSystem.core.gateway.CustomerGateway;
import com.example.InstalllmentSystem.dataprovider.mapper.CustomerEntityMapper;
import com.example.InstalllmentSystem.dataprovider.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomerGatewayImpl implements CustomerGateway {

    private final CustomerRepository customerRepository;
    private final CustomerEntityMapper customerMapper;

    @Override
    public Customer save(Customer customer) {

        var convert = customerMapper.toEntity(customer);
        var save = customerRepository.save(convert);

        return customerMapper.toDomain(save);
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

        return customerMapper.toDomain(find.orElse(null));
    }

    @Override
    public Customer update(Customer customer) {

        var convert = customerMapper.toEntity(customer);
        var save = customerRepository.save(convert);

        return customerMapper.toDomain(save);
    }

    @Override
    public List<Customer> findAll() {
        var entities = customerRepository.findAll();
        return entities.stream().map(customerMapper::toDomain).toList();    }
}
