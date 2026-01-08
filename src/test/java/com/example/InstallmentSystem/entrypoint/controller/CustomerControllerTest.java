package com.example.InstallmentSystem.entrypoint.controller;

import com.example.InstallmentSystem.core.domain.Customer;
import com.example.InstallmentSystem.core.exception.contract.ContractNotFoundException;
import com.example.InstallmentSystem.core.exception.customer.CustomerAddressNotFoundException;
import com.example.InstallmentSystem.core.exception.customer.CustomerBirthDateException;
import com.example.InstallmentSystem.core.exception.customer.CustomerNotFoundException;
import com.example.InstallmentSystem.core.usercase.customer.CreateCustomerUseCase;
import com.example.InstallmentSystem.core.usercase.customer.DeleteCustomerUseCase;
import com.example.InstallmentSystem.core.usercase.customer.FindAllCustomerUseCase;
import com.example.InstallmentSystem.core.usercase.customer.GetByIdCustomerUseCase;
import com.example.InstallmentSystem.core.usercase.customer.UpdateCustomerUseCase;
import com.example.InstallmentSystem.entrypoint.dto.CustomerDTO;
import com.example.InstallmentSystem.entrypoint.mapper.CustomerMapper;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;


@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    @InjectMocks
    private CustomerController underTest;

    @Mock
    private CreateCustomerUseCase createCustomerUseCase;

    @Mock
    private DeleteCustomerUseCase deleteCustomerUseCase;

    @Mock
    private UpdateCustomerUseCase updateCustomerUseCase;

    @Mock
    private FindAllCustomerUseCase findCustomerUseCase;

    @Mock
    private GetByIdCustomerUseCase getByNameCustomerUseCase;

    @Mock
    private CustomerMapper customerMapper;

    @Test
    void testReturnGetById() throws CustomerNotFoundException, ContractNotFoundException {
        //given
        var id = "lala";
        var customer = Instancio.of(Customer.class).create();
        given(getByNameCustomerUseCase.execute(id)).willReturn(customer);

        //when
        var result = underTest.getById(id);

        //then
        assertThat(result)
                .isNotNull()
                .isEqualTo(customer);
    }

    @Test
    void findAll() {
        //given
        var pageable = PageRequest.of(0,1);
        //when/then
        assertThatCode(() -> underTest.findAll(pageable)).doesNotThrowAnyException();
    }

    @Test
    void create() throws CustomerBirthDateException, CustomerAddressNotFoundException {
        //given
        var customerDTO = Instancio.create(CustomerDTO.class);
        var customer = Instancio.create(Customer.class);
        given(customerMapper.toDomain(customerDTO)).willReturn(customer);
        given(createCustomerUseCase.execute(customer)).willReturn(customer);

        //when
        var result = underTest.create(customerDTO);

        //then
        then(customerMapper).should().toDomain(customerDTO);
        then(createCustomerUseCase).should().execute(customer);
        assertThat(result).
                isNotNull()
                .isEqualTo(customer);
    }

    @Test
    void deleteById() throws CustomerNotFoundException {
        //given
        var id = "lala";
        //when
        var result = catchThrowable(() -> underTest.deleteById(id));
        //then
        assertThat(result);
    }

    @Test
    void update() throws CustomerNotFoundException, CustomerAddressNotFoundException, CustomerBirthDateException {
        //given
        var customerDto = Instancio.create(CustomerDTO.class);
        var customer = Instancio.of(Customer.class)
                .set(Select.field("birthDate"), LocalDate.of(2000,1,1))
                .create();

        given(customerMapper.toDomain(customerDto)).willReturn(customer);
        given(updateCustomerUseCase.execute(customer.getId(), customer)).willReturn(customer);

        //when
        var result = underTest.update(customer.getId(), customerDto);

        //then
        assertThat(result).
                isNotNull()
                .isEqualTo(customer);
    }
}