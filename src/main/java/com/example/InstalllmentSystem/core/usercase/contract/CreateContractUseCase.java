package com.example.InstalllmentSystem.core.usercase.contract;

import com.example.InstalllmentSystem.core.domain.Contract;
import com.example.InstalllmentSystem.core.domain.Customer;
import com.example.InstalllmentSystem.core.domain.enumeration.ContractStatus;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class CreateContractUseCase {
    public Contract execute(String id, Customer customer, BigDecimal requestedAmount, Integer operationPeriod){

        BigDecimal monthlyCetRate = BigDecimal.valueOf(1.05);
        BigDecimal totalAmount = requestedAmount.multiply(monthlyCetRate);

        return Contract.builder()
                .id(id)
                .startDate(LocalDate.now())
                .customer(customer)
                .monthlyCetRate(monthlyCetRate)
                .requestedAmount(requestedAmount)
                .remainingAmount(totalAmount)
                .totalAmount(totalAmount)
                .status(ContractStatus.ACTIVE)
                .daysOverDue(0)
                .operationPeriod(operationPeriod)
                .build();
    }
}
