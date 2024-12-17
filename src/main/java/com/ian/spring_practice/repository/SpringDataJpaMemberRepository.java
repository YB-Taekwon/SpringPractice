package com.ian.spring_practice.repository;

import com.ian.spring_practice.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 인터페이스만 있을 경우, 해당 인터페이스가 스프링 데이터 JPA를 상속받으면 자동으로 구현체를 생성 및 등록해줌
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    @Override
    Optional<Member> findByName(String name);
}
