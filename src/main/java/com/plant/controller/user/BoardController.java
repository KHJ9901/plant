//package com.plant.controller.user;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import com.cosmos.controller.user.ReplyController;
//import com.cosmos.dto.Criteria;
//import com.cosmos.dto.ReplyVo;
//import com.cosmos.service.ReplyService;
//import com.plant.dto.Board;
//import com.plant.service.BoardService;
//import com.plant.service.BoardServicelmp;
//
//@WebServlet(urlPatterns = {"/board/"})
//public class BoardController extends HttpServlet {
//	
//	private static final Logger log = LoggerFactory.getLogger(QnaReplyController.class);
//
//	@Autowired
//	BoardService service;
//	
//	@PostMapping(value="add",
//				 consumes="application/json",
//				 produces= "text/plain; charset=utf-8")
//	
//	
//	private void doAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		resp.setContentType("text/html; charset=utf8");
//		req.setCharacterEncoding("utf8");
//		
//		String uri = req.getRequestURI();	
//		String cmd = uri.substring(uri.lastIndexOf("/")+1);
//		
//		System.out.println("cmd=" + cmd);
//		BoardServicelmp boardService = new BoardServicelmp();
//		
//		if(cmd.equals("board")) {
//			//게시판 리스트
//			List<Board> board = boardService.list();
//			req.setAttribute("board", board);
//			goView(req, resp, "/board/board.jsp");
//
//			
//		} else if(cmd.equals("boardDetail")) {
//			//게시판 세부내용
//			String seqno = req.getParameter("seqno");
//			if(seqno == null) {
//				seqno = (String)req.getAttribute("seqno");
//			}
//			Board modi= boardService.searchBoard(seqno);
//			req.setAttribute("board", modi);
//			String page = req.getParameter("page");
//			
//			if(page != null) {			
//				goView(req, resp, "/board/boardmodify.jsp");
//			} else {
//				goView(req, resp, "/board/boardDetail.jsp");
//			}
//		
//			//게시글 등록
//		} else if (cmd.equals("write")) {
//			req.setAttribute("seqno", boardService.Boardnew(req));
//			goView(req, resp, "/bo/boardDetail");
//			
//		} else if (cmd.equals("boardwrite")) {
//			List<Board> board = boardService.list();
//			req.setAttribute("board", board);
//			boardService.Boardnew(req);
//			goView(req, resp, "/board/boardReg.jsp");
//		
//			//댓글 등록
//		} else if (cmd.equals("reply")) {
//			boardService.reply(req);
//			String seqno = req.getParameter("asd_board_seqno");
//			req.setAttribute("seqno", seqno);
//			goView(req, resp, "/bo/boardDetail");
//			
//			//게시글 수정
//		} else if(cmd.equals("modify")) {
//			req.setAttribute("seqno", boardService.update(req, resp));
//			goView(req, resp, "/bo/boardDetail");
//		
//			//게시글 삭제
//		} else if(cmd.equals("boardDelete")) {
//			boardService.boarddel(req.getParameter("seqno"));
//			
//			resp.setContentType("text/html; charset=UTF-8");
//	        PrintWriter writer = resp.getWriter();
//	        writer.println("<script>alert('정말삭제하시겠습니까'); location.href='/bo/board';</script>"); 
//	        writer.close();
//		}
//		
//			
//	}
//	
//	void goView(HttpServletRequest req, HttpServletResponse resp, String viewPage) throws ServletException, IOException {
//		RequestDispatcher rd = req.getRequestDispatcher(viewPage);
//		rd.forward(req, resp);
//
//	}
//}