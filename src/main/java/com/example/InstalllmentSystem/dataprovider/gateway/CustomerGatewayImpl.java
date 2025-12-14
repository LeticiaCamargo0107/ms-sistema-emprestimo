package com.example.InstalllmentSystem.dataprovider.gateway;

import com.example.InstalllmentSystem.core.domain.Customer;
import com.example.InstalllmentSystem.core.gateway.GenericGateway;
import com.example.InstalllmentSystem.dataprovider.adapter.AddressAdapter;
import com.example.InstalllmentSystem.dataprovider.entity.CustomerEntity;
import com.example.InstalllmentSystem.dataprovider.mapper.CustomerEntityMapper;
import com.example.InstalllmentSystem.dataprovider.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class CustomerGatewayImpl implements GenericGateway<Customer> {

    private final CustomerRepository customerRepository;
    private final CustomerEntityMapper customerMapper;
    private final AddressAdapter adapter;

    @Override
    public Customer save(Customer customer) {

        var entity = customerMapper.toEntity(customer);
        var saved = customerRepository.save(entity);
        var addressEntity = addressBuilder(customer);
        entity.setAddress(addressEntity);

        return customerMapper.toDomain(saved);
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
        var entity = customerRepository.findById(id);
        return customerMapper.toDomain(entity.orElse(null));
    }


    @Override
    public Page<Customer> findAll(Pageable pageable) {
        Page<CustomerEntity> entities = customerRepository.findAll(pageable);
        List<Customer> contracts = entities.map(customerMapper::toDomain).getContent();
        return new PageImpl<>(contracts, pageable, entities.getTotalElements());
    }

    private CustomerEntity.CustomerAddress addressBuilder(Customer customer) {

        var addressResponse = adapter.getAddressByZipcode(customer.getZipcode());
        return CustomerEntity.CustomerAddress.builder()
                .uf(addressResponse.uf())
                .bairro(addressResponse.bairro())
                .complemento(addressResponse.complemento())
                .unidade(addressResponse.unidade())
                .logradouro(addressResponse.logradouro())
                .localidade(addressResponse.localidade())
                .build();
    }
}
