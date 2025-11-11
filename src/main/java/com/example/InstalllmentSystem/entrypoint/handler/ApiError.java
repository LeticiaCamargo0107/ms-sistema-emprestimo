package com.example.InstalllmentSystem.entrypoint.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {

    private String message;
    private String code;
    private List<String> erros;

    public ApiError (String message, String code) {
        this.message = message;
        this.code = code;
    }

    public ApiError (List<String> erros, String code) {
        this.erros = erros;
        this.code = code;
    }
}
