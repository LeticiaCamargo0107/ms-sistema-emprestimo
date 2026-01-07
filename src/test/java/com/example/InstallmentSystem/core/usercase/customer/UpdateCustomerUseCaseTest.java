package com.example.InstallmentSystem.core.usercase.customer;

import com.example.InstallmentSystem.core.domain.Customer;
import com.example.InstallmentSystem.core.exception.customer.CustomerAddressNotFoundException;
import com.example.InstallmentSystem.core.exception.customer.CustomerNotFoundException;
import com.example.InstallmentSystem.core.gateway.CustomerGateway;
import org.instancio.Instancio;
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
public class UpdateCustomerUseCaseTest {

    @InjectMocks
    private UpdateCustomerUseCase underTest;

    @Mock
    private GetByIdCustomerUseCase getByIdCustomerUseCase;

    @Mock
    private CustomerGateway customerGateway;


    @Test
    void whenCustomerIsValidThenShouldGetCustomerByIdSuccessfully() throws CustomerAddressNotFoundException, CustomerNotFoundException {
        // Given
        String id = "lalala";
        var customer = Instancio.of(Customer.class).create();
        given(getByIdCustomerUseCase.execute(id)).willReturn(customer);

        // When
        var result = catchThrowable(() -> underTest.execute(id, customer));

        // Then
        then(customerGateway).should().save(customer);
        assertThat(result);
    }
}
