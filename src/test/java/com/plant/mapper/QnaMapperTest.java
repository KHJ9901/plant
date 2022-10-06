package com.plant.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.plant.dto.Criteria;
import com.plant.dto.QnaReplyVo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class QnaMapperTest {

	private static final Logger log = LoggerFactory.getLogger("ReplyMapperTest.class");
	
	@Autowired
	private QnaReplyMapper qrm;
	
	/* 댓글 등록 */
//	@Test
//	public void test() {
//		QnaReplyVo r = new QnaReplyVo();
//		r.setSeqno(2L);
//		r.setContent("안녕하세요");
//		r.setId("wotls3574");
//		
//		qrm.insert(r);
//	}
	
	
	/* 댓글 리스트 */
	@Test
	public void testList() {
		Criteria cri = new Criteria(1, 5);
		List<QnaReplyVo> list = qrm.getList(cri, 2L);
		for(QnaReplyVo r : list) {
			log.info("댓글내용:" + r.getContent());
		}
	}
	
	/* 댓글 수정 */
//	@Test
//	public void testUpdate() {
//		QnaReplyVo vo = new QnaReplyVo();
//		vo.setSeqno(3L);
//		vo.setContent("댓글 수정합니다");
//		int count = qrm.update(vo);
//		log.info("update count:" + count);
//	}
	
	
	/* 댓글 삭제 
	@Test
	public void testDelete() {
		qrm.delete(22L);
	}
	*/
}
