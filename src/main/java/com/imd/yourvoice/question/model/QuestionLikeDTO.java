package com.imd.yourvoice.question.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class QuestionLikeDTO {
    private Long id;
    private UUID questionId;
    private LocalDateTime createDateTime;

    public QuestionLike toEntity() {
        return QuestionLike.builder()
                .id(id)
                .question(Question.of(questionId))
                .createDateTime(createDateTime)
                .build();
    }
}
