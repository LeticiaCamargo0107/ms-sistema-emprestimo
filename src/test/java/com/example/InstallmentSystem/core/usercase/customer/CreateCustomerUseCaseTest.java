package com.example.InstallmentSystem.core.usercase.customer;

import com.example.InstallmentSystem.core.domain.Customer;
import com.example.InstallmentSystem.core.exception.customer.CustomerAddressNotFoundException;
import com.example.InstallmentSystem.core.exception.customer.CustomerBirthDateException;
import com.example.InstallmentSystem.core.gateway.CustomerGateway;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CreateCustomerUseCaseTest {

    @InjectMocks
    private CreateCustomerUseCase underTest;

    @Mock
    private CustomerGateway customerGateway;

    @Test
    @DisplayName("when Age Is Less Than Eighteen Then Should Throw CustomerBirthDateException")
    void whenAgeIsLessThanEighteenThenShouldThrowCustomerBirthDateException() throws CustomerBirthDateException {
        // Given
        var age = LocalDate.of(2009, 7, 1);
        var customer = Instancio.of(Customer.class)
                .set(Select.field("birthDate"), age)
                .create();

        // When
        var result = catchThrowable(() -> underTest.execute(customer));

        // Then
        then(customerGateway).shouldHaveNoInteractions();
        assertThat(result).isInstanceOf(CustomerBirthDateException.class);
    }



    @Test
    void whenZipCodeIsNullThenShouldThrowCustomerAddressNotFoundException() throws CustomerAddressNotFoundException {
        // Given
        var age = LocalDate.of(2000, 7, 1);
        var customer = Instancio.of(Customer.class)
                .set(Select.field("birthDate"), age)
                .set(Select.field("zipcode"), null)
                .create();

        // When
        var result = catchThrowable(() -> underTest.execute(customer));

        // Then
        then(customerGateway).shouldHaveNoInteractions();
        assertThat(result).isInstanceOf(CustomerAddressNotFoundException.class);
    }


    @Test
    void whenCustomerIsValidThenShouldCreateCustomerSuccessfully() throws CustomerAddressNotFoundException, CustomerBirthDateException {
        // Given
        var customer = Instancio.create(Customer.class);

        given(customerGateway.save(customer)).willReturn(customer);

        // When
        var result = underTest.execute(customer);

        // Then
        then(customerGateway).should().save(customer);

        assertThat(result)
                .isNotNull()
                .isEqualTo(customer);
    }
}