package com.example.InstallmentSystem.entrypoint.worker;

import com.example.InstallmentSystem.core.exception.customer.CustomerAddressNotFoundException;
import com.example.InstallmentSystem.core.exception.customer.CustomerBirthDateException;
import com.example.InstallmentSystem.core.usercase.customer.CreateCustomerUseCase;
import com.example.InstallmentSystem.entrypoint.dto.CustomerDTO;
import com.example.InstallmentSystem.entrypoint.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class CreateCustomerConsumer {

    private final CustomerMapper customerMapper;
    private final CreateCustomerUseCase createCustomerUseCase;

    @Bean
    Consumer<Message<CustomerDTO>> createCustomerEvent() {
        return this::receive;
    }

    public void receive(Message<CustomerDTO> message) {
        try {
            var customer = customerMapper.toDomain(message.getPayload());
            createCustomerUseCase.execute(customer);
        } catch (CustomerBirthDateException | CustomerAddressNotFoundException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
