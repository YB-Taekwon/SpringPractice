package com.ian.spring_practice.repository;

import com.ian.spring_practice.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {
    // JPA 라이브러리 사용 시, 스프링 부트에서 자동으로 생성
    private final EntityManager em; // DataSource 및 매핑이 내부에서 완료된 상태 (데이터베이스랑 연동된 것을 인젝션만 하면 됨)

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        // JPQL, Entity 객체를 대상으로 쿼리를 작성, EntityManager로 인해 이미 매핑되어 있기 때문에 쿼리만 작성하면 됨
        return em.createQuery("select m from Member m where m.name = :name", Member.class).setParameter("name", name).getResultList().stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
