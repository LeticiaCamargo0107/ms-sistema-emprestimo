package com.example.InstalllmentSystem.entrypoint.worker;

import com.example.InstalllmentSystem.core.exception.contract.ContractPeriodZeroException;
import com.example.InstalllmentSystem.core.exception.contract.ContractRequestAmountZeroException;
import com.example.InstalllmentSystem.core.usercase.contract.CreateContractUseCase;
import com.example.InstalllmentSystem.entrypoint.DTOs.ContractDTO;
import com.example.InstalllmentSystem.entrypoint.mapper.ContractMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

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
