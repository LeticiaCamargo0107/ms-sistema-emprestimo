package com.example.InstallmentSystem.entrypoint.handler;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApiError {

    private String message;
    private String code;
    private List<String> errors;

    public ApiError (String message, String code) {
        this.message = message;
        this.code = code;
    }

    public ApiError (List<String> errors, String code) {
        this.errors = errors;
        this.code = code;
    }
}
