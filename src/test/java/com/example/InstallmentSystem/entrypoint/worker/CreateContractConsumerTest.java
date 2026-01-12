package com.example.InstallmentSystem.entrypoint.worker;

import com.example.InstallmentSystem.core.domain.Contract;
import com.example.InstallmentSystem.core.domain.Payment;
import com.example.InstallmentSystem.core.exception.contract.ContractPeriodZeroException;
import com.example.InstallmentSystem.core.exception.contract.ContractRequestAmountZeroException;
import com.example.InstallmentSystem.core.exception.payment.PaymentAmountZeroException;
import com.example.InstallmentSystem.core.exception.payment.PaymentMethodNotFoundException;
import com.example.InstallmentSystem.core.usercase.contract.CreateContractUseCase;
import com.example.InstallmentSystem.entrypoint.dto.ContractDTO;
import com.example.InstallmentSystem.entrypoint.dto.PaymentDTO;
import com.example.InstallmentSystem.entrypoint.mapper.ContractMapper;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.Message;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
public class CreateContractConsumerTest {

    @InjectMocks
    private CreateContractConsumer underTest;

    @Mock
    private ContractMapper contractMapper;

    @Mock
    private CreateContractUseCase createContractUseCase;

    @Mock
    private Message<ContractDTO> message;

    @Test
    void testReturnCreateContractEvent() {
        //Given/When/Then
        assertThatCode(() -> underTest.createLoanEvent())
                .doesNotThrowAnyException();
    }

    @Test
     void testReturnReceive() throws ContractRequestAmountZeroException, ContractPeriodZeroException {
        //Given
        var contractDTO = Instancio.of(ContractDTO.class).create();
        var contract = Instancio.of(Contract.class).create();

        given(message.getPayload()).willReturn(contractDTO);
        given(contractMapper.toDomain(message.getPayload())).willReturn(contract);
        given(createContractUseCase.execute(contract)).willReturn(contract);

        //When/Then
        assertThatCode(() -> underTest.receive(message)).doesNotThrowAnyException();

    }

    @Test
    void testReturnThrowReceive() throws ContractRequestAmountZeroException, ContractPeriodZeroException {
        //given
        var contractDTO = Instancio.create(ContractDTO.class);
        var contract = Instancio.of(Contract.class)
                .set(Select.field("requestedAmount"), null)
                .create();

        given(message.getPayload()).willReturn(contractDTO);
        given(contractMapper.toDomain(message.getPayload())).willReturn(contract);
        given(createContractUseCase.execute(contract)).willThrow(ContractRequestAmountZeroException.class);

        //when/then
        var result = catchThrowable(() -> underTest.receive(message));
        assertThat(result)
                .isInstanceOf(RuntimeException.class);

    }
}
