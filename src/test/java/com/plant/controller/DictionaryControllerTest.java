package com.plant.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.*;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.plant.dto.*;
import com.plant.mapper.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml", 
								   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class DictionaryControllerTest {

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
	
	//DicController
	@Test //전체 목록
	public void list() {
		
		try {
			Criteria cri = new Criteria();
			String rs = mockMvc.perform(MockMvcRequestBuilders.post("/dic/dictionary")
						).andReturn().getModelAndView().getViewName();
			
			log.info("리스트 출력 : " + rs);
			//ModelAndView 은값집어 넣고 페이지 설정 할 때
			//model 은 값만 가져옴
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test //실내용 목록
	public void inList() {
		
		try {
			String rs = mockMvc.perform(MockMvcRequestBuilders.post("/dic/dictionaryIn")
						).andReturn().getModelAndView().getViewName();
			log.info("리스트 출력 : " + rs);
			//ModelAndView 은값집어 넣고 페이지 설정 할 때
			//model 은 값만 가져옴
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test //야외용 목록
	public void outList() {
		
		try {
			String rs = mockMvc.perform(MockMvcRequestBuilders.post("/dic/dictionaryOut")
						).andReturn().getModelAndView().getViewName();
			log.info("리스트 출력 : " + rs);
			//ModelAndView 은값집어 넣고 페이지 설정 할 때
			//model 은 값만 가져옴
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test //회원식물 목록
	public void mpList() {
		
		try {
			String rs = mockMvc.perform(MockMvcRequestBuilders.post("/dic/mplant")
						).andReturn().getModelAndView().getViewName();
			log.info("리스트 출력 : " + rs);
			//ModelAndView 은값집어 넣고 페이지 설정 할 때
			//model 은 값만 가져옴
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test // 사전 디테일-----------미완성
	public void dictionDetail() {
		
		try {
			String rs = mockMvc.perform(MockMvcRequestBuilders.post("/dic/dicDetail")
						).andReturn().getModelAndView().getViewName();
			log.info("디테일 출력 : " + rs);
			//ModelAndView 은값집어 넣고 페이지 설정 할 때
			//model 은 값만 가져옴
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test // 회원 식물 디테일 ----------미완성
	public void mplantDetail() {

		try {
			String rs = mockMvc.perform(MockMvcRequestBuilders.post("/dic/mplantDetail")
						).andReturn().getModelAndView().getViewName();
			log.info("디테일 출력 : " + rs);
			//ModelAndView 은값집어 넣고 페이지 설정 할 때
			//model 은 값만 가져옴
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test // 사전 삽입 테스트 --------미완성
	public void insertDiction() { //메소드 이름으로 객체가 만들어짐
	
		Dictionary d = new Dictionary();
		DicImg di = new DicImg();
		try {
			String rs = mockMvc.perform(MockMvcRequestBuilders.post("/dic/insertDiction")
						).andReturn().getModelAndView().getViewName();
			log.info("사전 삽입 테스트 : " + rs);
			//ModelAndView 은값집어 넣고 페이지 설정 할 때
			//model 은 값만 가져옴
			mapper.dictionInsert(d, di);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
//	@Test // 회원 식물 삽입 테스트 --------미완성
//	public void insertMplant() { //메소드 이름으로 객체가 만들어짐
//		
//		MplantVO m = new MplantVO();
//		MplantImg mi = new MplantImg();
//		try {
//			String rs = mockMvc.perform(MockMvcRequestBuilders.post("/dic/insertMplant")
//						).andReturn().getModelAndView().getViewName();
//			log.info("회원식물 삽입 테스트 : " + rs);
//			//ModelAndView 은값집어 넣고 페이지 설정 할 때
//			//model 은 값만 가져옴
//			m.setMoist("컨트롤러 회원식물 습도 추가 테스트");
//			mi.setFileName("컨트롤러 회원식물 사진 이름 추가 테스트");
//			mapper.mplantInsert(m, mi);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	@Test // 사전 수정 테스트 ------ 미완성
	public void updateDiction() { //메소드 이름으로 객체가 만들어짐
		
		Dictionary d = new Dictionary();
		DicImg di = new DicImg();
		try {
			String rs = mockMvc.perform(MockMvcRequestBuilders.post("/dic/updateDiction")
						).andReturn().getModelAndView().getViewName();
			log.info("사전 수정  테스트 : " + rs);
			//ModelAndView 은값집어 넣고 페이지 설정 할 때
			//model 은 값만 가져옴
			mapper.dictionUpdate(d, di);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test // 회원식물 수정 테스트 ------ 미완성
	public void updateMplant() { //메소드 이름으로 객체가 만들어짐
		
		MplantVO m = new MplantVO();
		MplantImg mi = new MplantImg();
		try {
			String rs = mockMvc.perform(MockMvcRequestBuilders.post("/dic/updateMplant")
						).andReturn().getModelAndView().getViewName();
			log.info("회원식물 삽입 테스트 : " + rs);
			//ModelAndView 은값집어 넣고 페이지 설정 할 때
			//model 은 값만 가져옴
			mapper.mplantUpdate(m, mi);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test // 사전 삭제 테스트 ------ 미완성
	public void deleteDiction() { //메소드 이름으로 객체가 만들어짐
		
		String seqno = null;
		try {
			String rs = mockMvc.perform(MockMvcRequestBuilders.post("/dic/deleteDiction")
						).andReturn().getModelAndView().getViewName();
			log.info("사전 삭제  테스트 : " + rs);
			//ModelAndView 은값집어 넣고 페이지 설정 할 때
			//model 은 값만 가져옴
			mapper.dictionDelete(seqno);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test // 회원식물 삭제 테스트 ------ 미완성
	public void deleteMplant() { //메소드 이름으로 객체가 만들어짐
		
		String seqno = null;
		try {
			String rs = mockMvc.perform(MockMvcRequestBuilders.post("/dic/deleteMplant")
					).andReturn().getModelAndView().getViewName();
			log.info("회원식물 삭제  테스트 : " + rs);
			//ModelAndView 은값집어 넣고 페이지 설정 할 때
			//model 은 값만 가져옴
			mapper.mplantDelete(seqno);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
