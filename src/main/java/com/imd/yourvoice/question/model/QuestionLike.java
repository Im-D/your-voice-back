package com.imd.yourvoice.question.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
@Entity
public class QuestionLike {
    @Id
    private long id;
    @ManyToOne
    private Question question;
    private LocalDateTime createDateTime;
}
