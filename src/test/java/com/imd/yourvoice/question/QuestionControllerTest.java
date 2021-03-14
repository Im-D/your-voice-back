package com.imd.yourvoice.question;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imd.yourvoice.common.ResponseDTO;
import com.imd.yourvoice.question.model.QuestionDTO;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(QuestionController.class)
@ExtendWith(MockitoExtension.class)
class QuestionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @ParameterizedTest
    @MethodSource
    void createQuestion(QuestionDTO.CreateRequest input, Map<String, Object> expected) throws Exception {
        mockMvc.perform(post("/question")
                .content(objectMapper.writeValueAsString(input))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expected)));
    }

    @SuppressWarnings("unused")
    static Stream<Arguments> createQuestion() {
        return Stream.of(Arguments.arguments(
                QuestionDTO.CreateRequest.builder()
                        .contents("test")
                        .emoji("test")
                        .createDateTime(LocalDateTime.of(2021, 03, 03, 22, 34))
                        .build(),
                Map.of(
                        "data", QuestionDTO.builder()
                                .contents("test")
                                .emoji("test")
                                .questionLikeDTOs(Collections.emptyList())
                                .createDateTime(LocalDateTime.of(2021, 03, 03, 22, 34))
                                .build(),
                        "isSuccess", "success",
                        "message", "CREATE SUCCESSFUL"
                )
        ));
    }

    @ParameterizedTest
    @MethodSource
    void createQuestion_useJsonString(String input, Map<String, Object> expected) throws Exception {
        mockMvc.perform(post("/question")
                .content(input)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expected)));
    }

    @SuppressWarnings("unused")
    static Stream<Arguments> createQuestion_useJsonString() {
        return Stream.of(Arguments.arguments(
                "{\"contents\": \"test\", \"emoji\": \"test\",\"createDateTime\": \"2021-03-03T22:34:00\"}",
                Map.of(
                        "data", QuestionDTO.builder()
                                .contents("test")
                                .emoji("test")
                                .questionLikeDTOs(Collections.emptyList())
                                .createDateTime(LocalDateTime.of(2021, 03, 03, 22, 34))
                                .build(),
                        "isSuccess", "success",
                        "message", "CREATE SUCCESSFUL"
                )
        ));
    }

    @ParameterizedTest
    @MethodSource
    void createQuestion_useJsonString_unknownProperties(String input, ResponseDTO expected) throws Exception {
        mockMvc.perform(post("/question")
                .content(input)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().json(objectMapper.writeValueAsString(expected)));
    }

    @SuppressWarnings("unused")
    static Stream<Arguments> createQuestion_useJsonString_unknownProperties() {
        return Stream.of(Arguments.arguments(
                "{\"contents\": \"test\", \"emoji\": \"test\",\"createDateTime\": \"2021-03-03 22:34:00\",\"test\" : \"test\"}",
                ResponseDTO.builder()
                        .data(Map.of(
                                "contents", "test",
                                "emoji", "test",
                                "createDateTime", "2021-03-03 22:34:00",
                                "test", "test"
                        )).isSuccess("fail")
                        .message("UnknownRequestProperties Error")
                        .build()
        ));
    }

    @ParameterizedTest
    @MethodSource
    void createQuestion_validationFail(QuestionDTO.CreateRequest input, ResponseDTO expected) throws Exception {
        mockMvc.perform(post("/question")
                .content(objectMapper.writeValueAsString(input))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().json(objectMapper.writeValueAsString(expected)));
    }

    @SuppressWarnings("unused")
    static Stream<Arguments> createQuestion_validationFail() {
        return Stream.of(
                Arguments.arguments(
                        QuestionDTO.CreateRequest.builder()
                                .contents("")
                                .emoji("test")
                                .createDateTime(LocalDateTime.of(2021, 03, 03, 22, 34))
                                .build(),
                        ResponseDTO.builder()
                                .data(QuestionDTO.CreateRequest.builder()
                                        .contents("")
                                        .emoji("test")
                                        .createDateTime(LocalDateTime.of(2021, 03, 03, 22, 34))
                                        .build())
                                .isSuccess("fail")
                                .message("Validation Error")
                                .build()
                ), Arguments.arguments(
                        QuestionDTO.CreateRequest.builder()
                                .contents("메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿")
                                .emoji("test")
                                .createDateTime(LocalDateTime.of(2021, 03, 03, 22, 34))
                                .build(),
                        ResponseDTO.builder()
                                .data(QuestionDTO.CreateRequest.builder()
                                        .contents("메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿")
                                        .emoji("test")
                                        .createDateTime(LocalDateTime.of(2021, 03, 03, 22, 34))
                                        .build())
                                .isSuccess("fail")
                                .message("Validation Error")
                                .build()
                )
        );
    }
}
