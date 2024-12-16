package com.ian.spring_practice.repository;

import com.ian.spring_practice.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    /* Unit Test에서는 메서드 테스트 순서가 보장되지 않기 때문에 하나의 테스트가 끝날 때마다 메모리를 비워줘야 한다.
       따라서, TestCode는 순서나 의존 관계와 관계 없이 설계되어야 한다. */
    @AfterEach // 메서드 하나가 실행이 끝날 때마다 특정 동작을 하는 애너테이션, 콜백 메서드와 비슷한 느낌
    public void afterEach() {
        memberRepository.clear();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("test");

        memberRepository.save(member);

        Member result = memberRepository.findById(member.getId()).get(); // 반환 타입이 Optional이기 때문에 get()으로 값을 꺼내 Member 타입으로 저장
//        Assertions.assertEquals(member, result); // Assertions.assertThat()과 import가 다름 -> import org.junit.jupiter.api.Assertions;
//        Assertions.assertThat(member).isEqualTo(result); // import org.assertj.core.api.Assertions;
        assertThat(member).isEqualTo(result); // static으로 import하면 Assertions 생략 가능
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("test1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("test2");
        memberRepository.save(member2);

        Member result = memberRepository.findByName("test1").get();
        assertThat(member1).isEqualTo(result);
    }

    @Test
    public void findByAll() {
        Member member1 = new Member();
        member1.setName("test1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("test2");
        memberRepository.save(member2);

        List<Member> result = memberRepository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
