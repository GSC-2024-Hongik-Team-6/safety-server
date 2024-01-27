package gsc.backend.domain.mapping;

import gsc.backend.domain.Education;
import gsc.backend.domain.User;
import gsc.backend.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserEducation extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_education_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "education_id")
    private Education education;

    // 해당 유형의 스테이지 전체 개수
    private int stageNum;

    // 사용자가 해결한 스테이지 수
    private int userSolvedStageNum;
}
