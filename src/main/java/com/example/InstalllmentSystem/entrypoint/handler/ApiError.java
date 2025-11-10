package com.example.InstalllmentSystem.entrypoint.handler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiError {

    private String message;
    private String code;

    public ApiError (String message, String code) {
        this.message = message;
        this.code = code;
    }
}
