package com.example.hello.spring.controller;

import com.example.hello.spring.domain.Member;
import com.example.hello.spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
  // 공용으로 쓸 수 있게
  //private final MemberService memberService = new MemberService();

  private final MemberService memberService;

  @Autowired
  //생성자의 Autowired?? ->
    public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  @GetMapping(value = "/members/new")
  public String createForm() {
    //단순한 이동 기능
    return "members/createMemberForm";
    //탬플릿에서 members/createMemberForm를 찾는다.
  }

  //같은 URL이지만 방식에 따라서 다르게 매핑된다.
  //HTML에서 MemberForm에 있는 name에 매핑해줌.
  @PostMapping("/members/new")
  public String create(MemberForm form) {
    Member member = new Member();
    member.setName(form.getName());

    memberService.join(member);

    return "redirect:/";
  }

  @GetMapping("/members")
  public String list(Model model) {
    List<Member> members = memberService.findMembers();
    model.addAttribute("members", members);
    return "members/memberList";
   }

}
