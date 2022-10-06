package com.plant.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plant.dto.Board;
import com.plant.dto.QnaPageDTO;
import com.plant.dto.Criteria;

public interface BoardService {
	
	public List<Board> getboard(Criteria cri, Long bno);
	
	public QnaPageDTO getboardPage(Criteria cri, Long bno);
	
//	public Board searchBoard(String seqno);
//	
//	public String Boardnew(HttpServletRequest req);
//	
//	public void reply(HttpServletRequest req);
//	
//	public String update(HttpServletRequest req, HttpServletResponse resp);
//
//	public void boarddel(String seqno);
}
