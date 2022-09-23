package learn.oauth.sociallogin.model.entity;

import learn.oauth.sociallogin.model.dto.MemberDTO;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberId",nullable = false)
    private Long id;

    @Column(name = "username",nullable = false)
    private String username;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "authority")
    @Enumerated(EnumType.STRING)
    private Role role;


    @Column(length = 1000)
    private String refreshToken;



    @Builder
    public Member(String username, String password,Role role) {
        this.username = username;
        this.password = password;
        this.role=role;
    }

    public void changeRefreshToken(String refreshToken){
        System.out.println("토큰이 변경되었습니다");
        this.refreshToken=refreshToken;
    }

    public void destroyRefreshToken(){
        this.refreshToken=null;
    }

    public boolean ChangeMemberState(MemberDTO.ChangeStateDTO changeStateDTO){
        if (changeStateDTO.getUsername()==null&&changeStateDTO.getPassword()==null){
            return false;
        }else {
            this.username=changeStateDTO.getUsername();
            this.password=changeStateDTO.getPassword();
            return true;
        }
    }
    //권한 추가
    public void addMemberAuthority(){
        this.role=Role.MEMBER;
    }

    public void encodeToPassword(PasswordEncoder passwordEncoder){
        this.password=passwordEncoder.encode(password);
    }
}

