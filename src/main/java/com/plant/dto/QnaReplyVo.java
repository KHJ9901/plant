package com.plant.dto;

public class QnaReplyVo {
	private int rn;
	private Long qr_seqno;
	private String content;
	private String id;
	private Long seqno;
	private String wdate;
	
	
	public int getRn() {
		return rn;
	}
	public void setRn(int rn) {
		this.rn = rn;
	}
	public Long getQr_seqno() {
		return qr_seqno;
	}
	public void setQr_seqno(Long qr_seqno) {
		this.qr_seqno = qr_seqno;
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
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	
}
