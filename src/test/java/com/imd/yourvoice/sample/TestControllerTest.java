package com.imd.yourvoice.sample;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TestService testService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void readHello() throws Exception {
        TestDTO expected = TestDTO.builder().id(1L).name("hello").build();

        given(testService.readHello())
                .willReturn(TestDTO.builder().id(1L).name("hello").build());

        mockMvc.perform(get("/hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(expected.getName())));
    }

    @ParameterizedTest
    @MethodSource
    void createHello(TestDTO input, TestDTO expected) throws Exception {
        given(testService.createHello(input))
                .willReturn(expected);

        mockMvc.perform(post("/hello")
                .content(objectMapper.writeValueAsString(input))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(expected.getName())));
    }

    @SuppressWarnings("unused")
    static Stream<Arguments> createHello() {
        return Stream.of(
                Arguments.arguments(
                        TestDTO.builder().name("hello").build(),
                        TestDTO.builder().name("hello").build())
        );
    }

    @ParameterizedTest
    @MethodSource
    void createHello_validFail(TestDTO input) throws Exception {
        mockMvc.perform(post("/hello")
                .content(objectMapper.writeValueAsString(input))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @SuppressWarnings("unused")
    static Stream<Arguments> createHello_validFail() {
        return Stream.of(
                Arguments.arguments(TestDTO.builder().name("length over 5").build())
        );
    }
}
