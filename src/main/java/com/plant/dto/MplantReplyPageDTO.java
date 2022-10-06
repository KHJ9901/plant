package com.plant.dto;

import java.util.List;

public class MplantReplyPageDTO {

	private int replyCnt;
	private List<MplantReply> list;
	
	public MplantReplyPageDTO(int replyCnt, List<MplantReply> list) {
		this.replyCnt = replyCnt;
		this.list = list;
	}

	public int getReplyCnt() {
		return replyCnt;
	}

	public void setReplyCnt(int replyCnt) {
		this.replyCnt = replyCnt;
	}

	public List<MplantReply> getList() {
		return list;
	}

	public void setList(List<MplantReply> list) {
		this.list = list;
	}
	
	
	
}
