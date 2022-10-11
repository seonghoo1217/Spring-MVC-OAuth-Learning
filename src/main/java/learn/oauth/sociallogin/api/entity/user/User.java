package learn.oauth.sociallogin.api.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import learn.oauth.sociallogin.api.entity.BaseEntity;
import learn.oauth.sociallogin.oauth.entity.ProviderType;
import learn.oauth.sociallogin.oauth.entity.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USERS")
public class User extends BaseEntity {
    @JsonIgnore
    @Id
    @Column(name = "USER_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "USER_ID",length = 64,unique = true)
    @NotNull
    @Size(max = 64)
    private String username;

    @Column(name = "USER_NAME",length = 100)
    @NotNull
    @Size(max = 100)
    private String nickname;

    @JsonIgnore
    @Column(name = "PASSWORD", length = 128)
    @NotNull
    @Size(max = 128)
    private String password;

    @Column(name = "EMAIL", length = 512, unique = true)
    @NotNull
    @Size(max = 512)
    private String email;

    @Column(name = "EMAIL_VERIFIED_YN", length = 1)
    @NotNull
    @Size(min = 1, max = 1)
    private String emailVerifiedYn;

    @Column(name = "PROFILE_IMAGE_URL", length = 512)
    @NotNull
    @Size(max = 512)
    private String profileImageUrl;

    @Column(name = "PROVIDER_TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    @NotNull
    private ProviderType providerType;

    @Column(name = "ROLE_TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    @NotNull
    private RoleType roleType;

    public User(
            @NotNull @Size(max = 64) String username,
            @NotNull String nickname,
            @NotNull String password,
            @NotNull String email,
            @NotNull String emailVerifiedYn,
            @NotNull String profileImageUrl,
            @NotNull ProviderType providerType,
            @NotNull RoleType roleType
    ) {
        this.username = username;
        this.nickname = nickname;
        this.password = "NO_PASS";
        this.email = email !=null ? email :"NO_EMAIL";
        this.emailVerifiedYn = emailVerifiedYn;
        this.profileImageUrl = profileImageUrl;
        this.providerType = providerType;
        this.roleType = roleType;
    }
}
