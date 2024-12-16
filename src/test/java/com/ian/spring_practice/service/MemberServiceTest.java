package com.ian.spring_practice.service;

import com.ian.spring_practice.domain.Member;
//import org.junit.jupiter.api.Assertions;
import static org.assertj.core.api.Assertions.*;

import com.ian.spring_practice.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    // 실행되기 전에 설정되는 부분
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository); // 서비스에서 사용되는 리포지토리와 동일한 리포지토리
    }

    @AfterEach
    void afterEach() {
        memberRepository.clear();
    }

    @Test
    void 회원가입() { // 테스트 코드는 빌드에 포함되지 않기 때문에 한글 사용 가능 (더 직관적임)
        // given: 주어진 상황
        Member member = new Member();
        member.setName("test");

        // when: 실핼한 동작
        Long id = memberService.join(member);

        // then: 나와야 하는 결과
        Member findMember = memberService.findMember(id).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복회원예외() {
        // given
        Member member1 = new Member();
        member1.setName("test");

        Member member2 = new Member();
        member2.setName("test");

        // when
        memberService.join(member1);
//        try {
//            memberService.join(member2);
//            fail(); // 만약 예외가 발생하지 않으면 fail()에서 실패를 명시적으로 호출
//        }catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));// memberService.join(member2) 로직 실행 시, IllegalStateException 예외가 발생해야 한다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        // then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findMember() {
    }
}