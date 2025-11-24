package com.example.InstalllmentSystem.core.util;

import com.example.InstalllmentSystem.core.domain.Contract;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

@UtilityClass
public class ContractUtils {

    public static BigDecimal getMonthlyCetRate() {
        return BigDecimal.valueOf(1.05);
    }

    public static BigDecimal getMultiply(Contract contract, BigDecimal monthlyCetRate) {
        return contract.getRequestedAmount()
                .divide(BigDecimal.valueOf(100))
                .multiply(monthlyCetRate)
                .multiply(BigDecimal.valueOf(contract.getOperationPeriod()));
    }
}
