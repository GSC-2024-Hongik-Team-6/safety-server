package gsc.backend.domain;

import gsc.backend.domain.common.BaseEntity;
import gsc.backend.domain.mapping.UserEducation;
import gsc.backend.domain.mapping.UserQuiz;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Badge> badgeList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserQuiz> userQuizList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserEducation> userEducationList = new ArrayList<>();

    // 사용자 레벨
    private int level;

    // 레벨을 위한 경험치
    private int exp;
}
