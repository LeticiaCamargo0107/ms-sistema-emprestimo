package com.example.InstallmentSystem.dataprovider.gateway;

import com.example.InstallmentSystem.core.domain.Customer;
import com.example.InstallmentSystem.core.exception.customer.CustomerAddressNotFoundException;
import com.example.InstallmentSystem.dataprovider.adapter.AddressAdapter;
import com.example.InstallmentSystem.dataprovider.dto.ViaCepResponse;
import com.example.InstallmentSystem.dataprovider.entity.ContractEntity;
import com.example.InstallmentSystem.dataprovider.entity.CustomerEntity;
import com.example.InstallmentSystem.dataprovider.mapper.CustomerEntityMapper;
import com.example.InstallmentSystem.dataprovider.repository.CustomerRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mockStatic;


@ExtendWith(MockitoExtension.class)
public class CustomerGatewayImplTest {

    @InjectMocks
    private CustomerGatewayImpl underTest;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerEntityMapper customerMapper;

    @Mock
    private AddressAdapter adapter;

    @Test
    @DisplayName("test Return method save Customer in CustomerGatewayImpl")
    void testMethodSave() throws CustomerAddressNotFoundException {
        //given
        var customer = Instancio.of(Customer.class).create();
        var entity = Instancio.of(CustomerEntity.class).create();
        var address = Instancio.of(ViaCepResponse.class).create();
        var addressMock = mockStatic(CustomerEntity.class, CALLS_REAL_METHODS); {
            addressMock.when(() -> addressBuilder(customer)).thenReturn(address);
        }

        given(customerMapper.toEntity(customer)).willReturn(entity);
        given(customerMapper.toDomain(entity)).willReturn(customer);
        given(customerRepository.save(entity)).willReturn(entity);

        //when
        var result = underTest.save(customer);

        //then
        assertThat(result)
                .isNotNull()
                .isEqualTo(customer);

        addressMock.close();
    }

    @Test
    @DisplayName("test Return delete By Id Customer in CustomerGatewayImpl")
    void TestDelete() {
        //given
        var id = "lala";
        //when
        var result = catchThrowable(() -> underTest.deleteById(id));
        //then
        then(customerRepository).should().deleteById(id);
        assertThat(result);
    }

    @Test
    @DisplayName("test Return exists By Id Customer in CustomerGatewayImpl")
    void TestExistById() {
        //given
        var id = "lalala";
        //when
        var result = catchThrowable(() -> underTest.existById(id));
        //then
        then(customerRepository).should().existsById(id);
        assertThat(result);
    }

    @Test
    @DisplayName("test Return Find By Id Customer in CustomerGatewayImpl")
    void testReturnFindByIdIsACustomer() {
        //given
        var customerEntity = Instancio.of(CustomerEntity.class).create();
        var customer = Instancio.of(Customer.class).create();
        given(customerRepository.findById(customerEntity.getId())).willReturn(Optional.of(customerEntity));
        given(customerMapper.toDomain(customerEntity)).willReturn(customer);

        //when
        var result = underTest.findById(customerEntity.getId());

        //then
        then(customerRepository).should().findById(customerEntity.getId());
        then(customerMapper).should().toDomain(customerEntity);
        assertThat(result)
                .isNotNull()
                .isEqualTo(customer);
    }

    @Test
    @DisplayName("test Return Find By Id Is Null in CustomerGatewayImpl")
    void testReturnFindByIdIsNull() {
        //given
        var customerEntity = Instancio.of(CustomerEntity.class).create();
        var customer = Instancio.of(Customer.class).create();
        given(customerRepository.findById(customerEntity.getId())).willReturn(Optional.empty());

        //when
        var result = underTest.findById(customerEntity.getId());

        //then
        then(customerRepository).should().findById(customerEntity.getId());
        assertThat(result)
                .isNull();
    }

    @Test
    void testReturnFindAll() {
        //Given
        var pageable = PageRequest.of(1,1);
        var entities = new PageImpl<>(Instancio.createList(CustomerEntity.class));
        var listCustomer = entities.map(customerMapper::toDomain).getContent();
        var expectedValue = new PageImpl<>(listCustomer, pageable, entities.getTotalElements());

        given(customerRepository.findAll(pageable)).willReturn(entities);

        //When
        var result = underTest.findAll(pageable);

        //Then
        assertThat(result)
                .isEqualTo(expectedValue);
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
