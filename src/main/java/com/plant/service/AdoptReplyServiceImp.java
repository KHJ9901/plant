package com.plant.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plant.dto.AdoptReply;
import com.plant.dto.AdoptReplyPageDTO;
import com.plant.dto.AdoptReplyVO;
import com.plant.dto.Criteria;
import com.plant.mapper.AdoptReplyMapper;

@Service
public class AdoptReplyServiceImp implements AdoptReplyService {

	private static final Logger log = LoggerFactory.getLogger(AdoptReplyServiceImp.class);
	
	@Autowired 
	private AdoptReplyMapper mapper;
	
	@Override
	public int register(AdoptReply adoptReply) {
		
		log.info("reply register service called.." + adoptReply);
		return mapper.insert(adoptReply);
	}

	@Override
	public List<AdoptReplyVO> getList(Criteria cri, Long bno) {
		return mapper.getList(cri, bno);
	}

	@Override
	public AdoptReplyVO get(Long rno) {
		return mapper.read(rno);
	}

	@Override
	public int modify(AdoptReplyVO vo) {
		return mapper.update(vo);
	}

	@Override
	public int remove(Long rno) {
		return mapper.delete(rno);
	}

	@Override
	public AdoptReplyPageDTO getListPage(Criteria cri, Long bno) {
		return new AdoptReplyPageDTO(
									  mapper.getCoungByBno(bno),
									  mapper.getList(cri, bno)
									);
	}

}
