package com.example.InstalllmentSystem.entrypoint.controller;

import com.example.InstalllmentSystem.core.domain.Contract;
import com.example.InstalllmentSystem.core.domain.Installment;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/v1/contracts")
public class ContractController {

    @GetMapping("/{id}")
    public List<Contract> get(@PathVariable String id){
        System.out.printf("Get por id: %s", id);
        return List.of();
    }


}