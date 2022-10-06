package com.plant.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.plant.dto.AdoptReply;
import com.plant.dto.AdoptReplyVO;
import com.plant.dto.Criteria;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class AdoptReplyMapperTest {

	private static final Logger log = LoggerFactory.getLogger("AdoptReplyMapperTest.class");
		
	@Autowired
	private AdoptReplyMapper mapper;
	
	/*
	//등록테스트
	@Test
	public void test() {
		
		AdoptReply ar = new AdoptReply();
		ar.setComment("등록 테스트");
		ar.setAdoptNo("1");
		ar.setId("young");
		
		mapper.insert(ar);
	}
	*/
	
	//조회
	@Test
	public void testList() {
		Criteria cri = new Criteria(1, 5);
		List<AdoptReplyVO> list = mapper.getList(cri, 1L);
		for(AdoptReplyVO r : list) {
			log.info("댓글내용:"+r.getContent());
		}
	}
	
	/*
	//수정
	@Test
	public void testUpdate() {
		AdoptReplyVO vo = new AdoptReplyVO();
		vo.setSeqno(21L);
		vo.setContent("댓글 수정!!");
		int count = mapper.update(vo);
		log.info("update count:" + count);
	}
	
	//삭제
	@Test
	public void testDel() {
		mapper.delete(21L);
	}
	*/
	
}
