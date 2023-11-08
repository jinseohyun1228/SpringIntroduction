package com.example.hello.spring.service;

import com.example.hello.spring.domain.Member;
import com.example.hello.spring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {

  /*
  @BeforeEach
  public void beforEach() {
    //DI..!!
    memberRepository = new MemoryMemberRepository();
    memberService = new MemberService(memberRepository);
    //직접 리파지토리를 넣는 구문!
  }
  */

  /************************
   clear 안해도 된다.  Aftereach안해도 된다.
   왜?
   //@Transactional 이게 테스트 코드에 있으면 자동으로 ⭐롤백을 해준다.
   테스트 전용 DB를 만든다고 한다.
  **************************/

  @Autowired MemberService memberService;
  @Autowired MemberRepository memberRepository; //인터페이스로
  @Test
  public void 회원가입() throws Exception {
    //Given
    Member member = new Member();
    member.setName("hello");
    //When
    Long saveId = memberService.join(member);
    //Then
    Member findMember = memberRepository.findById(saveId).get();
    assertEquals(member.getName(), findMember.getName());
  }
  @Test
  public void 중복_회원_예외() throws Exception {
    //Given
    Member member1 = new Member();
    member1.setName("spring");
    Member member2 = new Member();
    member2.setName("spring");
    //When
    memberService.join(member1);
    IllegalStateException e = assertThrows(IllegalStateException.class,
            () -> memberService.join(member2));//예외가 발생해야 한다.
    assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
  }
}
