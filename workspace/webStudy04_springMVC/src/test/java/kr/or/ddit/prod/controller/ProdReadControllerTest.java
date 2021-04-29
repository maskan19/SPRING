package kr.or.ddit.prod.controller;

import static org.junit.Assert.*; /*static import*/
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*; 

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.util.ViewMatcher;
import com.sun.glass.ui.View;

import kr.or.ddit.TestWebAppConfiguration;

@RunWith(SpringRunner.class)
@TestWebAppConfiguration
public class ProdReadControllerTest {

    @Inject
    private WebApplicationContext container;

    private MockMvc mockMvc;
	
	@Before /* 모든 테스트 호출 이전에 실행 */
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(container).build();
		
		
	}

	@Test
	public void testListProdVOHTML() throws Exception {
		mockMvc.perform(get("/prod/prodList.do").param("page", "2"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("pagingVO"))
			.andExpect(view().name("prod/prodList"))
			.andDo(log())
			.andReturn();
	}

	@Test
	public void testListProdVOJSON() throws Exception {
		mockMvc.perform(get("/prod/prodList.do")
				.param("page", "2").accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andDo(log())
			.andReturn();
	}

	@Test
	public void testView() throws Exception {
		mockMvc.perform(get("/prod/prodList.do")).andExpect(status().isBadRequest())
		.andDo(log())
		.andReturn();
		mockMvc.perform(get("/prod/prodList.do").param("what", "P101000001"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("prod"))
		.andExpect(view().name("prod/prodView"))
		.andDo(log())
		.andReturn();
	}

}
