package gsc.backend.domain;

import gsc.backend.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Choice extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "choice_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    // 선지 번호
    private int choiceNumber;

    // 관련 설명
    @Column(columnDefinition = "TEXT")
    private String description;

    // 이미지 경로
    @Column(columnDefinition = "TEXT")
    private String imageUrl;
}
