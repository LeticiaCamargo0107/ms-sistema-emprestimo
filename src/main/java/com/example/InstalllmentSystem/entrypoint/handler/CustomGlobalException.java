package com.example.InstalllmentSystem.entrypoint.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class CustomGlobalException {

//    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
//    private ResponseEntity<ApiError> hhaah(HttpClientErrorException.BadRequest ex) {
//
//
//    }
}
