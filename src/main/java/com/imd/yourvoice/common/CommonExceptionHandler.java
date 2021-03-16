package com.imd.yourvoice.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.util.ContentCachingRequestWrapper;

@RestControllerAdvice
public class CommonExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseDTO<ExceptionResponse> methodArgumentNotValidExceptionHandler(ContentCachingRequestWrapper request) {
        return ResponseDTO.<ExceptionResponse>builder()
                .data(ExceptionResponse.of(request))
                .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .isSuccess("fail")
                .build();
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseDTO<ExceptionResponse> httpMessageNotReadableExceptionHandler(ContentCachingRequestWrapper request) {
        return ResponseDTO.<ExceptionResponse>builder()
                .data(ExceptionResponse.of(request))
                .message(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase())
                .isSuccess("fail")
                .build();
    }
}
