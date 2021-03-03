package com.imd.yourvoice.question;

import com.imd.yourvoice.common.ResponseDTO;
import com.imd.yourvoice.question.model.QuestionDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class QuestionController {
    @PostMapping("/question")
    public ResponseDTO<QuestionDTO> createQuestion(@RequestBody@Valid QuestionDTO questionDTO) {
        return ResponseDTO.<QuestionDTO>builder()
                .data(questionDTO)
                .isSuccess("success")
                .message("CREATE SUCCESSFUL")
                .build();
    }
}
