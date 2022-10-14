package learn.oauth.sociallogin.api.entity.user.dto;

import learn.oauth.sociallogin.api.entity.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class OnlyUserNickNameDTO{
        private String nickname;
    }
}
