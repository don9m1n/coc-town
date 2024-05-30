package com.dmk.coctown.domain.member.service;

import com.dmk.coctown.domain.member.dto.MemberJoinRequest;
import com.dmk.coctown.domain.member.model.Member;
import com.dmk.coctown.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void join(MemberJoinRequest request) {

        memberRepository.findByEmail(request.getEmail()).ifPresent(member -> {
            throw new RuntimeException("이미 존재하는 회원입니다.");
        });

        Member member = Member.of(request, passwordEncoder.encode(request.getPassword()));
        memberRepository.save(member);
    }
}
