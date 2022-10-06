package com.plant.dto;

public class Qna_Img {
	private String qiseqno;
	private String uploadfile;
	private String savefile;
	private String filesize;
	private String type;
	private String location;
	private Qna_Thumbnail thumbnail;
	
	
	public Qna_Img() {
		super();
	}
	public String getFilesize() {
		return filesize;
	}
	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}
	public String getQiseqno() {
		return qiseqno;
	}
	public void setQiseqno(String qiseqno) {
		this.qiseqno = qiseqno;
	}
	public String getUploadfile() {
		return uploadfile;
	}
	public void setUploadfile(String uploadfile) {
		this.uploadfile = uploadfile;
	}
	public String getSavefile() {
		return savefile;
	}
	public void setSavefile(String savefile) {
		this.savefile = savefile;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Qna_Thumbnail getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(Qna_Thumbnail thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	
	
}
