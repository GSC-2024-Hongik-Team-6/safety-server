package gsc.backend.domain;

import gsc.backend.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Badge extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "badge_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // 뱃지 이름
    private String name;

    // 뱃지 설명
    @Column(columnDefinition = "TEXT")
    private String description;

    // 뱃지 이미지
    @Column(columnDefinition = "TEXT")
    private String imageUrl;
}
