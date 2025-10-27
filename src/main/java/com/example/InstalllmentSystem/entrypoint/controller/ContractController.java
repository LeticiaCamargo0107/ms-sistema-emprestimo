package com.example.InstalllmentSystem.entrypoint.controller;

import com.example.InstalllmentSystem.core.domain.Contract;
import com.example.InstalllmentSystem.core.domain.Installment;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@RequestMapping("/v1/contract")
public class ContractController {

    @GetMapping("/id/{id}")
    public String getById(Installment listIntallment){
        return listIntallment.getId();
    }


}