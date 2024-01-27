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

    // 교육 유형 이름
    private String name;

    // 관련
    @Column(columnDefinition = "TEXT")
    private String description;

    // 대표 사진
    @Column(columnDefinition = "TEXT")
    private String image;
}
