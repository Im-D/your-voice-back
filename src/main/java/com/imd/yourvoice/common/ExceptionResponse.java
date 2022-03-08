package com.imd.yourvoice.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
public class ExceptionResponse {
    private Map<String, Object> requestBody;

    @SneakyThrows
    public static ExceptionResponse of(ContentCachingRequestWrapper requestWrapper) {
        return new ExceptionResponse(new ObjectMapper().readValue(requestWrapper.getContentAsByteArray(), HashMap.class));
    }
}
