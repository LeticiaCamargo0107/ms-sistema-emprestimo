package com.example.InstallmentSystem.dataprovider.gateway;

import com.example.InstallmentSystem.core.domain.Customer;
import com.example.InstallmentSystem.core.exception.customer.CustomerAddressNotFoundException;
import com.example.InstallmentSystem.core.gateway.CustomerGateway;
import com.example.InstallmentSystem.dataprovider.adapter.AddressAdapter;
import com.example.InstallmentSystem.dataprovider.entity.CustomerEntity;
import com.example.InstallmentSystem.dataprovider.mapper.CustomerEntityMapper;
import com.example.InstallmentSystem.dataprovider.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class CustomerGatewayImpl implements CustomerGateway {

    private final CustomerRepository customerRepository;
    private final CustomerEntityMapper customerMapper;
    private final AddressAdapter adapter;

    @Override
    public Customer save(Customer customer) throws CustomerAddressNotFoundException {

        var entity = customerMapper.toEntity(customer);
        var addressEntity = addressBuilder(customer);

        if (addressEntity.getBairro() == null) {
            throw new CustomerAddressNotFoundException(customer.getZipcode());
        }

        entity.setAddress(addressEntity);
        var saved = customerRepository.save(entity);
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
        var entities = customerRepository.findById(id);
        return customerMapper.toDomain(entities.orElse(null));
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        Page<CustomerEntity> entities = customerRepository.findAll(pageable);
        List<Customer> customer = entities.map(customerMapper::toDomain).getContent();
        return new PageImpl<>(customer, pageable, entities.getTotalElements());
    }

    private CustomerEntity.CustomerAddress addressBuilder(Customer customer) {

        var addressResponse = adapter.getAddressByZipcode(customer.getZipcode());
        return CustomerEntity.CustomerAddress.builder()
                .zipcode(customer.getZipcode())
                .uf(addressResponse.uf())
                .bairro(addressResponse.bairro())
                .complemento(addressResponse.complemento())
                .unidade(addressResponse.unidade())
                .logradouro(addressResponse.logradouro())
                .localidade(addressResponse.localidade())
                .build();
    }
}
