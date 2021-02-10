package com.imd.yourvoice.sample;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestControllerTest_integration {

    @LocalServerPort
    private int port;

    private String uri;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void setUri() {
        uri = "http://localhost:" + port;
    }

    @Test
    void readHello() {
        assertThat(restTemplate.getForObject(uri + "/hello", TestDTO.class))
                .isEqualTo(TestDTO.builder().id(1L).name("hello").build());
    }

    @ParameterizedTest
    @MethodSource
    void createHello(TestDTO input, TestDTO expected) {
        assertThat(this.restTemplate.postForObject(uri + "/hello", input, TestDTO.class).getName())
                .isEqualTo(expected.getName());
    }

    @SuppressWarnings("unused")
    static Stream<Arguments> createHello() {
        return Stream.of(
                Arguments.arguments(
                        TestDTO.builder().name("hello").build(),
                        TestDTO.builder().id(1L).name("hello").build())
        );
    }
}
