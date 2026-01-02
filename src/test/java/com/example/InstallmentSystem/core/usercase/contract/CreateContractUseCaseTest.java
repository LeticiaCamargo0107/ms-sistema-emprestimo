package com.example.InstallmentSystem.core.usercase.contract;

import com.example.InstallmentSystem.core.domain.Contract;
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
class CreateContractUseCaseTest {

    @InjectMocks
    private CreateContractUseCase underTest;

    @Mock
    private GenericGateway<Contract> contractGateway;

    @ParameterizedTest
    @MethodSource("whenRequestedAmountIsLessThanOrZeroThenShouldThrowContractRequestAmountZeroExceptionProvider")
    @DisplayName("When requested amount is less than or zero Then should throw ContractRequestAmountZeroException")
    void whenRequestedAmountIsLessThanOrZeroThenShouldThrowContractRequestAmountZeroException(BigDecimal requestedAmount) throws ContractPeriodZeroException, ContractRequestAmountZeroException {
        // Given
        var contract = Instancio.of(Contract.class)
                .set(Select.field("requestedAmount"), requestedAmount)
                .create();

        // When
        var result = catchThrowable(() -> underTest.execute(contract));

        // Then
        assertThat(result).isInstanceOf(ContractRequestAmountZeroException.class);
    }

    static Object[] whenRequestedAmountIsLessThanOrZeroThenShouldThrowContractRequestAmountZeroExceptionProvider() {
        return new Object[] {
                BigDecimal.valueOf(-1000),
                BigDecimal.ZERO,
                null
        };
    }

    @Test
    void whenRequestedAmountIsZeroThenShouldThrowContractRequestAmountZeroExceptiondd() throws ContractPeriodZeroException, ContractRequestAmountZeroException {
        // Given
        var contract = Instancio.of(Contract.class)
                .set(Select.field("operationPeriod"), 0)
                .create();

        // When
        var result = catchThrowable(() -> underTest.execute(contract));

        // Then
        then(contractGateway).shouldHaveNoInteractions();

        assertThat(result).isInstanceOf(ContractPeriodZeroException.class);
    }


    @Test
    void whenContractIsValidThenShouldCreateContractSuccessfully() throws ContractPeriodZeroException, ContractRequestAmountZeroException  {
        // Given
        var contract = Instancio.create(Contract.class);

        given(contractGateway.save(contract)).willReturn(contract);

        // When
        var result = underTest.execute(contract);

        // Then
        then(contractGateway).should().save(contract);

        assertThat(result)
                .isNotNull()
                .isEqualTo(contract);
    }
}