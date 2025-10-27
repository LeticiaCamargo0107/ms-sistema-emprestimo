
package com.example.InstalllmentSystem.entrypoint.controller;

import com.example.InstalllmentSystem.core.domain.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {

    @GetMapping("/name/{name}")
    public String getByNamoe (String name){
        return name;
    }
}