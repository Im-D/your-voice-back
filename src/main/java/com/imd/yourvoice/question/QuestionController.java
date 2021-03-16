package com.imd.yourvoice.question;

import com.imd.yourvoice.common.ResponseDTO;
import com.imd.yourvoice.question.model.QuestionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class QuestionController {

    @PostMapping("/question")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO<QuestionDTO> createQuestion(@RequestBody @Valid QuestionDTO.CreateRequest questionDTO) {
        return ResponseDTO.<QuestionDTO>builder()
                .data(questionDTO.toEntity().toDTO())
                .isSuccess("success")
                .message(HttpStatus.CREATED.getReasonPhrase())
                .build();
    }
}
