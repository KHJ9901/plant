package com.plant.dao;

import java.util.List;
import java.util.Map;

import com.plant.dto.Criteria;
import com.plant.dto.Diary;

public interface DiaryDao {
	
	public List<Diary> diaryList(Criteria cri);
	
	public Diary diaryDetail(String diary_seqno);
		
	public String insert(Diary diary);
	
	public String insertDiary(Diary diary);
	
	//void insertAdoptThumb (String attach_no, AdoptFile adoptfile);
	
	//String insertAdoptFile(String seqno, AdoptFile adoptfile);
	
	public void update(Diary diary);
			
	public int getTotalRec(Criteria cri);
	
	public Map<String, String> diarydel(String diary_seqno);
	
}
