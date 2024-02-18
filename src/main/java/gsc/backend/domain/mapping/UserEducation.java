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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "education_id")
    private Education education;

    // 사용자가 해결한 해당 교육 유형의 문제 수
    private int userSolvedQuizCount = 0;

    public static UserEducation createUserEduaction(User user, Education education) {
        UserEducation userEducation = UserEducation.builder()
                .user(user)
                .education(education)
                .userSolvedQuizCount(0)
                .build();
        return userEducation;
    }

    public void updateQuizCount() { this.userSolvedQuizCount++; }
}
