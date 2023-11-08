package com.example.hello.spring.service;

import com.example.hello.spring.domain.Member;
import com.example.hello.spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

  MemberService memberService;
  MemoryMemberRepository memberRepository;

  /*
  MemberService memberService = new MemberService();
  MemoryMemberRepository memberRepository = new MemoryMemberRepository();
  +
  memberRepository.clearStore(); 를 쓰면 좋지 않은 이유?!!
  -> memberService에서 이미 memberRepository를 사용하고 있고,
  memberRepository.clearStore()의 memberRepository는 다른 인스턴스 (static이라서 가능하다고 함)
  */

  //태스트를 위해서
  @BeforeEach
  public void beforEach() {
    //DI..!!
    memberRepository = new MemoryMemberRepository();
    memberService = new MemberService(memberRepository);
    //직접 리파지토리를 넣는 구문!
  }

  @AfterEach
  public void afterEach() {
    memberRepository.clearStore();
  }

  @Test
  void 회원가입() {
    //givem : 데이터 부분
    Member member = new Member();
    member.setName("spring");

    //when : 무엇을 검증
    Long saveID = memberService.join(member);

    //them : 검증 부분
    Member findMember = memberService.findOne(saveID).get();
    assertThat(member.getName()).isEqualTo(findMember.getName());
  }

  //테스트는 정상 플로우보다도 예외 플로우가 중요하다. 예외가 터지는 상황도 봐야한다.
  @Test
  public void 중복_회원_예외() {
    //giver
    Member member1 = new Member();
    member1.setName("spring");

    Member member2 = new Member();
    member2.setName("spring");


    //when
    memberService.join(member1);
    IllegalStateException e = assertThrows(IllegalStateException.class, ()-> memberService.join(member2));
  /*
    try {
      memberService.join(member2);
      fail();
    }catch (IllegalStateException e) {
      assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
  */

    //then
    assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
  }


  @Test
  void findMembers() {
  }

  @Test
  void findOne() {
  }
}