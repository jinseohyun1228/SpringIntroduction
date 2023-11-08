package com.example.hello.spring.repository;

import com.example.hello.spring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

  private final EntityManager em;

  // EntityManger를 주입받아야한다.
  public JpaMemberRepository(EntityManager em) {
    this.em = em;
  }
  //인잭션이 뭐지?

  @Override
  public Member save(Member member) {
    em.persist(member);
    return member;
    //insert까지해준다고 함.
  }

  @Override
  public Optional<Member> findById(Long id) {
    Member member = em.find(Member.class, id);
    return Optional.ofNullable(member);
  }

  @Override
  public Optional<Member> findByName(String name) {
    List<Member> result =  em.createQuery("select m from m where m.name = :name", Member.class)
            .setParameter("name",name)
            .getResultList();
    //여러개의 리스트를 가지고 뭐 이런건 PK기반이 아닌 것들은 jpql을 작성해야한다.
    return result.stream().findAny();
  }
  @Override
  public List<Member> findAll() {
    return em.createQuery("select m from Member m", Member.class) //객체를 대상으로 쿼리를 날리는 -> 쿼리문으로 변환해줌
            .getResultList();
  }
}
