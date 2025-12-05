package com.example.InstalllmentSystem.entrypoint.worker;

import com.example.InstalllmentSystem.core.exception.customer.CustomerBirthDateException;
import com.example.InstalllmentSystem.core.usercase.customer.CreateCustomerUseCase;
import com.example.InstalllmentSystem.entrypoint.DTOs.CustomerDTO;
import com.example.InstalllmentSystem.entrypoint.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

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
        } catch (CustomerBirthDateException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
