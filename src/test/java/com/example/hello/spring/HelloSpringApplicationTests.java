package com.example.hello.spring;

import com.example.hello.spring.repository.MemberRepository;
import com.example.hello.spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloSpringApplicationTests {

	MemoryMemberRepository repository = new MemoryMemberRepository();

	@Test
	public void save() {

	}

}
