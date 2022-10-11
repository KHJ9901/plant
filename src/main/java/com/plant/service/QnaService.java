package com.plant.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.plant.dto.Criteria;
import com.plant.dto.Qna;
import com.plant.dto.Qna_Img;

public interface QnaService {

	List<Qna> list(Criteria cri);
	
	public Qna searchAsk(String seqno);
	
//	public String Qnanew(HttpServletRequest req, HttpServletResponse resp);

	public String update(Qna qna, MultipartFile filename);
	
	public void qnadel(String seqno);
	
	public int getTotalRec(Criteria cri);
	
	public void asklike(HttpServletRequest req);
	
	public String insert(Qna qna, MultipartFile qnaimg);
}
