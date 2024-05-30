package com.dmk.coctown.domain.member.service;

import com.dmk.coctown.domain.member.dto.MemberJoinRequest;
import com.dmk.coctown.domain.member.model.Member;
import com.dmk.coctown.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

@DisplayName("비즈니스 로직 테스트 - 회원")
@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    private MemberService sut;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @DisplayName("회원 정보를 입력하면, 회원가입이 성공한다.")
    @Test
    void givenMemberInfo_whenJoining_thenSaveMember() {

        // given
        MemberJoinRequest request = MemberJoinRequest.of("test1@email.com", "asdf1234", "test1", null);
        given(memberRepository.findByEmail(anyString())).willReturn(Optional.empty());
        given(passwordEncoder.encode(anyString())).willReturn("encrypt_password");
        given(memberRepository.save(any(Member.class))).willReturn(mock(Member.class));

        // when
        sut.join(request);

        // then
        then(memberRepository).should().findByEmail(anyString());
        then(passwordEncoder).should().encode(anyString());
        then(memberRepository).should().save(any(Member.class));
    }

    @DisplayName("이미 가입된 이메일이 있으면, 예외를 던진다.")
    @Test
    void givenDuplicatedEmail_whenJoining_thenThrowException() {

        // given
        String duplicatedEmail = "test1@email.com";
        MemberJoinRequest request = MemberJoinRequest.of("test1@email.com", "asdf1234", "test1", null);
        given(memberRepository.findByEmail(duplicatedEmail)).willReturn(Optional.of(mock(Member.class)));

        // when
        Throwable t = catchThrowable(() -> sut.join(request));

        // then
        assertThat(t)
                .isInstanceOf(RuntimeException.class)
                .hasMessage("이미 존재하는 회원입니다.");

        then(memberRepository).should().findByEmail(duplicatedEmail);
    }

}