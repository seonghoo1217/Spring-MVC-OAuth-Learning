package learn.oauth.sociallogin.api.service.user;

import learn.oauth.sociallogin.api.entity.user.User;
import learn.oauth.sociallogin.api.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUser(String username) {
        return userRepository.findByUsername(username).get();
    }
}
