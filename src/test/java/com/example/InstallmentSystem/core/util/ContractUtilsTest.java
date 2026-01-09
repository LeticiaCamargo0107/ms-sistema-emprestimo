package com.example.InstallmentSystem.core.util;

import com.example.InstallmentSystem.core.domain.Contract;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ContractUtilsTest {

    @Test
    @DisplayName("Test Return Monthly Cet Rate")
    void TestReturnMonthlyCetRate() {
        //Given/when
        var value = BigDecimal.valueOf(3);
        var result = ContractUtils.getMonthlyCetRate();
        //then
        assertThat(result)
                .isNotNull()
                .isEqualTo(value);
    }

    @Test
    @DisplayName("test Return Calculate Total Amount")
    void testReturnCalculateTotalAmount() {
        //given
        var value = BigDecimal.valueOf(109);
        var contract = Instancio.of(Contract.class)
                .set(Select.field("requestedAmount"), BigDecimal.valueOf(100))
                .set(Select.field("operationPeriod"), 3)
                .create();
        //when
        var result = ContractUtils.calculateTotalAmount(contract, ContractUtils.getMonthlyCetRate());

        //then
        assertThat(result)
                .isNotNull()
                .isEqualTo(value);
    }

    @Test
    @DisplayName("test Return Get Installment Amount")
    void testReturnGetInstallmentAmount() {
        //given
        var contract = Instancio.of(Contract.class)
                .set(Select.field("operationPeriod"), 3)
                .set(Select.field("requestedAmount"), BigDecimal.valueOf(100))
                .create();
        var value = BigDecimal.valueOf(36.33);

        //when
        var result = ContractUtils.getInstallmentAmount(contract);

        //then
        assertThat(result)
                .isEqualTo(value);
    }
}
