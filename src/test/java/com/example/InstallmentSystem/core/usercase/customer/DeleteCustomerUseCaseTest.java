package com.example.InstallmentSystem.core.usercase.customer;

import com.example.InstallmentSystem.core.exception.customer.CustomerNotFoundException;
import com.example.InstallmentSystem.core.gateway.CustomerGateway;
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

@ExtendWith(MockitoExtension.class)
public class DeleteCustomerUseCaseTest {

    @InjectMocks
    private DeleteCustomerUseCase underTest;

    @Mock
    private CustomerGateway customerGateway;


    @ParameterizedTest
    @MethodSource("whenCustomerDoesNotExistByIdThenShouldThrowCustomerNotFoundExceptionProvider")
    @DisplayName("when Customer Does Not Exist By Id Then Should Throw CustomerNotFoundException")
    void whenCustomerDoesNotExistByIdThenShouldThrowCustomerNotFoundException (String id) {
        // Given
        given(customerGateway.existById(id)).willReturn(false);

        // When
        var result = catchThrowable(() -> underTest.execute(id));

        // Then
        assertThat(result).isInstanceOf(CustomerNotFoundException.class);
    }

    static Object[] whenCustomerDoesNotExistByIdThenShouldThrowCustomerNotFoundExceptionProvider() {
        return new Object[] {
                "vbfdrvgb",
                null
        };
    }


    @Test
    @DisplayName("when Customer Is Valid Then Should Delete Customer Successfully")
    void whenCustomerIsValidThenShouldDeleteCustomerSuccessfully() {
        // Given
        String id = "lalala";
        given(customerGateway.existById(id)).willReturn(true);

        // When
        var result = catchThrowable(() -> underTest.execute(id));

        // Then
        assertThat(result);
    }


}
