package com.plant.controller.user;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.plant.common.LoginImpl;
import com.plant.dto.Board;
import com.plant.dto.Criteria;
import com.plant.dto.Page;
import com.plant.service.BoardService;

@Controller
@RequestMapping(value="/board/")
public class BoardController {
	
	@Autowired
	BoardService service;
	
	@RequestMapping(value="boardlist", method= {RequestMethod.GET, RequestMethod.POST})
	public String qnalist(Criteria cri, Model model) {
		
		if(cri.getCurrentPage() == 0) cri.setCurrentPage(1);
		if(cri.getRowPerpage() == 0) cri.setRowPerpage(5);
		
		List<Board> board = service.list(cri);
		
		model.addAttribute("pageMaker", new Page(service.getTotalRec(cri), cri));
		model.addAttribute("board", board);
		return "/board/board";
	}
	
	@RequestMapping(value="detail", method= RequestMethod.GET)
	public String detail(@ModelAttribute("seqno") String seqno, @ModelAttribute("page") String page, Model model) {
		model.addAttribute("board", service.searchBoard(seqno));
		if(page.equals("boardmodify")) {return "/board/boardmodify";}
		else {return "/board/boardDetail";}
	}
	
	@RequestMapping(value="boardnew", method= RequestMethod.GET)
	public String qnanew() {
		
		return "/board/boardReg";
	}
	@PostMapping("register")
	public String register(Board board,
						   HttpSession sess, 
						   RedirectAttributes rttr) {
		board.setId(((LoginImpl)sess.getAttribute("loginUser")).getId());
		
		rttr.addFlashAttribute("seqno", service.insert(board));
		return "redirect:/board/detail";
	}
	
	@RequestMapping(value="boardUpdate", method= RequestMethod.POST)
	public String update(HttpSession sess, Model model, Board board) 
						throws Exception {
		
		LoginImpl login = (LoginImpl)sess.getAttribute("loginUser");	
		board.setId(login.getId());
		model.addAttribute("seqno", service.update(board));
		return "redirect:detail";
	}
	
	@RequestMapping(value="boardDelete", method= RequestMethod.GET)
	public String Delete(@ModelAttribute("seqno") String seqno) {
		service.boarddel(seqno);
		return "redirect:boardlist";
	}
		
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
}