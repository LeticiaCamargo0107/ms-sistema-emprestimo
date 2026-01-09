package com.example.InstallmentSystem.core.usercase.contract;

import com.example.InstallmentSystem.core.domain.Contract;
import com.example.InstallmentSystem.core.exception.contract.ContractNotFoundException;
import com.example.InstallmentSystem.core.gateway.GenericGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class GetByIdContractUseCaseTest {

    @InjectMocks
    private GetByIdContractUseCase underTest;

    @Mock
    private GenericGateway<Contract> contractGateway;


    @ParameterizedTest
    @MethodSource("whenContractDoesNotExistByIdThenShouldThrowContractNotFoundExceptionProvider")
    @DisplayName("when Contract Does Not Exist By Id Then Should Throw ContractNotFoundException")
    void whenContractDoesNotExistByIdThenShouldThrowContractNotFoundException (String id) throws ContractNotFoundException {
        // Given
        given(contractGateway.existById(id)).willReturn(false);

        // When
        var result = catchThrowable(() -> underTest.execute(id));

        // Then
        assertThat(result).isInstanceOf(ContractNotFoundException.class);
    }

    static Object[] whenContractDoesNotExistByIdThenShouldThrowContractNotFoundExceptionProvider() {
        return new Object[] {
                "vbfdrvgb",
                null
        };
    }


    @Test
    @DisplayName("when Contract Is Valid Then Should Get Contract By Id Successfully")
    void whenContractIsValidThenShouldGetContractByIdSuccessfully() {
        // Given
        String id = "lalala";
        given(contractGateway.existById(id)).willReturn(true);

        // When/Then
        assertThatCode(() -> underTest.execute(id)).doesNotThrowAnyException();
    }
}
