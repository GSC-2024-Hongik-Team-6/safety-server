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

    @Column(nullable = false)
    private String uuid;

    // 사용자 레벨
    private int level = 0;

    // 레벨을 위한 경험치
    private int exp = 0;

    public static User createUser(String userUuid) {
        User newUser = User.builder()
                .uuid(userUuid)
                .level(0)
                .exp(0)
                .build();
        return newUser;
    }
}
