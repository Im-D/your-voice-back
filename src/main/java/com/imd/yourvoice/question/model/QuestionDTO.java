package com.imd.yourvoice.question.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class QuestionDTO {
    private UUID id;

    private Long indexNumber;

    @NotEmpty
    @Size(max = 140)
    private String contents;

    private String emoji;

    @JsonSerialize(using= LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDateTime;

    private List<QuestionLikeDTO> questionLikeDTOs;

    public Question toEntity() {
        return Question.builder()
                .id(id)
                .indexNumber(indexNumber)
                .contents(contents)
                .emoji(emoji)
                .createDateTime(createDateTime)
                .questionLikes(questionLikeDTOs.stream().map(QuestionLikeDTO::toEntity).collect(Collectors.toList()))
                .build();
    }
}
