package com.ian.spring_practice.repository;

import com.ian.spring_practice.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 회원 저장
    // Optional: 있을 수도 있고 없을 수도 있음, 조회한 값에 일치하는 결과가 없을 경우, Optional.empty()를 반환
    Optional<Member> findById(Long id); // id로 회원 조회
    Optional<Member> findByName(String name); // 이름으로 회원 조회
    List<Member> findAll(); // 모든 회원 조회
}
