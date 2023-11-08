package com.example.hello.spring.repository;

import com.example.hello.spring.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepository {

  private final JdbcTemplate jdbcTemplate;

  public JdbcTemplateMemberRepository(DataSource dataSource) {
    jdbcTemplate = new JdbcTemplate(dataSource);
  }

  //sql을 직접 작성
  @Override
  public Member save(Member member) {
    SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate); //쿼리문을 만들어준다.
    jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("name", member.getName());
    Number key = jdbcInsert.executeAndReturnKey(new
            MapSqlParameterSource(parameters));
    member.setId(key.longValue());
    return member;
  }

  //디자인 패턴에 탬플릿 메서드 패턴을 사용해서 JDBC 탬플릿이라는
  @Override
  public Optional<Member> findById(Long id) {
    List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id);
    return result.stream().findAny();
  }

  @Override
  public List<Member> findAll() {
    return jdbcTemplate.query("select * from member", memberRowMapper());
  }
  @Override
  public Optional<Member> findByName(String name) {
    List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name);
    return result.stream().findAny();
  }

  private RowMapper<Member> memberRowMapper() {
    return (rs, rowNum) -> {
      Member member = new Member();
      member.setId(rs.getLong("id"));
      member.setName(rs.getString("name"));
      return member;
    };
  }
}
