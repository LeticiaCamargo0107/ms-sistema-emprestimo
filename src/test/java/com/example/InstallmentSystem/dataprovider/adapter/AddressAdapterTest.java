package com.example.InstallmentSystem.dataprovider.adapter;

import com.example.InstallmentSystem.core.exception.customer.CustomerAddressNotFoundException;
import com.example.InstallmentSystem.dataprovider.client.ViaCepClient;
import com.example.InstallmentSystem.dataprovider.dto.ViaCepResponse;
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


@ExtendWith(MockitoExtension.class)
public class AddressAdapterTest {

    @InjectMocks
    private AddressAdapter underTest;

    @Mock
    private ViaCepClient viaCepClient;

    @ParameterizedTest
    @MethodSource("whenZipCodeIsNullOrDoNotExistsThenShouldThrowCustomerAddressNotFoundExceptionProvider")
    @DisplayName("when ZipCode Is Null Or Do Not Exist sThen Should Throw CustomerAddressNotFoundException")
    void whenZipCodeIsNullOrDoNotExistsThenShouldThrowCustomerAddressNotFoundException(String zipcode) {
        //when
        var result = catchThrowable(() -> underTest.getAddressByZipcodeFallback(zipcode));

        //then
        assertThat(result).isInstanceOf(CustomerAddressNotFoundException.class);
    }

    static Object[] whenZipCodeIsNullOrDoNotExistsThenShouldThrowCustomerAddressNotFoundExceptionProvider() {
        return new Object[] {
          "frevfe",
           null
        };
    }

    @Test
    @DisplayName("when ZipCode Is Valid Should Return Address Of The Customer")
    void whenZipCodeIsValidShouldReturnAddressOfTheCustomer() {
        //given
        var address = Instancio.of(ViaCepResponse.class).create();
        given(viaCepClient.getAddressByZipcode(address.zipcode())).willReturn(address);

        //when
        var result = underTest.getAddressByZipcode(address.zipcode());

        //then
        assertThat(result)
                .isNotNull();
    }
}
