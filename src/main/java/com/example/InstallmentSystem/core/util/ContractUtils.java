package com.example.InstallmentSystem.core.util;

import com.example.InstallmentSystem.core.domain.Contract;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.RoundingMode;

@UtilityClass
public class ContractUtils {

    public static BigDecimal getMonthlyCetRate() {
        return BigDecimal.valueOf(3);
    }

    public static BigDecimal getMultiply(Contract contract, BigDecimal monthlyCetRate) {
        var conversion = BigDecimal.valueOf(contract.getOperationPeriod());
        var rate = monthlyCetRate.multiply(conversion);
        var multiply = contract.getRequestedAmount()
                .multiply(rate)
                .divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);

        return contract.getRequestedAmount().add(multiply);
    }

    public static BigDecimal getInstallmentAmount(Contract contract) {
        var conversion = BigDecimal.valueOf(contract.getOperationPeriod());
        return contract.getRequestedAmount().divide(conversion.setScale(2, RoundingMode.HALF_UP), RoundingMode.HALF_UP);
    }
}
