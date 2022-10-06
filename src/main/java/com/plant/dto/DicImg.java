package com.plant.dto;

public class DicImg {
	private String no;
	private String fileName;
	private String saveFileName;
	private String filePath;
	private String fileSize;
	private String fileType;
	private DicThumb dicthumb;
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getSaveFileName() {
		return saveFileName;
	}
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public DicThumb getDicthumb() {
		return dicthumb;
	}
	public void setDicthumb(DicThumb dicthumb) {
		this.dicthumb = dicthumb;
	}

	
}
