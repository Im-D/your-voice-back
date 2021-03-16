package com.imd.yourvoice.question.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
@Entity
public class Question {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private Long indexNumber;

    private String contents;

    private String emoji;

    private LocalDateTime createDateTime;

    @OneToMany(mappedBy = "question")
    private List<QuestionLike> questionLikes;

    public QuestionDTO toDTO() {
        return QuestionDTO.builder()
                .id(id)
                .indexNumber(indexNumber)
                .contents(contents)
                .emoji(emoji)
                .createDateTime(createDateTime)
                .questionLikeDTOs(questionLikes.stream().map(QuestionLike::toDTO).collect(Collectors.toList()))
                .build();
    }
}
