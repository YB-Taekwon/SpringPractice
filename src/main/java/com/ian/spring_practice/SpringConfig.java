package com.ian.spring_practice;

import com.ian.spring_practice.repository.JdbcTemplateMemberRepository;
import com.ian.spring_practice.repository.JpaMemberRepository;
import com.ian.spring_practice.repository.MemberRepository;
import com.ian.spring_practice.repository.SpringDataJpaMemberRepository;
import com.ian.spring_practice.service.MemberService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

// 컴포넌트 스캔 없이 스프링 빈 직접 등록
@Configuration
public class SpringConfig {

    // JDBC
    //    @Autowired DataSource dataSource;
//    private final DataSource dataSource;
//
//    @Autowired // 생성자가 1개면 생략 가능
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    // JPA
//    @PersistenceContext
//    private EntityManager em;
//
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    // Spring Data JPA
    // 인터페이스만 있을 경우, 해당 인터페이스가 스프링 데이터 JPA를 상속받으면 자동으로 구현체를 생성 및 등록해줌
    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
}
