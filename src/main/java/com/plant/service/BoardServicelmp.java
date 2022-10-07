package com.plant.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plant.dao.BoardDaoImp;
import com.plant.dto.Board;
import com.plant.dto.Criteria;

@Service
public class BoardServicelmp implements BoardService{
	
	
	@Autowired
	BoardDaoImp boardDao;
	
	@Override
	public List<Board> list(Criteria cri) {
		return boardDao.boardList(cri);
	}

	@Override
	public Board searchBoard(String seqno) {
		return boardDao.boardDetail(seqno);
	}

	public void reply(HttpServletRequest req) {		  
		boardDao.reply(req);
	}

	@Override
	public String update(Board board) {
		boardDao.update(board);
		return board.getSeqno();
	}
	
	public void boarddel(String seqno) {
		boardDao.boarddel(seqno);
	}

	@Override
	public int getTotalRec(Criteria cri) {
		return boardDao.getTotalRec(cri);
	}

	@Override
	public Object insert(Board board) {
		return boardDao.boardnew(board);
	}

}
