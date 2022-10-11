package learn.oauth.sociallogin.api.repository.user;

import learn.oauth.sociallogin.api.entity.user.UserRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshToken,Long> {
    Optional<UserRefreshToken> findByUsername(String username);
    Optional<UserRefreshToken> findByUsernameAndRefreshToken(String username,String refreshToken);
}
