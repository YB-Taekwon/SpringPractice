package com.ian.spring_practice.service;

import com.ian.spring_practice.domain.Member;
import com.ian.spring_practice.repository.MemberRepository;
import com.ian.spring_practice.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemoryMemberRepository memberRepository;

    // 테스트 코드에서 서비스에서 실제로 사용하는 리포지토리와 동일한 객체를 사용하기 위해 생성자를 통해 의존성 주입
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = new MemoryMemberRepository(); // 직접 의존성 주입
    }

    // 회원가입
    public Long join(Member member) {
        // 같은 이름의 중복 회원 가입 불가
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> {throw new IllegalStateException("이미 존재하는 회원입니다.");}); // Optional.ifPresent(): null이 아닌 값이 있으면 로직이 동작하는 메서드 (if문 제거 가능)
        isDuplicateName(member);

        memberRepository.save(member);
        return member.getId();
    }

    // 회원가입 시 중복 이름 검사
    private void isDuplicateName(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> { // 반환 타입이 Optional이기 때문에 바로 ifPresent() 사용 가능
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 특정 회원 조회
    public Optional<Member> findMember(Long id) {
        return memberRepository.findById(id);
    }
}
