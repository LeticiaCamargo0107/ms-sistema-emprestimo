package com.example.InstallmentSystem.dataprovider.dto;

public record ViaCepResponse(String zipcode, String logradouro, String complemento,
                             String unidade, String bairro, String localidade, String uf) {
}
