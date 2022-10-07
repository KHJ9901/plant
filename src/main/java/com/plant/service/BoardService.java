package com.plant.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.plant.dto.Board;
import com.plant.dto.QnaPageDTO;
import com.plant.dto.Criteria;

public interface BoardService {
	
	public List<Board> list(Criteria cri);

	public int getTotalRec(Criteria cri);

	public Object searchBoard(String seqno);

	public Object insert(Board board);

	public Object update(Board board);

	public void boarddel(String seqno);


	
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
