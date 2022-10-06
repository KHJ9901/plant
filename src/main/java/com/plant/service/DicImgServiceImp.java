package com.plant.service;

import java.io.File;

import java.io.IOException;
import java.util.UUID;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.plant.dao.*;
import com.plant.dto.*;

import net.coobird.thumbnailator.Thumbnails;

@Repository
public class DicImgServiceImp implements DicImgFileService{
	
	@Autowired
	DicImgDao imgDao;

	//-----------------------사전---------------------
	@Override
	public DicImg dicImgUpload(MultipartFile item) {
		DicImg mpi = null;
		
		long fileSize = item.getSize();
		
		if(fileSize > 0) {
			String fileUploadPath = "d:/khj/plant/";
			String fileName = item.getOriginalFilename();
			
			int idx = fileName.lastIndexOf(".");
			String split_fileName = fileName.substring(0, idx);
			String split_extension = fileName.substring(idx+1);
			
			UUID uid = UUID.randomUUID(); //랜덤값 생성
			String saveFileName = split_fileName + "_" + uid + "." + split_extension;
			
			File file = new File(fileUploadPath + saveFileName);
			
			try {
				item.transferTo(file);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			mpi = new DicImg();
			mpi.setFileName(fileName);
			mpi.setSaveFileName(saveFileName);
			mpi.setFilePath(fileUploadPath);
			mpi.setFileSize(String.valueOf(fileSize)); //타입변환
			mpi.setFileType(item.getContentType()); //파일타입 불러옴
			//이미지 파일타입 확인
			String fileType = item.getContentType();
			String type = fileType.substring(0, fileType.indexOf("/"));
			
			if(type.equals("image")) {
				mpi.setDicthumb(setDicThumb(file, saveFileName));
			}
		}
		
		return mpi;
	}


	@Override
	public DicThumb setDicThumb(File file, String saveFileName) {
		//썸네일 파일 저장
		String thumbFileName = "thumb_200x200" + "_" + saveFileName;
		String thumbFilePath = "d:/khj/plant/thumb/";
		File thumbFile = new File(thumbFilePath + thumbFileName);
		
		try {
			Thumbnails.of(file).size(200, 200).toFile(thumbFile);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		DicThumb thumbNail = new DicThumb();
		thumbNail.setFileName(thumbFileName);
		thumbNail.setFilePath(thumbFilePath);
		thumbNail.setFileSize(String.valueOf(thumbFile.length()));
		return thumbNail;
	}

	@Override
	public int dicImgDelete(String no, String savefilename, String filepath, String thumb_filename) {
		//db레코드 삭제
		int rs = 0;
		rs = imgDao.dicImgDel(no);
		
		//파일삭제
		File file = new File(filepath+savefilename);
		if(file.exists()) {
			file.delete();
			rs = 1;
		}
		
		//썸네일 삭제
		if(thumb_filename != null && rs == 1) {
			File thumb_file = new File(filepath + "thumb/" + thumb_filename);
			if(thumb_file.exists()) {
				thumb_file.delete();
			}
		}
		
		return rs;
	}
	
	//---------------------회원 식물--------------------------------------------

	@Override
	public MplantImg mpImgUpload(MultipartFile item) {
		MplantImg mpi = null;
		
		long fileSize = item.getSize();
			System.out.println("회원식물 사진 사이즈 쳐찍어 : " + fileSize);
		
		if(fileSize > 0) {
			String fileUploadPath = "d:/khj/plant/";
			String fileName = item.getOriginalFilename();
				System.out.println("회원식물 사진 파일이름 쳐찍어 : " + fileName);
			int idx = fileName.lastIndexOf(".");
			String split_fileName = fileName.substring(0, idx);
			String split_extension = fileName.substring(idx+1);
				System.out.println(split_fileName + "분리!!" + split_extension);
			UUID uid = UUID.randomUUID(); //랜덤값 생성
			String saveFileName = split_fileName + "_" + uid + "." + split_extension;
				System.out.println("회원식물 사진 저장할 파일이름 쳐찍어 : " + saveFileName);
			File file = new File(fileUploadPath + saveFileName);
			
			try {
				item.transferTo(file);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			mpi = new MplantImg();
			mpi.setFileName(fileName);
			mpi.setSaveFileName(saveFileName);
			mpi.setFilePath(fileUploadPath);
			mpi.setFileSize(String.valueOf(fileSize)); //타입변환
			mpi.setFileType(item.getContentType()); //파일타입 불러옴
			
			//이미지 파일타입 확인
			String fileType = item.getContentType();
			String type = fileType.substring(0, fileType.indexOf("/"));
			if(type.equals("image")) {
				mpi.setMpthumb(setMpThumb(file, saveFileName));
				System.out.println("썸네일 이름 쳐찍어 : " + mpi.getMpthumb().getFileName());
			}
		}
		return mpi;
	}
	
	@Override
	public MplantThumb setMpThumb(File file, String saveFileName) {
		//썸네일 파일 저장
		String thumbFileName = "thumb_200x200" + "_" + saveFileName;
		String thumbFilePath = "d:/khj/plant/thumb/";
		File thumbFile = new File(thumbFilePath + thumbFileName);
		
		try {
			Thumbnails.of(file).size(200, 200).toFile(thumbFile);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		MplantThumb thumbNail = new MplantThumb();
		thumbNail.setFileName(thumbFileName);
		thumbNail.setFilePath(thumbFilePath);
		thumbNail.setFileSize(String.valueOf(thumbFile.length()));	
		return thumbNail;
	}
	
	@Override
	public int mpImgDelete(String no, String savefilename, String filepath, String thumb_filename) {
		//db레코드 삭제
		int rs = 0;
		rs = imgDao.mpImgDel(no);
		
		//파일삭제
		File file = new File(filepath+savefilename);
		if(file.exists()) {
			file.delete();
			rs = 1;
		}
		
		//썸네일 삭제
		if(thumb_filename != null && rs == 1) {
			File thumb_file = new File(filepath + "thumb/" + thumb_filename);
			if(thumb_file.exists()) {
				thumb_file.delete();
			}
		}
		
		return rs;
	}
	
	/*
	 * @Override public DicImg dicImgUpload(FileItem item) { DicImg mpi = null;
	 * 
	 * long fileSize = item.getSize(); System.out.println("회원식물 사진 사이즈 쳐찍어 : " +
	 * fileSize);
	 * 
	 * if(fileSize > 0) { String fileUploadPath = "d:/khj/plant/"; String fileName =
	 * item.getName(); System.out.println("회원식물 사진 파일이름 쳐찍어 : " + fileName);
	 * 
	 * int idx = fileName.lastIndexOf("."); String split_fileName =
	 * fileName.substring(0, idx); String split_extension =
	 * fileName.substring(idx+1); System.out.println(split_fileName + "분리!!" +
	 * split_extension);
	 * 
	 * UUID uid = UUID.randomUUID(); //랜덤값 생성 String saveFileName = split_fileName +
	 * "_" + uid + "." + split_extension;
	 * System.out.println("회원식물 사진 저장할 파일이름 쳐찍어 : " + saveFileName);
	 * 
	 * File file = new File(fileUploadPath + saveFileName);
	 * 
	 * try { item.write(file);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * mpi = new DicImg(); //mpi.setUploadfile(fileName);
	 * mpi.setSaveFileName(saveFileName); //mpi.setLocation(fileUploadPath);
	 * mpi.setFileSize(String.valueOf(fileSize)); //타입변환
	 * mpi.setFileType(item.getContentType()); //파일타입 불러옴
	 * 
	 * //이미지 파일타입 확인 String fileType = item.getContentType(); String type =
	 * fileType.substring(0, fileType.indexOf("/"));
	 * System.out.println("업로드 파일 타입 : " + type);
	 * 
	 * if(type.equals("image")) {
	 * 
	 * mpi.setDicthumb(setDicThumb(file, saveFileName)); } }
	 * 
	 * return mpi; }
	 */


	/*
	 * @Override public MplantImg mpImgUpload(FileItem item) { MplantImg mpi = null;
	 * 
	 * long fileSize = item.getSize();
	 * 
	 * 
	 * if(fileSize > 0) { String fileUploadPath = "d:/khj/plant/"; String fileName =
	 * item.getName();
	 * 
	 * 
	 * int idx = fileName.lastIndexOf("."); String split_fileName =
	 * fileName.substring(0, idx); String split_extension =
	 * fileName.substring(idx+1);
	 * 
	 * 
	 * UUID uid = UUID.randomUUID(); //랜덤값 생성 String saveFileName = split_fileName +
	 * "_" + uid + "." + split_extension;
	 * 
	 * 
	 * File file = new File(fileUploadPath + saveFileName);
	 * 
	 * try { item.write(file);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * mpi = new MplantImg(); //mpi.setUploadfile(fileName);
	 * mpi.setSaveFileName(saveFileName); //mpi.setLocation(fileUploadPath);
	 * mpi.setFileSize(String.valueOf(fileSize)); //타입변환
	 * mpi.setFileType(item.getContentType()); //파일타입 불러옴
	 * 
	 * //이미지 파일타입 확인 String fileType = item.getContentType(); String type =
	 * fileType.substring(0, fileType.indexOf("/"));
	 * System.out.println("업로드 파일 타입 : " + type);
	 * 
	 * if(type.equals("image")) {
	 * 
	 * mpi.setMpthumb(setMpThumb(file, saveFileName)); } }
	 * 
	 * return mpi; }
	 */


	
	

}
