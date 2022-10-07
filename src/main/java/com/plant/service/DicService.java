package com.plant.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.plant.dto.*;

public interface DicService {
	//---------------리스트 조회--------------------//
	public List<Dictionary> list(Criteria cri);
	
	public List<Dictionary> inList(Criteria cri);

	public List<Dictionary> outList(Criteria cri);

	public List<Mplant> mpList(Criteria cri);
	
	
	//---------------상세페이지--------------------//
	public Dictionary DicDetail(String seqno);

	public Mplant MpDetail(String seqno);
	
	

	//---------------삽입--------------------//	
	public int insertDic(Dictionary diction, MultipartFile files);
	
	public int insertMp(Mplant mplant, MultipartFile files);
	
	
	
	//---------------수정--------------------//
	public int updateDic(Dictionary diction, MultipartFile files);
	
	public int updateMp(Mplant mplant, MultipartFile files);
	
	
	
	//---------------삭제--------------------//
	public int deleteDic(String seqno);
	
	public int deleteMp(String seqno);
	
	
	
	int getDicRec(Criteria cri);

	int getDicInRec(Criteria cri);

	int getDicOutRec(Criteria cri);

	int getMplantRec(Criteria cri);





}
