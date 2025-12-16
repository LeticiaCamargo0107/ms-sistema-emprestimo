package com.example.InstalllmentSystem.core.util;

import com.example.InstalllmentSystem.core.domain.Customer;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;

@UtilityClass
public class CustomerUtils {

    public static int calculateAge (Customer customer) {
        int birthYear = customer.getBirthDate().getYear();
        int yearNow = LocalDate.now().getYear();
        return yearNow - birthYear;
    }
}
