package com.plant.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.plant.dao.AdoptFileDao;
import com.plant.dto.AdoptFile;
import com.plant.dto.AdoptThumb;

import net.coobird.thumbnailator.Thumbnails;

public class AdoptFileServiceImp implements AdoptFileService {

	AdoptFileDao adoptfileDao = new AdoptFileDao();
	AdoptFile adoptfile = null;
	
	//스프링
	@Override
	public AdoptFile fileUpload(MultipartFile item) {

		AdoptFile adoptfile = null;
		long filesize = item.getSize();
		
		if(filesize>0) {
			String fileuploadpath = "d:/plant/upload/";
			String filename = item.getOriginalFilename();
			System.out.println("업로드 파일이름:"+ filename);
			
			//방법1
			int idx = filename.lastIndexOf(".");				
			String split_filename = filename.substring(0,idx);
			String split_extension = filename.substring(idx+1);
			
			//방법2
			//split_filename = FilenameUtils.getBaseName(filename);
			//split_extension = FilenameUtils.getExtension(filename);
			
			//중복된 파일을 업로드 하지 않기 위해 UID값 생성
			UUID uid = UUID.randomUUID();
			String savefilename = split_filename + "_" + uid + "." + split_extension;
			System.out.println("저장할 파일 이름:"+savefilename);
			
			//업로드 파일 저장
			File file = new File(fileuploadpath + savefilename);
			
			try {
				item.transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			adoptfile = new AdoptFile();
			adoptfile.setFilename(filename);
			adoptfile.setSavefilename(savefilename);
			adoptfile.setFilepath(fileuploadpath);
			adoptfile.setFilesize(String.valueOf(filesize));
			adoptfile.setFiletype(item.getContentType());
			
			//이미지 파일타입 확인
			String filetype = item.getContentType();
			String type = filetype.substring(0, filetype.indexOf("/"));
			System.out.println("업로드 파일 타입:"+type);
			
			if(type.equals("image")) {
				adoptfile.setThumbnail((setAdoptThumb(file, savefilename)));
			}
		}
		
		return adoptfile;

	}
	
	//썸네일 파일 저장
	@Override
	public AdoptThumb setAdoptThumb(File file, String savefilename) {
		String thumbFileName = "thumb_200x200_" + savefilename;
		String thumbFilePath = "d:/plant/upload/thumbnail/";
		File thumbFile = new File(thumbFilePath + thumbFileName);
		try {
			Thumbnails.of(file).size(200, 200).toFile(thumbFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		AdoptThumb adoptthumb = new AdoptThumb();
		adoptthumb.setFileName(thumbFileName);
		adoptthumb.setFilePath(thumbFilePath);
		//썸네일 파일사이즈 구하기
		adoptthumb.setFileSize(String.valueOf(thumbFile.length()));
		
		return adoptthumb;
	}
	
	
	//썸네일 저장
	@Override
	public void fileDown(HttpServletRequest request, HttpServletResponse response) {

		String filename = request.getParameter("filename");
		String savefilename = request.getParameter("savefilename");
		String filepath = request.getParameter("filepath");
		
		File file = new File(filepath + savefilename);
		
		try {
			InputStream in= new FileInputStream(file);
			OutputStream os = response.getOutputStream();
			
			response.reset(); //이미 열려있는 출력스트림을 비움
			response.setHeader("Cache-Control", "no-cache");
			response.addHeader("Content-disposition", "attachment; fileName="+ URLEncoder.encode(filename,"UTF-8"));
			byte[] fileByte = new byte[(int)file.length()];
		
			int readByte = 0;
			while( (readByte = in.read(fileByte)) > 0 ) {
				os.write(fileByte, 0, readByte);
			}
			
			in.close();
			os.flush();
			os.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Override
	public int delete(String no, String savefilename, String filepath, String thumb_filename) {
		
		int rs = 0;
		rs = adoptfileDao.deleteByNo(no);
		
		//첨부파일삭제
		File file = new File(filepath+savefilename);
		if(file.exists()) {
			file.delete();
			rs = 1;
		}
		
		//썸네일삭제
		if(thumb_filename != null && rs == 1) {
			File thumb_file = new File(filepath + "thumbnail/" + thumb_filename);
			if(file.exists()) {
				thumb_file.delete();
			}
		}
		
		return rs;
	}
	
	
	
	
	/*
	@Override
	public AdoptFile fileUpload(FileItem item) {
		
		AdoptFile adoptfile = null;
		long filesize = item.getSize();
		System.out.println("업로드 파일사이즈 : " + filesize);
		
		if(filesize>0) {
			String fileuploadpath = "d:/plant/upload/";
			String filename = item.getName();
			System.out.println("업로드 파일이름:"+ filename);
			//방법1
			int idx = filename.lastIndexOf(".");
			String split_filename = filename.substring(0,idx);
			String split_extension = filename.substring(idx+1);
			
			//방법2
			//split_filename = FilenameUtils.getBaseName(filename);
			//split_extension = FilenameUtils.getExtension(filename);
			
			UUID uid = UUID.randomUUID();
			String savefilename = split_filename + "_" + uid + "." + split_extension;
			
			//업로드 파일 저장
			File file = new File(fileuploadpath + savefilename);
		
			try {
				item.write(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			adoptfile = new AdoptFile();
			adoptfile.setFilename(filename);
			adoptfile.setSavefilename(savefilename);
			adoptfile.setFilepath(fileuploadpath);
			adoptfile.setFilesize(String.valueOf(filesize));
			adoptfile.setFiletype(item.getContentType());
			
			String filetype = item.getContentType();
			String type = filetype.substring(0, filetype.indexOf("/"));
			
			if(type.equals("image")) {
				adoptfile.setAdoptthumb(setAdoptThumb(file, savefilename));
			}
		
		} return adoptfile;
		
	}
	*/
	
	
	
	
	
}