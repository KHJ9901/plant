//package com.plant.service;
//
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.plant.dao.BoardDaoImp;
//import com.plant.dto.Board;
//import com.plant.dto.Criteria;
//import com.plant.mapper.BoardMapper;
//
//@Service
//public class BoardServicelmp implements BoardService{
//	
//	
//	@Autowired
//	BoardDaoImp boardDao;
//	
//	
//
//	@Override
//	public Board searchBoard(String seqno) {
//		return boardDao.boardDetail(seqno);
//	}
//
//	@Override
//	public String Boardnew(HttpServletRequest req) {
//		
//		Board board = new Board();
//		board.setTitle(req.getParameter("title"));
//		board.setContent(req.getParameter("content"));
//		board.setId(req.getParameter("id"));
//		
//		return boardDao.boardnew(board);	
//	}
//
//	@Override
//	public void reply(HttpServletRequest req) {		  
//		boardDao.reply(req);
//		
//	}
//
//	@Override
//	public String update(HttpServletRequest req, HttpServletResponse resp) {
//		Board board = new Board();
//		board.setTitle(req.getParameter("title"));
//		board.setContent(req.getParameter("content"));
//		board.setId(req.getParameter("id"));
//		
//		return board.getSeqno();
//	}
//	
//	public void boarddel(String seqno) {
//		boardDao.boarddel(seqno);
//	}
//	
//
//}
