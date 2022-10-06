package com.plant.service;

import java.util.List;

import com.plant.dto.*;

public interface MplantReplyService {
	
	public List<MplantReply> getList(Criteria cri, Long bno);
	
	public MplantReplyPageDTO getListPage(Criteria cri, Long bno);
	
	public MplantReply getOneReply(Long rno);
	
	public int register(MplantReply reply);

	public int modify(MplantReply vo);

	public int delete(Long rno);
	
}
