package gsc.backend.domain;

import gsc.backend.domain.common.BaseEntity;
import gsc.backend.domain.enums.VideoType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserVideo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_cpr_video_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // 사용자 영상
    @Column(columnDefinition = "TEXT")
    private String videoUrl;

    // 영상 종류
    @Enumerated(EnumType.STRING)
    private VideoType videoType;
}
