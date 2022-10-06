package com.plant.service;

import java.util.List;

import com.plant.dto.AdoptReply;
import com.plant.dto.AdoptReplyPageDTO;
import com.plant.dto.AdoptReplyVO;
import com.plant.dto.Criteria;

public interface AdoptReplyService {

	public int register(AdoptReply adoptreply);

	public List<AdoptReplyVO> getList(Criteria cri, Long bno);

	public AdoptReplyPageDTO getListPage(Criteria cri, Long bno);
	
	public AdoptReplyVO get(Long rno);

	public int modify(AdoptReplyVO vo);

	public int remove(Long rno);

	
}
