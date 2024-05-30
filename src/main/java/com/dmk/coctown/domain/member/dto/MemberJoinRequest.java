package com.dmk.coctown.domain.member.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
public class MemberJoinRequest {

    private String email;
    private String password;
    private String nickname;
    private MultipartFile profileImg;

    public static MemberJoinRequest of(String email, String password, String nickname, MultipartFile profileImg) {
        return MemberJoinRequest.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .profileImg(profileImg)
                .build();
    }
}
