package com.example.InstallmentSystem.entrypoint.handler;

import org.apache.coyote.BadRequestException;
import org.apache.kafka.shaded.com.google.protobuf.Api;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

@ExtendWith(MockitoExtension.class)
public class CustomGlobalExceptionTest {

    @InjectMocks
    private CustomGlobalException underTest;

    @Test
    void testHandleBadRequested() {
        var ex = Instancio.create(BadRequestException.class);
        var error = new ApiError(ex.getMessage(), HttpStatus.BAD_REQUEST.toString());
        var result = catchThrowable(this::testHandleBadRequested);
        assertThat(result).isEqualTo(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error));
    }


    private ResponseEntity<ApiError> handleBadRequestException(BadRequestException ex) {
        var error = new ApiError(ex.getMessage(), HttpStatus.BAD_REQUEST.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage()).collect(Collectors.toList());

        var error = new ApiError(errors, HttpStatus.BAD_REQUEST.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
