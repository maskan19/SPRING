package kr.or.ddit.member.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*; 

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import kr.or.ddit.TestWebAppConfiguration;
@RunWith(SpringRunner.class)
@TestWebAppConfiguration
public class IdCheckControllerTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testDoPost() {
		fail("Not yet implemented");
	}

}
