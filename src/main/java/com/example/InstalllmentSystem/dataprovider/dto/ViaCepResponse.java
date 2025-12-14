package com.example.InstalllmentSystem.dataprovider.dto;

public record ViaCepResponse(String zipcode, String logradouro, String complemento,
                             String unidade, String bairro, String localidade, String uf) {
}
