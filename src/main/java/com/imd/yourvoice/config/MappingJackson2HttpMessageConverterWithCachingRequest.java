package com.imd.yourvoice.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonInputMessage;

import javax.servlet.ServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PushbackInputStream;
import java.lang.reflect.Type;
import java.util.Map;

@Configuration
public class MappingJackson2HttpMessageConverterWithCachingRequest extends MappingJackson2HttpMessageConverter {

    @Autowired
    private ServletRequest request;

    public MappingJackson2HttpMessageConverterWithCachingRequest(ObjectMapper objectMapper) {
        super(objectMapper);
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    }

    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        request.setAttribute("test", "test");

        byte[] requestBody = inputMessage.getBody().readAllBytes();
        request.setAttribute("requestBody", new ObjectMapper().readValue(requestBody, Map.class));

        return super.read(type, contextClass, new MappingJacksonInputMessage(new PushbackInputStream(new ByteArrayInputStream(requestBody)), inputMessage.getHeaders()));
    }
}
