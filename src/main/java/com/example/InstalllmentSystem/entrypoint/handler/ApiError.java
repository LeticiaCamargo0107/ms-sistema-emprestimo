package com.example.InstalllmentSystem.entrypoint.handler;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApiError {

    private String message;
    private String code;
    private List<String> erro;

    public ApiError (String message, String code) {
        this.message = message;
        this.code = code;
    }

    public ApiError (List<String> erro, String code) {
        this.erro = erro;
        this.code = code;
    }
}
