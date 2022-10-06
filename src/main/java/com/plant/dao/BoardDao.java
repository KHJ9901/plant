package com.plant.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.plant.dto.Board;

public interface BoardDao {

	public List<Board> boardList();
	
	public Board boardDetail(String seqno);
	
	public String boardnew(Board board);
	
	public void reply(HttpServletRequest req);
	
	public void boarddel(String seqno);
	
	public void update(Board board);
}
