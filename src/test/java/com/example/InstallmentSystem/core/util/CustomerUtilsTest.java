package com.example.InstallmentSystem.core.util;

import com.example.InstallmentSystem.core.domain.Customer;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CustomerUtilsTest {

    @Test
    @DisplayName("test Return Calculate Age")
    void testReturnCalculateAge() {
        //given
        var age = LocalDate.of(2009, 7,1);
        var value = false;
        var customer = Instancio.of(Customer.class)
                .set(Select.field("birthDate"), age)
                .create();
        //when
        var result = CustomerUtils.calculateAge(customer);
        //then
        assertThat(result)
                .isEqualTo(value);
    }
}
