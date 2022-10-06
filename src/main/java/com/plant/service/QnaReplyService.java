package com.plant.service;

import java.util.List;

import com.plant.dto.Criteria;
import com.plant.dto.QnaPageDTO;
import com.plant.dto.QnaReplyVo;

public interface QnaReplyService {

	public int register(QnaReplyVo qnaReply);

	public List<QnaReplyVo> getList(Criteria cri, Long bno);
	
	public QnaPageDTO getListPage(Criteria cri, Long bno);

	public QnaReplyVo get(Long rno);

	public int modify(QnaReplyVo vo);

	public int remove(Long rno);
}
