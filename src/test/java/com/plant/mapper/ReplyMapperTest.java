package com.plant.mapper;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.*;

import com.plant.dto.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ReplyMapperTest {

	private static final Logger log = LoggerFactory.getLogger("ReplyMapperTest.class");
	
	@Autowired
	private MplantReplyMapper mapper;
	
	@Test //리스트
	public void list() {
		
		Criteria cri = new Criteria(1, 5);
		List<MplantReply> list = mapper.getList(cri, 1L);
		for(MplantReply r : list) {
			log.info("댓글내용: " + r.getContent());
		}
	}
	
	@Test //삽입
	public void insert() { //메소드 이름으로 객체가 만들어짐
		
		MplantReply r = new MplantReply();
		r.setContent("안녕하세요");
		r.setId("khj1234");
		r.setMplant_seqno(1L);
		
		mapper.insert(r);
	}

	
	@Test //수정
	public void Update() {
		MplantReply vo = new MplantReply();
		vo.setMpr_seqno(2L);
		vo.setContent("아작스 댓글 수정합니다");
		int count = mapper.update(vo);
		log.info("update count : " + count);
	}
	
	@Test //삭제
	public void delete() {
		int count = mapper.delete(2L);
		log.info("delete count : " + count);
	}

}
