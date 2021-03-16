package com.imd.yourvoice.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CommonExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseDTO<Map<String, Object>> methodArgumentNotValidExceptionHandler(ContentCachingRequestWrapper request) {
        return ResponseDTO.<Map<String, Object>>builder()
                .data(getRequestBodyAt(request))
                .message("Validation Error")
                .isSuccess("fail")
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseDTO<Map<String, Object>> httpMessageNotReadableExceptionHandler(ContentCachingRequestWrapper request) {
        return ResponseDTO.<Map<String, Object>>builder()
                .data(getRequestBodyAt(request))
                .message("UnknownRequestProperties Error")
                .isSuccess("fail")
                .build();
    }

    @SneakyThrows
    private Map<String, Object> getRequestBodyAt(ContentCachingRequestWrapper request) {
        return new ObjectMapper().readValue(request.getContentAsByteArray(), HashMap.class);
    }
}
