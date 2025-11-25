package com.example.InstalllmentSystem.core.util;

import com.example.InstalllmentSystem.core.domain.Contract;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.RoundingMode;

@UtilityClass
public class ContractUtils {

    public static BigDecimal getMonthlyCetRate() {
        return BigDecimal.valueOf(1.05);
    }

    public static BigDecimal getMultiply(Contract contract, BigDecimal monthlyCetRate) {
        return contract.getRequestedAmount()
                .divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP)
                .multiply(monthlyCetRate)
                .multiply(BigDecimal.valueOf(contract.getOperationPeriod()));
    }

    public static BigDecimal getInstallmentAmount(Contract contract) {
        var conversion = BigDecimal.valueOf(contract.getOperationPeriod());
        return contract.getRequestedAmount().divide(conversion.setScale(2, RoundingMode.HALF_UP));
    }
}
