package com.plant.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.plant.dto.Qna;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml", 
								 "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class QnaControllerTest {
	private static final Logger log = LoggerFactory.getLogger("QnaControllerTest.class");

	@Autowired
	private WebApplicationContext wac ;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		log.info("mockMvc setup..."); 
	}
	
	@Test
	public void test() throws Exception{
		
		String rs = mockMvc.perform(MockMvcRequestBuilders.post("/login")
						.param("Username", "wotls3574").param("Password", "357400"))
						.andReturn().getModelAndView().getViewName();
		log.info(rs);
	}
	
	//RestController
	@Test
	public void test2() throws Exception{
		
		Qna q = new Qna();
		q.setId("wotls3574");
		q.setContent("테스트");
		q.setWdate("2022-10-03");
		
		
		String jsonStr = new Gson().toJson(q);
		
		log.info(jsonStr);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/qn/qna").
							contentType(MediaType.APPLICATION_JSON).
							content(jsonStr)).andExpect(status().is(200));
	}
}
