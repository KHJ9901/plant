package com.plant.service;

import java.io.File;

import org.apache.commons.fileupload.FileItem;
import org.springframework.web.multipart.MultipartFile;

import com.plant.dto.*;

public interface DicImgFileService {
//-------------------------사전-------------------------
	//서블릿
	//public DicImg dicImgUpload(FileItem item);
	
	//스프링 기반
	public DicImg dicImgUpload(MultipartFile item);
	
	public int dicImgDelete(String no, String savefilename, String filepath, String thumb_filename);
	
	DicThumb setDicThumb(File file, String saveFileName);
	
	
	
	
	//-------------------------회원식물-------------------------
	//서블릿
	//public MplantImg mpImgUpload(FileItem item);
	
	//스프링 기반
	public MplantImg mpImgUpload(MultipartFile item);
	
	public int mpImgDelete(String no, String savefilename, String filepath, String thumb_filename);
	
	MplantThumb setMpThumb(File file, String saveFileName);
}
