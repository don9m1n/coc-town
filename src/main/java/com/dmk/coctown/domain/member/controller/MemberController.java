package com.dmk.coctown.domain.member.controller;

import com.dmk.coctown.domain.member.dto.MemberJoinRequest;
import com.dmk.coctown.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String joinForm(MemberJoinRequest request) {
        return "members/join";
    }

    @PostMapping("/join")
    public String join(MemberJoinRequest request) {

        memberService.join(request);
        return "redirect:/members/login";
    }
}
