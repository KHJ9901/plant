package com.plant.dto;

public class AdoptFile {

	private String no;
	private String adopt_seqno;
	private String filename;
	private String savefilename;
	private String filepath;
	private String filesize;
	private String filetype;
	private AdoptThumb thumbnail;
	
	public AdoptFile() {
		super();
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getAdopt_seqno() {
		return adopt_seqno;
	}
	public void setAdopt_seqno(String adopt_seqno) {
		this.adopt_seqno = adopt_seqno;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getSavefilename() {
		return savefilename;
	}
	public void setSavefilename(String savefilename) {
		this.savefilename = savefilename;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getFilesize() {
		return filesize;
	}
	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	public AdoptThumb getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(AdoptThumb thumbnail) {
		this.thumbnail = thumbnail;
	}


	
}
