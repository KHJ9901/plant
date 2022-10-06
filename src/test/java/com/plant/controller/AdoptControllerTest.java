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
import com.plant.dto.Adopt;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml", 
								 "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class AdoptControllerTest {
	
	private static final Logger log = LoggerFactory.getLogger("AdoptControllerTest.class");

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
						.param("Username", "young").param("Password", "11111"))
						.andReturn().getModelAndView().getViewName();
		log.info(rs);
	}
	
	//RestController
	@Test
	public void test2() throws Exception{
		
		Adopt a = new Adopt();
		a.setId("young");
		a.setContent("테스트중입니다");
		a.setStation("서울");
		a.setPname("테스트중입니다");
		a.setWater("테스트중입니다");
		a.setPlace("테스트중입니다");
		a.setMoist("테스트중입니다");
		
		String jsonStr = new Gson().toJson(a);
		
		log.info(jsonStr);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/ad/adopt").
							contentType(MediaType.APPLICATION_JSON).
							content(jsonStr)).andExpect(status().is(200));
	}
	
}
