package com.dmk.coctown.domain.member.controller;

import com.dmk.coctown.common.config.SecurityConfig;
import com.dmk.coctown.domain.member.dto.MemberJoinRequest;
import com.dmk.coctown.domain.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("[VIEW] 컨트롤러 테스트 - 회원")
@Import(SecurityConfig.class) // TODO: 테스트용 시큐리티 설정 파일 생성 필요
@WebMvcTest(MemberController.class)
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @DisplayName("[GET] 회원가입 페이지 - 정상 호출")
    @Test
    void givenNothing_whenRequestingJoinView_thenReturnJoinView() throws Exception {
        // given

        // when & then
        mockMvc.perform(get("/members/join"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("members/join"))
                .andExpect(model().attributeDoesNotExist());

    }

    @DisplayName("[POST] 회원가입 성공 - 회원가입 완료 후 로그인 페이지로 리다이렉트")
    @Test
    void givenMemberInfo_whenJoining_thenRedirectLoginView() throws Exception {
        // given
        String email = "dongmin@email.com";
        String password = "asdf1234";
        String nickname = "dongmin123";

        willDoNothing().given(memberService).join(any(MemberJoinRequest.class));

        // when
        // TODO: 프로필 이미지 업로드 구현 후 post() -> file()로 변경 필요.
        mockMvc.perform(post("/members/join")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("email", email)
                        .param("password", password)
                        .param("nickname", nickname)
                        .with(csrf()) // csrf 토큰 추가
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/members/login"))
                .andExpect(redirectedUrl("/members/login"));

        // then
        then(memberService).should().join(any(MemberJoinRequest.class));
    }

}