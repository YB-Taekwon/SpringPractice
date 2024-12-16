package com.ian.spring_practice.domain;

import lombok.Data;

@Data // getter, setter, toString, hashCode 등 메서드 생성 및 재정의
public class Member {
    private Long id;
    private String name;
}
