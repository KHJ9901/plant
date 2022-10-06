package com.plant.dto;

import java.util.List;

public class QnaPageDTO {
	private int replyCnt;
	private List<QnaReplyVo> list;
	
	
	public QnaPageDTO(int replyCnt, List<QnaReplyVo> list) {
		super();
		this.replyCnt = replyCnt;
		this.list = list;
	}

	public int getReplyCnt() {
		return replyCnt;
	}

	public void setReplyCnt(int replyCnt) {
		this.replyCnt = replyCnt;
	}

	public List<QnaReplyVo> getList() {
		return list;
	}

	public void setList(List<QnaReplyVo> list) {
		this.list = list;
	}
	
	
	
}
