package gsc.backend.domain;

import gsc.backend.domain.common.BaseEntity;
import gsc.backend.domain.mapping.UserEducation;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Education extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "education_id")
    private Long id;

    @OneToMany(mappedBy = "education", cascade = CascadeType.ALL)
    private List<UserEducation> userEducationList = new ArrayList<>();

    @OneToMany(mappedBy = "education", cascade = CascadeType.ALL)
    private List<EducationImage> educationImageList = new ArrayList<>();

    @OneToMany(mappedBy = "education", cascade = CascadeType.ALL)
    private List<Quiz> quizList = new ArrayList<>();

    // 교육 유형 이름
    private String name;

    // 홈화면 입장 시 쓰일 설명
    private String description;

    // 자세한 설명
    @Column(columnDefinition = "TEXT")
    private String detail;

    // 썸네일 사진
    @Column(columnDefinition = "TEXT")
    private String image;

    // 해당 교육 유형의 전체 문제 개수
    private int totalQuizCount;
}
