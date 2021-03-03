package com.imd.yourvoice.question.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class QuestionLikeDTO {
    private Long id;
    private Question question;
    private LocalDateTime createDateTime;

    public QuestionLike toEntity() {
        return QuestionLike.builder()
                .id(id)
                .question(question)
                .createDateTime(createDateTime)
                .build();
    }
}
