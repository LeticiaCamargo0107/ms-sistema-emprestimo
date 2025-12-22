package com.example.InstallmentSystem.entrypoint.worker;

import com.example.InstallmentSystem.core.exception.contract.ContractPeriodZeroException;
import com.example.InstallmentSystem.core.exception.contract.ContractRequestAmountZeroException;
import com.example.InstallmentSystem.core.usercase.contract.CreateContractUseCase;
import com.example.InstallmentSystem.entrypoint.dto.ContractDTO;
import com.example.InstallmentSystem.entrypoint.mapper.ContractMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class CreateContractConsumer {

    private final ContractMapper contractMapper;
    private final CreateContractUseCase createContractUseCase;

    @Bean
    Consumer<Message<ContractDTO>> createLoanEvent() {
        return this::receive;
    }

    public void receive(Message<ContractDTO> message) {
        try {
            var contract = contractMapper.toDomain(message.getPayload());
            createContractUseCase.execute(contract);
        } catch (ContractRequestAmountZeroException | ContractPeriodZeroException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
