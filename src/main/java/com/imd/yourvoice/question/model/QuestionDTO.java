package com.imd.yourvoice.question.model;

import lombok.*;

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
    private long indexNumber;
    private String contents;
    private String emoji;
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
