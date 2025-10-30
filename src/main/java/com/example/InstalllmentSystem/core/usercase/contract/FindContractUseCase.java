package com.example.InstalllmentSystem.core.usercase.contract;

import com.example.InstalllmentSystem.core.domain.Contract;
import com.example.InstalllmentSystem.core.domain.Customer;
import com.example.InstalllmentSystem.core.domain.enumeration.ContractStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class FindContractUseCase {
    public Contract execute(String id, LocalDate startDate, Customer customer, BigDecimal monthlyCetRate, BigDecimal requestedAmount, BigDecimal totalAmount, ContractStatus status,Integer dayOverDue, Integer operationPeriod){

        return Contract.builder()
                .id(id)
                .startDate(startDate)
                .customer(customer)
                .monthlyCetRate(monthlyCetRate)
                .requestedAmount(requestedAmount)
                .remainingAmount(totalAmount)
                .totalAmount(totalAmount)
                .status(status)
                .daysOverDue(dayOverDue)
                .operationPeriod(operationPeriod)
                .build();
    }
}

