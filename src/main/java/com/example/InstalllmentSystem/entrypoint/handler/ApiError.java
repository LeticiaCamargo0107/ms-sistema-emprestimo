package com.example.InstalllmentSystem.entrypoint.handler;

public class ApiError {

    private String message;
    private String code;

    public ApiError (String message, String code) {
        this.message = message;
        this.code = code;
    }
}
