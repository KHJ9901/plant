package com.plant.service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.springframework.web.multipart.MultipartFile;

import com.plant.dto.AdoptFile;
import com.plant.dto.AdoptThumb;

public interface AdoptFileService {

	//서블릿
	public AdoptFile fileUpload(FileItem item);
	
	//스프링
	public AdoptFile fileUpload(MultipartFile item);
	
	public AdoptThumb setAdoptThumb(File file, String savefilename);
	
	public void fileDown(HttpServletRequest request, HttpServletResponse response);

	public int delete(String no, String savefilename, String filepath, String thumb_file);
	
}
