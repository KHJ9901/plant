package com.plant.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.plant.dto.Board;
import com.plant.dto.Criteria;

public interface BoardDao {

	public List<Board> boardList(Criteria cri);
	
	public Board boardDetail(String seqno);
	
	public String boardnew(Board board);
	
	public void reply(HttpServletRequest req);
	
	public Map<String, String> boarddel(String seqno);
	
	public void update(Board board);
}
