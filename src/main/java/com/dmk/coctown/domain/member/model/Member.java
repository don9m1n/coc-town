package com.dmk.coctown.domain.member.model;

import com.dmk.coctown.common.model.BaseEntity;
import com.dmk.coctown.domain.member.dto.MemberJoinRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    private String password;

    private String nickname;

    private String profileImg;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    @Builder
    private Member(String email, String password, String nickname, String profileImg, MemberRole memberRole) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.profileImg = profileImg;
        this.memberRole = memberRole;
    }

    public static Member of(MemberJoinRequest request, String encodedPassword) {
        return Member.builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .nickname(request.getNickname())
                .memberRole(MemberRole.MEMBER)
                .profileImg(null) // TODO: 프로필 이미지 업로드 구현시 수정 필요.
                .build();
    }

}
