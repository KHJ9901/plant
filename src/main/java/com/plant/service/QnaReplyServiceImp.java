package com.plant.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plant.dto.Criteria;
import com.plant.dto.QnaPageDTO;
import com.plant.dto.QnaReplyVo;
import com.plant.mapper.QnaReplyMapper;

@Service
public class QnaReplyServiceImp implements QnaReplyService {

	private static final Logger log = LoggerFactory.getLogger(QnaReplyServiceImp.class);
	
	@Autowired
	private QnaReplyMapper rm;
	
	@Override
	public int register(QnaReplyVo reply) {
		log.info("reply regisetr service called.." + reply);
		return rm.insert(reply);
	}

	@Override
	public List<QnaReplyVo> getList(Criteria cri, Long bno) {
		return rm.getList(cri, bno);
	}

	@Override
	public QnaReplyVo get(Long rno) {
		return rm.read(rno);
	}

	@Override
	public int modify(QnaReplyVo vo) {
		return rm.update(vo);
	}

	@Override
	public int remove(Long rno) {
		return rm.delete(rno);
	}

	@Override
	public QnaPageDTO getListPage(Criteria cri, Long bno) {
		return new QnaPageDTO(
							rm.getCountByBno(bno),
							rm.getList(cri, bno));
	}

}
