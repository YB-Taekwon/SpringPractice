package com.ian.spring_practice.service;

import com.ian.spring_practice.domain.Member;
import com.ian.spring_practice.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service
public class MemberService {
    private final MemberRepository memberRepository;

//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입
    @Transactional // JPA 사용 시 필요, join에 값이 들어올 때 모든 데이터 변경이 트랜잭션 안에서 실행되어야 함
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
