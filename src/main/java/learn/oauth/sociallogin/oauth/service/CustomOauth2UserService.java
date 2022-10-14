package learn.oauth.sociallogin.oauth.service;

import learn.oauth.sociallogin.api.entity.user.User;
import learn.oauth.sociallogin.api.entity.user.dto.UserDTO;
import learn.oauth.sociallogin.api.repository.user.UserRepository;
import learn.oauth.sociallogin.oauth.entity.ProviderType;
import learn.oauth.sociallogin.oauth.entity.RoleType;
import learn.oauth.sociallogin.oauth.entity.UserPrincipal;
import learn.oauth.sociallogin.oauth.info.OAuth2UserInfo;
import learn.oauth.sociallogin.oauth.info.OAuth2UserInfoFactory;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOauth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        try {
            return this.process(userRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }

    }
    private OAuth2User process(OAuth2UserRequest userRequest, OAuth2User user) {
        ProviderType providerType = ProviderType.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());

        OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOauth2UserInfo(providerType, user.getAttributes());
        User savedUser = userRepository.findByUsername(userInfo.getUsername()).get();

        if (savedUser != null) {
            if (providerType != savedUser.getProviderType()) {
                throw new OAuthProviderMissMatchException(
                        "Looks like you're signed up with " + providerType +
                                " account. Please use your " + savedUser.getProviderType() + " account to login."
                );
            }
            updateUser(savedUser, userInfo);
        } else {
            savedUser = createUser(userInfo, providerType);
        }

        return UserPrincipal.create(savedUser, user.getAttributes());
    }
    private User createUser(OAuth2UserInfo userInfo, ProviderType providerType) {
        User user = new User(
                userInfo.getUsername(),
                userInfo.getNickName(),
                userInfo.getEmail(),
                "Y",
                userInfo.getImageUrl(),
                providerType,
                RoleType.USER
        );

        return userRepository.saveAndFlush(user);
    }

    private User updateUser(User user, OAuth2UserInfo userInfo) {
        if (userInfo.getNickName() != null && !user.getUsername().equals(userInfo.getNickName())) {
            user.setNickname(userInfo.getNickName());
        }

        if (userInfo.getImageUrl() != null && !user.getProfileImageUrl().equals(userInfo.getImageUrl())) {
            user.setProfileImageUrl(userInfo.getImageUrl());
        }

        return user;
    }



}
