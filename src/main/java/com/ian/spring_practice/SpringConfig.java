package com.ian.spring_practice;

import com.ian.spring_practice.repository.MemberRepository;
import com.ian.spring_practice.repository.MemoryMemberRepository;
import com.ian.spring_practice.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 컴포넌트 스캔 없이 스프링 빈 직접 등록
//@Configuration
//public class SpringConfig {
//
//    @Bean
//    public MemberService memberService() {
//        return new MemberService(memberRepository());
//    }
//
//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
//}
