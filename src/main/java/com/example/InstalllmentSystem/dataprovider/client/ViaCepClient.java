package com.example.InstalllmentSystem.dataprovider.client;

import com.example.InstalllmentSystem.dataprovider.dto.ViaCepResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viaCep", url = "${sgep.viaCep.url}")
public interface ViaCepClient {

    @GetMapping("{zipcode}/json")
    ViaCepResponse getAddressByZipcode(@PathVariable String zipcode);
}
