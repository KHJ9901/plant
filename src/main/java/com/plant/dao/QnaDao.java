package com.plant.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.plant.dto.Criteria;
import com.plant.dto.Qna;
import com.plant.dto.Qna_Img;

public interface QnaDao {

	public List<Qna> askList(Criteria cri);
	
	public void asklike(HttpServletRequest req);
	
	public Qna askDetail(String seqno);
	
	public String insert(Qna qna, Qna_Img qnaimg);
	
	public void reply(HttpServletRequest req);
	
	public Map<String, String> qnadel(String seqno);
	
	void insertQna_Thumbnail(String qnaimg_no, Qna_Img qnaimg);
	
	String insertQna_img(String seqno, Qna_Img qnaimg);
	
	public void update(Qna qna, Qna_Img qnaimg);
	
	public int getTotalRec(Criteria cri);
	
}
