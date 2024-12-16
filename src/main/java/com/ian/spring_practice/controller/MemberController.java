package com.ian.spring_practice.controller;


import com.ian.spring_practice.domain.Member;
import com.ian.spring_practice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // 컴포넌트 스캔을 이용한 자동 스프링 빈 생성
public class MemberController {
    private final MemberService memberService;

    @Autowired // 의존성을 주입시켜주는 애너테이션, 스프링 빈에 등록되어 있는 객체를 연결시켜주는 역할 (Controller - Service - Repository)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm () {
        return "members/createMemberForm";
    }

    @PostMapping("members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
