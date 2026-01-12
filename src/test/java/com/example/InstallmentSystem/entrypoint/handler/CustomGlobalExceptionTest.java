package com.example.InstallmentSystem.entrypoint.handler;

import com.example.InstallmentSystem.MockMvcTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@WebMvcTest(controllers = ExceptionHandlerControllerTest.class)
    class CustomGlobalExceptionHandlerTest extends MockMvcTestBase {

        @Test
        void testMethodArgumentNotValidException() {
            given()
                    .when()
                    .get("/tests/exception/method-argument-not-valid-exception")
                    .then()
                    .assertThat().statusCode(HttpStatus.BAD_REQUEST.value())
                    .and().body("message", equalTo("campoQueNaoExiste: erro de validação, outroCampoQueNaoExiste: mais um erro de validação"));
        }
}
