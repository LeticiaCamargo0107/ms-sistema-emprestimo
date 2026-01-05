package com.example.InstallmentSystem.core.usercase.payment;

import com.example.InstallmentSystem.core.domain.Contract;
import com.example.InstallmentSystem.core.gateway.GenericGateway;
import com.example.InstallmentSystem.core.usercase.contract.DeleteContractUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class DeletePaymentUseCaseTest {

    @InjectMocks
    private DeleteContractUseCase underTest;

    @Mock
    private GenericGateway<Contract> contractGateway;


    @ParameterizedTest
    @MethodSource("whenContractDoesNotExistByIdThenShouldThrowContractNotFoundExceptionProvider")
    @DisplayName("when Contract Does Not Exist By Id Then Should Throw ContractNotFoundException")
    void whenContractDoesNotExistByIdThenShouldThrowContractNotFoundException (String id) {
        // Given
        given(contractGateway.existById(id)).willReturn(false);

        // When
        var result = catchThrowable(() -> underTest.execute(id));

        // Then
        then(contractGateway).should().existById(id);
        assertThat(result);
    }

    static Object[] whenContractDoesNotExistByIdThenShouldThrowContractNotFoundExceptionProvider() {
        return new Object[] {
                "vbfdrvgb",
                null
        };
    }


    @Test
    void whenContractIsValidThenShouldDeleteContractSuccessfully() {
        // Given
        String id = "lalala";
        given(contractGateway.existById(id)).willReturn(true);

        // When
        var result = catchThrowable(() -> underTest.execute(id));

        // Then
        then(contractGateway).should().deleteById(id);
        assertThat(result);
    }


}
