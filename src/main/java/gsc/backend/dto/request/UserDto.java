package gsc.backend.dto.request;

import gsc.backend.domain.User;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto {

    private String uuid;
    private String level;
    private String exp;

    public User toQuiz(){
        return User.builder()
                .uuid(uuid)
                .level(0)
                .exp(0)
                .build();
    }

}
