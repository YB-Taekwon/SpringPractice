package com.ian.spring_practice.service;

import com.ian.spring_practice.domain.Member;
import com.ian.spring_practice.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional // 테스트 메서드 완료 후 DB RollBack (테스트를 돌려도 DB에 값이 남아있지 않는다.)
// 스프링 + 데이터베이스 + 자바 - 통합 테스트
class MemberServiceIntegrationTest {
    // 외부에서 테스트를 사용할 일이 없기 때문에 필드 기반으로 오토 와이어를 사용하는 게 편리
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

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

}