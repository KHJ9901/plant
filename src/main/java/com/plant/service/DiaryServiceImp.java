package com.plant.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plant.common.LoginImpl;
import com.plant.dao.DiaryDaoImp;
import com.plant.dto.Criteria;
import com.plant.dto.Diary;

@Service
public class DiaryServiceImp implements DiaryService {
	
	@Autowired
	DiaryDaoImp diaryDao;
	
	private static final String CHARSET = "utf-8";
		
	@Override
	public List<Diary> list(Criteria cri) {
		return diaryDao.diaryList(cri);
	}
	
	@Override
	public Diary searchDiary(String diary_seqno) {
		return diaryDao.diaryDetail(diary_seqno);
	}
	
	@Override
	public String insertDiary(HttpServletRequest req, HttpServletResponse resp) {
		
		Diary diary = new Diary();
		
		diary.setId(req.getParameter("id"));
		diary.setContent(req.getParameter("content"));
		diary.setName(req.getParameter("name"));
		
		return diaryDao.insert(diary);
	}

	public String update(HttpServletRequest req, HttpServletResponse resp) {
		
		Diary diary = new Diary();
		
		System.out.println("diaryservice diary_seqno: "+req.getParameter("diary_seqno"));
		
		diary.setId(req.getParameter("id"));
		diary.setContent(req.getParameter("content"));
		diary.setName(req.getParameter("name"));
		diary.setDiary_seqno(req.getParameter("diary_seqno"));
		
		LoginImpl login = (LoginImpl)req.getSession().getAttribute("loginUser");
		diary.setId(login.getId());

		diaryDao.update(diary);
		return diary.getDiary_seqno();
	}
	
	Diary getFormParameter(FileItem item, Diary diary) {
		System.out.printf("필드이름: %s, 필드값:%s\n", item.getFieldName(), item.getString());
		if(item.getFieldName().equals("id")) {
			diary.setId(item.getString());
		}
		if(item.getFieldName().equals("name")) {
			diary.setName(item.getString());
		}
		if(item.getFieldName().equals("content")) {
			diary.setContent(item.getString());
		}
		return diary;
	}

	public int getTotalRec(Criteria criteria) {
		return diaryDao.getTotalRec(criteria);
	}
	
	public void diarydel(String seqno) {
		diaryDao.diarydel(seqno);		
	}

	@Override
	public String insertDiary(Diary diary) {
		return diaryDao.insert(diary);
	}

	@Override
	public String update(Diary diary) {
		diaryDao.update(diary);
		return diary.getDiary_seqno();
	}

}
