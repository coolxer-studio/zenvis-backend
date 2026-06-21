package com.coolxer.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class WebServiceApplicationTests {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	void contextLoads() {

		stringRedisTemplate.opsForHash().entries("1");
		System.out.println(1);

	}

}
