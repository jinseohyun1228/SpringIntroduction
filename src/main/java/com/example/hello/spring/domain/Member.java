package com.example.hello.spring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity //JPA가 관리하는 엔티티
public class Member {
  //비즈니스 요구사항 id와 닉네임

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)//DB가 알아서 선택
  private Long id;

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}



