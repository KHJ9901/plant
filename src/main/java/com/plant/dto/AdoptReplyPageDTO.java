package com.plant.dto;

import java.util.List;

public class AdoptReplyPageDTO {
	
	private int replyCnt;
	private List<AdoptReplyVO> list;
	
	public AdoptReplyPageDTO(int replyCnt, List<AdoptReplyVO> list) {
		this.replyCnt = replyCnt;
		this.list = list;
	}

	public int getReplyCnt() {
		return replyCnt;
	}

	public void setReplyCnt(int ReplyCnt) {
		this.replyCnt = ReplyCnt;
	}

	public List<AdoptReplyVO> getList() {
		return list;
	}

	public void setList(List<AdoptReplyVO> list) {
		this.list = list;
	}
	

}
