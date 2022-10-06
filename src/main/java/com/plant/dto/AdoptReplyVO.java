package com.plant.dto;

public class AdoptReplyVO {

	private int rn;
	private Long seqno;
	private String content;
	private String id;
	private Long adopt_seqno;
	private String wdate;
	
	public int getRn() {
		return rn;
	}
	public void setRn(int rn) {
		this.rn = rn;
	}
	public Long getSeqno() {
		return seqno;
	}
	public void setSeqno(Long seqno) {
		this.seqno = seqno;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getAdopt_seqno() {
		return adopt_seqno;
	}
	public void setAoard_seqno(Long aoard_seqno) {
		this.adopt_seqno = aoard_seqno;
	}
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

}
