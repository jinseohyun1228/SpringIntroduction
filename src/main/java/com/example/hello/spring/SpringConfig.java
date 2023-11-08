package com.example.hello.spring;

import com.example.hello.spring.aop.TimeTraceAop;
import com.example.hello.spring.repository.MemberRepository;
import com.example.hello.spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

//  private final DataSource dataSource;
//  public SpringConfig(DataSource dataSource) {
//    this.dataSource = dataSource;
//  }

//  private EntityManager em;
//
//  public SpringConfig(EntityManager em) {
//    this.em = em;
//  }
  private final MemberRepository memberRepository;

  @Autowired
  public SpringConfig(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Bean
  public MemberService memberService() {
    //스프링 빈에 등록
    return new MemberService(memberRepository);
  }
//  @Bean
//  public MemberRepository memberRepository() {
//    //return new JdbcMemberRepository(dataSource);
//    //return new JdbcTemplateMemberRepository(dataSource);
//    //return new JpaMemberRepository(em);
//  }

  @Bean //직접 등록😊
  public TimeTraceAop timeTraceAop() {
    return new TimeTraceAop();
  }
}
