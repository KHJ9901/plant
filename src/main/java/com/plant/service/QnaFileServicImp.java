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

import org.apache.commons.fileupload.FileItem;
import org.springframework.web.multipart.MultipartFile;

import com.plant.dao.QnaFileDao;
import com.plant.dto.Qna_Img;
import com.plant.dto.Qna_Thumbnail;

import net.coobird.thumbnailator.Thumbnails;


public class QnaFileServicImp implements QnaFileService{
	
	QnaFileDao fileDao = new QnaFileDao();
	Qna_Img qna_img = null;
	
	@Override
	public Qna_Img fileUpload(FileItem item) {
		Qna_Img qna_img = null;
		
		long fileSize= item.getSize();
		System.out.println("업로드 파일사이즈 : " + fileSize);
		if(fileSize >0) {
			String fileUploadPath = "D:/plant/upload/";
			String fileName = item.getName();
			System.out.println("업로드 파일이름 : " + fileName);
			
			int idx = fileName.lastIndexOf(".");
			
			String split_fileName = fileName.substring(0,idx);
			String split_extension = fileName.substring(idx+1);
			
			UUID uid = UUID.randomUUID();
			String saveFileName = split_fileName + "_" + uid + "." + split_extension;
			System.out.println("저장할 파일이름 :" + saveFileName);
			
			//업로드 파일 저장
			File file = new File(fileUploadPath + saveFileName);
			try {
				item.write(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			qna_img = new Qna_Img();
			qna_img.setUploadfile(fileUploadPath);
			qna_img.setSavefile(saveFileName);
			qna_img.setLocation(fileUploadPath);
			qna_img.setFilesize(String.valueOf(fileSize));
			qna_img.setType(item.getContentType());
			
			//이미지파일타입
			String fileType = item.getContentType();
			String type = fileType.substring(0, fileType.indexOf("/"));
			System.out.println("업로드 파일 타입 :" + type );
			
			if(type.equals("image")) {
				qna_img.setThumbnail(setThumbnail(file, saveFileName));
			}		
		}
		
		return qna_img;	
	}
	
	//썸네일 파일 저장
	@Override
	public Qna_Thumbnail setThumbnail(File file, String saveFileName) {
		String thumFileName = "thumb_200x200_" + saveFileName;
		String thumFilePath = "D:\\plant\\upload\\tmp\\";
		File thumFile = new File(thumFilePath + thumFileName);
		try {
			Thumbnails.of(file).size(200, 200).toFile(thumFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Qna_Thumbnail thumNail = new Qna_Thumbnail();
		thumNail.setFilename(thumFileName);
		thumNail.setFilepath(thumFilePath);

		//파일사이즈구하기
		thumNail.setFilesize(String.valueOf(thumFile.length()));
	
		return thumNail;
	}
	
	public void fileDown(HttpServletRequest request, HttpServletResponse response) {
//		response.setContentType("text/html; charset=utf8");
//		try {
//			request.setCharacterEncoding("utf-8");
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
		
		String filename = request.getParameter("filename");
		String saveFileName = request.getParameter("savefilename");
		String filepath = request.getParameter("filepath");
		
		File file = new File(filepath + saveFileName);
		
		try {
			InputStream in = new FileInputStream(file);
			OutputStream os = response.getOutputStream();
			
			response.reset(); //이미 열려있는 출력스트림을 비움
			response.setHeader("Cache-Control", "no-cache");
			response.addHeader("Content-disposition", "attachment; fileName=" + URLEncoder.encode(filename, "UTF-8"));
			byte[] fileByte = new byte[(int)file.length()];
			
			int readByte = 0;
			while((readByte = in.read(fileByte)) > 0) {
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
	public int delete(String no, String savefile, String location, String thumbnail) {
		//attachfile 레코드 삭제
		int rs = 0;
		rs = fileDao.deleteByNO(no);
		
		//파일삭제
		File file = new File(location+savefile);
		if(file.exists()) {
			file.delete();	
			rs=1;
		}
		
		//썸네일삭제
		if(thumbnail != null) {
			File t_file = new File(location +"thumbnail/" + thumbnail);
			if(file.exists()) { //파일이 존재하면 삭제해라
				t_file.delete();
			}
		}
		
		return rs;
	}



	@Override
	public Qna_Img fileUpload(MultipartFile item) {
	Qna_Img qna_img = null;
		long filesize= item.getSize();
		System.out.println("업로드 파일사이즈 : " + filesize);
		if(filesize >0) {
			String fileUploadPath = "D:/plant/upload/";
			String fileName = item.getOriginalFilename();
			System.out.println("업로드 파일이름 : " + fileName);
			
			int idx = fileName.lastIndexOf(".");
			
			String split_fileName = fileName.substring(0,idx);
			String split_extension = fileName.substring(idx+1);
			
			UUID uid = UUID.randomUUID();
			String saveFileName = split_fileName + "_" + uid + "." + split_extension;
			System.out.println("저장할 파일이름 :" + saveFileName);
			
			//업로드 파일 저장
			File file = new File(fileUploadPath + saveFileName);
			try {
				item.transferTo(file);;
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			qna_img = new Qna_Img();
			qna_img.setUploadfile(fileUploadPath);
			qna_img.setSavefile(saveFileName);
			qna_img.setLocation(fileUploadPath);
			qna_img.setFilesize(String.valueOf(filesize));
			qna_img.setType(item.getContentType());
			
			//이미지파일타입
			String fileType = item.getContentType();
			String type = fileType.substring(0, fileType.indexOf("/"));
			System.out.println("업로드 파일 타입 :" + type );
			
			if(type.equals("image")) {
				qna_img.setThumbnail(setThumbnail(file, saveFileName));
			}		
		}
		
		return qna_img;	
	}
}
