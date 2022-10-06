package com.plant.service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.springframework.web.multipart.MultipartFile;

import com.plant.dto.Qna_Img;
import com.plant.dto.Qna_Thumbnail;

public interface QnaFileService {

	public Qna_Img fileUpload(FileItem item);

	//스프링기반
	public Qna_Img fileUpload(MultipartFile item);
	
	public Qna_Thumbnail setThumbnail(File file, String saveFileName);
	
	public void fileDown(HttpServletRequest request, HttpServletResponse response);
	
	public int delete(String no, String savefile, String location, String thumbnail);

	
}
