package com.example.hello.spring.repository;

import com.example.hello.spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository { //<T, ID>
  //JpaRepository
  //JPQL select m from Member m where m.name = ? 로 쿼리를 짜준다. 오.. 이름이 중요?
  //인터페이스 이름으로도 해결이 된다⁉ ️
  @Override
  Optional<Member> findByName(String name);

}
