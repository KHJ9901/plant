package com.plant.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plant.dto.Adopt;
import com.plant.dto.Criteria;
import com.plant.dto.Diary;

public interface DiaryService {

	public List<Diary> list(Criteria cri);
	
	public int getTotalRec(Criteria criteria);
	
	public Diary searchDiary(String seqno);
	
	public String insertDiary(HttpServletRequest req, HttpServletResponse resp);
	
	public String insertDiary(Diary diary);
	
	public String update(HttpServletRequest req, HttpServletResponse resp);

	public String update(Diary diary);
	
	public void diarydel(String seqno);


	
}
