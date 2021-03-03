package com.imd.yourvoice.question;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    void createQuestion(QuestionDTO input, Map<String, Object> expected) throws Exception {
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
                QuestionDTO.builder()
                        .contents("test")
                        .emoji("test")
                        .createDateTime(LocalDateTime.of(2021, 03, 03, 22, 34))
                        .build(),
                Map.of(
                        "data", QuestionDTO.builder()
                                .contents("test")
                                .emoji("test")
                                .createDateTime(LocalDateTime.of(2021, 03, 03, 22, 34))
                                .build(),
                        "isSuccess", "success",
                        "message", "CREATE SUCCESSFUL"
                )
        ));
    }

    @ParameterizedTest
    @MethodSource
    void createQuestion_validationFail(QuestionDTO input, Map<String, Object> expected) throws Exception {
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
                        QuestionDTO.builder()
                                .contents("")
                                .emoji("test")
                                .createDateTime(LocalDateTime.of(2021, 03, 03, 22, 34))
                                .build(),
                        Map.of(
                                "data", QuestionDTO.builder()
                                        .contents("")
                                        .emoji("test")
                                        .createDateTime(LocalDateTime.of(2021, 03, 03, 22, 34))
                                        .build(),
                                "isSuccess", "fail",
                                "message", "Validation Error"
                        )
                ), Arguments.arguments(
                        QuestionDTO.builder()
                                .contents("메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿")
                                .emoji("test")
                                .createDateTime(LocalDateTime.of(2021, 03, 03, 22, 34))
                                .build(),
                        Map.of(
                                "data", QuestionDTO.builder()
                                        .contents("메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿메세지메세지메세지굿")
                                        .emoji("test")
                                        .createDateTime(LocalDateTime.of(2021, 03, 03, 22, 34))
                                        .build(),
                                "isSuccess", "fail",
                                "message", "Validation Error"
                        )
                )
        );
    }
}
