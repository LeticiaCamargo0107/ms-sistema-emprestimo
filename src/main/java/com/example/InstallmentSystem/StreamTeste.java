package com.example.InstallmentSystem;

import com.example.InstallmentSystem.core.domain.Contract;
import com.example.InstallmentSystem.core.domain.Customer;
import com.example.InstallmentSystem.core.domain.Payment;
import com.example.InstallmentSystem.core.domain.enumeration.ContractStatus;
import com.example.InstallmentSystem.core.domain.enumeration.CustomerStatus;
import com.example.InstallmentSystem.core.domain.enumeration.PaymentMethod;

import com.example.InstallmentSystem.entrypoint.dto.PaymentDTO;
import com.example.InstallmentSystem.entrypoint.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class StreamTeste {

    private final PaymentMapper mapper;

    public static void main(String[] args) {


//      .max(Comparator.comparing(Contract::getRequestedAmount));

        var contractList = instantiateContracts();
        var customerList = instantiateCustomer();

        System.out.println(countContract(contractList));
        System.out.println(biggestRequestedAmount(contractList));
        System.out.println(noneMatch(contractList));
        System.out.println(sortedRemainingAmount(contractList));
        System.out.println(findFirstDate(customerList));

//        List<PaymentDTO> paymentDTOList = instantiatePayments();

    }

    //The method receive a Payment DTO, transform then in Payment, separate payment method and make a distinct
    public List<PaymentMethod> distinctPayMethod(List<PaymentDTO> dto) {
        return dto.stream()
                .map(mapper::toDomain)
                .map(Payment::getPayMethod)
                .distinct().toList();
    }

    //The method count how many contracts it created today
    public static long countContract(List<Contract> contractList) {
        return contractList.stream()
                .map(Contract::getStartDate)
                .filter(contract -> contract.equals(LocalDate.now()))
                .count();
    }

    //Search contract that requested amount is bigger than 100.000 and sum the values
    public static BigDecimal biggestRequestedAmount(List<Contract> contractList) {
        return contractList.stream()
                .map(Contract::getRequestedAmount)
                .filter(contract -> contract.compareTo(BigDecimal.valueOf(100000)) >= 0)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

    }

    //See if customer have none match with condition, and return a boolean
    public static Boolean noneMatch(List<Contract> list) {
        return list.stream()
                .noneMatch(contract -> contract.getStatus().equals(ContractStatus.CANCELED));
    }

    //Organize contracts for asc order if remaining amount is minor than requested amound
    public static List<BigDecimal> sortedRemainingAmount (List<Contract> list) {
        return list.stream()
                .filter(contract -> contract.getRemainingAmount().compareTo(contract.getRequestedAmount()) < 0)
                .map(Contract::getRemainingAmount)
                .sorted()
                .toList();
    }

    //search inactive clients and try to find first, if method don't find, search any active client and return null if not find any customer
    public static Customer findFirstDate (List<Customer> list) {
        return list.stream()
                .filter(customer -> customer.getStatus().equals(CustomerStatus.INACTIVE))
                .findFirst().orElse(list.stream()
                        .filter(customer -> customer.getStatus().equals(CustomerStatus.BLOCKED))
                        .findFirst()
                        .orElse(list.stream()
                                .findFirst()
                                .orElse(null)));
    }




    public static List<Contract> instantiateContracts () {
        var contract1 = Contract.builder().requestedAmount(BigDecimal.valueOf(290000)).operationPeriod(5).startDate(LocalDate.now()).remainingAmount(BigDecimal.valueOf(300000)).status(ContractStatus.ACTIVE).build();
        var contract2 = Contract.builder().requestedAmount(BigDecimal.valueOf(100)).operationPeriod(0).startDate(LocalDate.of(2020, 8, 12)).remainingAmount(BigDecimal.valueOf(97)).status(ContractStatus.ACTIVE).build();
        var contract3 = Contract.builder().requestedAmount(BigDecimal.valueOf(445)).operationPeriod(5).startDate(LocalDate.now()).remainingAmount(BigDecimal.valueOf(446)).status(ContractStatus.CANCELED).build();
        var contract4 = Contract.builder().requestedAmount(BigDecimal.valueOf(100000)).operationPeriod(5).startDate(LocalDate.of(2025, 12, 17)).remainingAmount(BigDecimal.valueOf(289)).status(ContractStatus.LIQUIDATED).build();

        return List.of(contract1, contract2, contract3, contract4);
    }

    public List<PaymentDTO> instantiatePayments () {
        var payment1 = Payment.builder().paidAt(LocalDateTime.now()).amount(BigDecimal.valueOf(200)).payMethod(PaymentMethod.CREDIT_CARD).build();
        var payment2 = Payment.builder().paidAt(LocalDateTime.now()).amount(BigDecimal.valueOf(200)).payMethod(PaymentMethod.SLIP).build();
        var payment3 = Payment.builder().paidAt(LocalDateTime.now()).amount(BigDecimal.valueOf(200)).payMethod(PaymentMethod.PIX).build();
        var payment4 = Payment.builder().paidAt(LocalDateTime.now()).amount(BigDecimal.valueOf(200)).payMethod(PaymentMethod.DEBIT_CARD).build();

        List<Payment> paymentList = List.of(payment1, payment2, payment3, payment4);
        return paymentList.stream().map(mapper::toDTO).toList();
    }

    public static List<Customer> instantiateCustomer () {
        var customer1 = Customer.builder().name("Ana").document("12345678910").status(CustomerStatus.ACTIVE).build();
        var customer2 = Customer.builder().name("Ryan").document("11223344556").status(CustomerStatus.BLOCKED).build();
        var customer3 = Customer.builder().name("Maria").document("10987654321").status(CustomerStatus.INACTIVE).build();
        var customer4 = Customer.builder().name("Cláudio").document("12345678999").status(CustomerStatus.ACTIVE).build();

        return List.of(customer1, customer2, customer3, customer4);
    }
}

