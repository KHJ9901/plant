package com.plant.dto;

import java.util.List;

public class Qna {
	private String no;
	private String seqno;
	private String content;
	private String count;
	private String wdate;
	private String id;
	private String reply;
	private String like;
	private List<QnaReply> askreply;
	private List<Qna_Img> qna_img;
	
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getLike() {
		return like;
	}
	public void setLike(String like) {
		this.like = like;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	
	public String getSeqno() {
		return seqno;
	}
	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}
	public List<QnaReply> getAskreply() {
		return askreply;
	}
	public void setAskreply(List<QnaReply> askreply) {
		this.askreply = askreply;
	}
	public List<Qna_Img> getQna_img() {
		return qna_img;
	}
	public void setQna_img(List<Qna_Img> qna_img) {
		this.qna_img = qna_img;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
