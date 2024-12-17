package com.ian.spring_practice.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity // JPA 사용 시, DB Table과 매핑해주는 애너테이션
@Data // getter, setter, toString, hashCode 등 메서드 생성 및 재정의
public class Member {
    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id 자동 부여

//    @Column(name = "member_id") // DB 컬럼명이 member_id, DB의 컬럼명과 동일할 경우 생략 가능
    private Long id;
    private String name;
}
