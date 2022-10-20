package learn.oauth.sociallogin.oauth.service;

import learn.oauth.sociallogin.api.entity.user.User;
import learn.oauth.sociallogin.api.repository.user.UserRepository;
import learn.oauth.sociallogin.oauth.entity.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).get();
        if (user==null){
            throw new UsernameNotFoundException("Can not find Username");
        }

        return UserPrincipal.create(user);
    }
}
