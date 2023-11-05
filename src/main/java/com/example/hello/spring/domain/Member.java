package com.example.hello.spring.domain;

public class Member {
  //비즈니스 요구사항 id와 닉네임
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



