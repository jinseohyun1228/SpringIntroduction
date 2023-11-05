package com.example.hello.spring;

import com.example.hello.spring.repository.MemberRepository;
import com.example.hello.spring.repository.MemoryMemberRepository;
import com.example.hello.spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SpringConfig {
  @Bean
  public MemberService memberService() {
    //스프링 빈에 등록
    return new MemberService(memberRepository());
  }
  @Bean
  public MemberRepository memberRepository() {
    return new MemoryMemberRepository();
  }
}
