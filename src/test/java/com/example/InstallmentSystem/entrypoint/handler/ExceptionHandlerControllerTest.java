package com.example.InstallmentSystem.entrypoint.handler;

import org.springframework.core.MethodParameter;
import org.springframework.validation.MapBindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

public class ExceptionHandlerControllerTest {

    @RestController
    @RequestMapping("/tests/exception")
    public class ExceptionHandlerTestController {

        @GetMapping("/method-argument-not-valid-exception")
        public @ResponseBody String methodArgumentNotValidException() throws MethodArgumentNotValidException, NoSuchMethodException {
            var classConstructor = RuntimeException.class.getDeclaredConstructor(String.class);
            var methodParameter = new MethodParameter(classConstructor, 0);

            var bindingResult = new MapBindingResult(Map.of(), "mapName");
            bindingResult.rejectValue("campoQueNaoExiste", "BINDING_ERROR_CODE", "erro de validação");
            bindingResult.rejectValue("outroCampoQueNaoExiste", "BINDING_ERROR_CODE", "mais um erro de validação");

            throw new MethodArgumentNotValidException(methodParameter, bindingResult);
        }

//        @GetMapping("/business-exception")
//        public @ResponseBody String businessException() {
//            throw new BusinessException("ERROR_CODE_1", "Business logic error occurred");
//        }
    }
}
