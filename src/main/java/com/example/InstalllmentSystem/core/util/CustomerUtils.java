package com.example.InstalllmentSystem.core.util;

import com.example.InstalllmentSystem.core.domain.Customer;

import java.time.LocalDate;

public class CustomerUtils {

    public static int calculateAge (Customer customer) {
        int year = customer.getBirthDate().getYear();
        int yearNow = LocalDate.now().getYear();
        return yearNow - year;
    }
}
