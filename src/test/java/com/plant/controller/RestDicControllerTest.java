package com.plant.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.*;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.plant.dto.*;
import com.plant.mapper.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml", 
								   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class RestDicControllerTest {

	private static final Logger log = LoggerFactory.getLogger("DictionaryControllerTest.class");
	
	@Autowired
	private WebApplicationContext wac; //context 역할
	
	private MockMvc mockMvc; //요청, 응답 처리 , 서블릿 역할
	
	private DictionaryMapper mapper;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		log.info("mockMvc setup...");
	}
	
	//RestDicController ------- 검색 기능
	@Test
	public void test() throws Exception {
		Dictionary d = new Dictionary();
		d.setRn(1);
		d.setEname("goldclister");
		
		String jsonStr = new Gson().toJson(d);
		log.info(jsonStr);
		mockMvc.perform(MockMvcRequestBuilders.post("/dicsearch/dictionary")
						.contentType(MediaType.APPLICATION_JSON)
						.contentType(jsonStr)).andExpect(status().is(200));
	}


}
