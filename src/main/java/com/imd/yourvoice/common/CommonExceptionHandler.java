package com.imd.yourvoice.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestControllerAdvice
public class CommonExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseDTO<Map<String, Object>> methodArgumentNotValidExceptionHandler(HttpServletRequest request) {
        return ResponseDTO.<Map<String, Object>>builder()
                .data(getRequestBodyAt(request))
                .message("Validation Error")
                .isSuccess("fail")
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseDTO<Map<String, Object>> httpMessageNotReadableExceptionHandler(HttpServletRequest request) {
        return ResponseDTO.<Map<String, Object>>builder()
                .data(getRequestBodyAt(request))
                .message("UnknownRequestProperties Error")
                .isSuccess("fail")
                .build();
    }

    private Map<String, Object> getRequestBodyAt(HttpServletRequest request) {
        return (Map<String, Object>) request.getAttribute("requestBody");
    }
}
