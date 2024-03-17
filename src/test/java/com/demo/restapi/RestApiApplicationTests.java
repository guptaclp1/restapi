package com.demo.restapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class RestApiApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;



}
