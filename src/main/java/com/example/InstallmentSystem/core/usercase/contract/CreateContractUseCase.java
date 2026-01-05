package com.example.InstallmentSystem.core.usercase.contract;

import com.example.InstallmentSystem.core.domain.Contract;
import com.example.InstallmentSystem.core.domain.enumeration.ContractStatus;
import com.example.InstallmentSystem.core.exception.contract.ContractPeriodZeroException;
import com.example.InstallmentSystem.core.exception.contract.ContractRequestAmountZeroException;
import com.example.InstallmentSystem.core.gateway.GenericGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.example.InstallmentSystem.core.util.ContractUtils.getInstallmentAmount;
import static com.example.InstallmentSystem.core.util.ContractUtils.getMonthlyCetRate;
import static com.example.InstallmentSystem.core.util.ContractUtils.getMultiply;

@RequiredArgsConstructor
@Component
@Slf4j
public class CreateContractUseCase {

    private final GenericGateway<Contract> contractGateway;

    public Contract execute(Contract contract) throws ContractPeriodZeroException, ContractRequestAmountZeroException {

        if (contract.getRequestedAmount() == null || (contract.getRequestedAmount().compareTo(BigDecimal.ZERO) <= 0)) {
            log.error("RequestedAmount must be greater than zero");
            throw new ContractRequestAmountZeroException();
        }

        if (contract.getOperationPeriod() <= 0) {
            log.error("Operation Period must be greater than zero");
            throw new ContractPeriodZeroException();
        }

        var monthlyCetRate = getMonthlyCetRate();
        var totalAmount = getMultiply(contract, monthlyCetRate);
        var installmentAmount = getInstallmentAmount(contract);

        contract.setMonthlyCetRate(monthlyCetRate);
        contract.setTotalAmount(totalAmount);
        contract.setStartDate(LocalDate.now());
        contract.setEndDate(LocalDate.now().plusMonths(contract.getOperationPeriod()));
        contract.setDaysOverDue(0);
        contract.setStatus(ContractStatus.ACTIVE);
        contract.setRemainingAmount(BigDecimal.ZERO);
        contract.setInstallmentAmount(installmentAmount);

        return contractGateway.save(contract);
    }


}
