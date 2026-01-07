package com.example.InstallmentSystem.entrypoint.worker;

import com.example.InstallmentSystem.core.domain.Customer;
import com.example.InstallmentSystem.core.exception.customer.CustomerAddressNotFoundException;
import com.example.InstallmentSystem.core.exception.customer.CustomerBirthDateException;
import com.example.InstallmentSystem.core.usercase.customer.CreateCustomerUseCase;
import com.example.InstallmentSystem.entrypoint.dto.CustomerDTO;
import com.example.InstallmentSystem.entrypoint.mapper.CustomerMapper;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.Message;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class CreateCustomerConsumerTest {

    @InjectMocks
    private CreateCustomerConsumer underTest;

    @Mock
    private CustomerMapper customerMapper;

    @Mock
    private CreateCustomerUseCase createCustomerUseCase;

    @Mock
    private Message<CustomerDTO> message;

    @Test
    void receive() throws CustomerBirthDateException, CustomerAddressNotFoundException {
        var customerDTO = Instancio.of(CustomerDTO.class).create();
        var customer = Instancio.of(Customer.class).create();

        given(message.getPayload()).willReturn(customerDTO);
        given(customerMapper.toDomain(message.getPayload())).willReturn(customer);
        given(createCustomerUseCase.execute(customer)).willReturn(customer);

        assertThatCode(() -> underTest.receive(message)).doesNotThrowAnyException();

    }
}
