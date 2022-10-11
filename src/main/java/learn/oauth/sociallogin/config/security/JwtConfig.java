package learn.oauth.sociallogin.config.security;

import learn.oauth.sociallogin.oauth.token.AuthTokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class JwtConfig {
    @Value("${jwt.secret}")
    private String secret;

    @Bean
    public AuthTokenProvider jwtProvider() {
        return new AuthTokenProvider(secret);
    }
}
