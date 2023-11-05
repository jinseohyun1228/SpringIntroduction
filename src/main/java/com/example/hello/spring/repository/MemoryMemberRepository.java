package com.example.hello.spring.repository;

import com.example.hello.spring.domain.Member;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository {

  private static Map<Long, Member> store = new HashMap<>();
  private static Long sequence = 0L;

  //저장하는 메서드
  @Override
  public Member save(Member member) {
    //Member의 ID 셋팅
    member.setId(++sequence);
    store.put(member.getId(), member);
    return member;
  }

  @Override
  public Optional<Member> findById(Long id) {
    // 없을수도 있잖아요? 그러면 Null 반환 -> 이걸 감쌀수있도록!!
    return Optional.ofNullable(store.get(id));
  }

  @Override
  public Optional<Member> findByName(String name) {
    return store.values().stream()
            .filter(member -> member.getName().equals(name))
            .findAny();
  }

  @Override
  public List<Member> findAll() {
    return new ArrayList<>(store.values());
  }

  public void clearStore() {
    store.clear();
  }
}
