package com.example.InstallmentSystem.core.util;

import com.example.InstallmentSystem.core.domain.Customer;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.Period;

@UtilityClass
public class CustomerUtils {

    public static boolean calculateAge (Customer customer) {
        var birthYear = customer.getBirthDate();
        var age = Period.between(birthYear, LocalDate.now()).getYears();
        return age > 18;
    }
}
