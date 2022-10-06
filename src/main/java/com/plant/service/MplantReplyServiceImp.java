package com.plant.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plant.dto.*;
import com.plant.mapper.*;

@Service
public class MplantReplyServiceImp implements MplantReplyService {

	private static final Logger log = LoggerFactory.getLogger(MplantReplyServiceImp.class);
	
	@Autowired
	private MplantReplyMapper mapper;
	
	@Override
	public List<MplantReply> getList(Criteria cri, Long bno) {
		return mapper.getList(cri, bno);
	}

	@Override
	public MplantReplyPageDTO getListPage(Criteria cri, Long bno) {
		return new MplantReplyPageDTO(mapper.getCountByBno(bno), 
								   	  mapper.getList(cri, bno));
	}

	@Override
	public MplantReply getOneReply(Long rno) {
		return mapper.getOne(rno);
	}

	@Override
	public int register(MplantReply reply) {
		log.info("reply register service called.." + reply);
		return mapper.insert(reply);
	}

	@Override
	public int modify(MplantReply vo) {
		return mapper.update(vo);
	}

	@Override
	public int delete(Long rno) {
		return mapper.delete(rno);
	}

}
