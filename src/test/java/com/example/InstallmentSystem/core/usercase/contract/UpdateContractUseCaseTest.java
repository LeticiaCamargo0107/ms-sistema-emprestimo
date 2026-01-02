package com.example.InstallmentSystem.core.usercase.contract;

import com.example.InstallmentSystem.core.domain.Contract;
import com.example.InstallmentSystem.core.exception.contract.ContractNotFoundException;
import com.example.InstallmentSystem.core.exception.contract.ContractPeriodZeroException;
import com.example.InstallmentSystem.core.exception.contract.ContractRequestAmountZeroException;
import com.example.InstallmentSystem.core.gateway.GenericGateway;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class UpdateContractUseCaseTest {

    @InjectMocks
    private UpdateContractUseCase underTest;

    @Mock
    private GetByIdContractUseCase getByIdContractUseCase;

    @Mock
    private GenericGateway<Contract> contractGateway;

    @ParameterizedTest
    @MethodSource("WhenRequestedAmountIsLessThanOrZeroThenShouldThrowContractRequestAmountZeroExceptionProvider")
    @DisplayName("When Requested Amount Is Less Than Or Zero Then Should Throw ContractRequestAmountZeroException")
    void WhenRequestedAmountIsLessThanOrZeroThenShouldThrowContractRequestAmountZeroException(BigDecimal requestedAmount) throws ContractRequestAmountZeroException, ContractNotFoundException {
        //Given
        var id = "lalala";
        var contract = Instancio.of(Contract.class)
                .set(Select.field("requestedAmount"), requestedAmount)
                .create();

        // When
        var result = catchThrowable(() -> underTest.execute(id, contract));

        // Then
        assertThat(result).isInstanceOf(ContractRequestAmountZeroException.class);
    }

    static Object[] WhenRequestedAmountIsLessThanOrZeroThenShouldThrowContractRequestAmountZeroExceptionProvider() {
        return new Object[] {
          BigDecimal.valueOf(-1),
          BigDecimal.ZERO,
          null
        };
    }


    @ParameterizedTest
    @MethodSource("WhenOperationPeriodIsLessThanOrZeroThenShouldThrowContractRequestAmountZeroExceptionProvider")
    @DisplayName("When Operation Period Is Less Than Or Zero Then Should Throw ContractRequestAmountZeroException")
    void WhenOperationPeriodIsLessThanOrZeroThenShouldThrowContractRequestAmountZeroException(Integer operationPeriod) throws ContractRequestAmountZeroException, ContractNotFoundException {
        //Given
        var id = "lalala";
        var contract = Instancio.of(Contract.class)
                .set(Select.field("operationPeriod"), operationPeriod)
                .create();

        // When
        var result = catchThrowable(() -> underTest.execute(id, contract));

        // Then
        assertThat(result).isInstanceOf(ContractPeriodZeroException.class);
    }

    static Object[] WhenOperationPeriodIsLessThanOrZeroThenShouldThrowContractRequestAmountZeroExceptionProvider() {
        return new Object[] {
                -1,
                0,
                null
        };
    }

    @Test
    void WhenContractIsValidShouldUpdateContractSuccessfully() throws ContractRequestAmountZeroException, ContractPeriodZeroException, ContractNotFoundException {
        // Given
        var id = "lalala";
        var contract = Instancio.create(Contract.class);

        given(contractGateway.save(contract)).willReturn(contract);
        given(getByIdContractUseCase.execute(id)).willReturn(contract);

        // When
        var result = underTest.execute(id, contract);

        // Then
        then(contractGateway).should().save(contract);

        assertThat(result)
                .isNotNull()
                .isEqualTo(contract);
    }
}
