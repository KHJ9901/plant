package com.plant.controller.user;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.reflection.SystemMetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.plant.common.LoginImpl;
import com.plant.dto.Board;
import com.plant.dto.Criteria;
import com.plant.dto.MemInfo;
import com.plant.dto.Page;
import com.plant.dto.Plantmember;
import com.plant.service.*;

@Controller
@RequestMapping("/me/")
public class MemInfoController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemInfoService ms;

	@RequestMapping(value="mypage", method= {RequestMethod.POST, RequestMethod.GET})
	public String mypageview(HttpSession sess, Model model) {
		LoginImpl loginUser = (LoginImpl) sess.getAttribute("loginUser");
		
		String id = loginUser.getId();
		Plantmember board = ms.mypage(id);
		System.out.println("????? : "+ id);
		model.addAttribute("board", board);
		return "/member/mypage";
	}
	
	@RequestMapping(value="myboard", method= {RequestMethod.POST, RequestMethod.GET})
	public String list(Criteria cri, Model model, HttpSession sess) {
		LoginImpl loginUser = (LoginImpl) sess.getAttribute("loginUser");
		String id = loginUser.getId();
		
		if(cri.getCurrentPage() == 0) cri.setCurrentPage(1);
         if(cri.getRowPerpage() == 0) cri.setRowPerpage(10);
         
         List<Board> board = ms.list(cri, id);
         //System.out.println("보드!!!!!!!! : " + board.size());

         model.addAttribute("pageMaker", new Page(ms.getTotalRec(cri), cri));
         model.addAttribute("board", board); //요청
         return"/member/myboard";
      }
	
	@RequestMapping(value="myqnaboard", method= {RequestMethod.POST, RequestMethod.GET})
	public String list2(Criteria cri, Model model, HttpSession sess) {
		LoginImpl loginUser = (LoginImpl) sess.getAttribute("loginUser");
		String id = loginUser.getId();
		
		if(cri.getCurrentPage() == 0) cri.setCurrentPage(1);
         if(cri.getRowPerpage() == 0) cri.setRowPerpage(10);
         
         List<Board> board = ms.list2(cri, id);
         System.out.println("보드!!!!!!!! : " + board.size());

         model.addAttribute("pageMaker", new Page(ms.getTotalRec(cri), cri));
         model.addAttribute("board", board); //요청
         return"/member/myqnaboard";
      }
	
	@RequestMapping(value="myadoptboard", method= {RequestMethod.POST, RequestMethod.GET})
	public String list3(Criteria cri, Model model, HttpSession sess) {
		LoginImpl loginUser = (LoginImpl) sess.getAttribute("loginUser");
		String id = loginUser.getId();
		
		if(cri.getCurrentPage() == 0) cri.setCurrentPage(1);
         if(cri.getRowPerpage() == 0) cri.setRowPerpage(10);
         
         List<Board> board = ms.list3(cri, id);
         System.out.println("보드!!!!!!!! : " + board.size());

         model.addAttribute("pageMaker", new Page(ms.getTotalRec(cri), cri));
         model.addAttribute("board", board); //요청
         return"/member/myadoptboard";
      }
	
	@RequestMapping(value="myplantboard", method= {RequestMethod.POST, RequestMethod.GET})
	public String list4(Criteria cri, Model model, HttpSession sess) {
		LoginImpl loginUser = (LoginImpl) sess.getAttribute("loginUser");
		String id = loginUser.getId();
		
		if(cri.getCurrentPage() == 0) cri.setCurrentPage(1);
         if(cri.getRowPerpage() == 0) cri.setRowPerpage(10);
         
         List<Board> board = ms.list4(cri, id);
         System.out.println("보드!!!!!!!! : " + board.size());

         model.addAttribute("pageMaker", new Page(ms.getTotalRec(cri), cri));
         model.addAttribute("board", board); //요청
         return"/member/myboard";
      }
	@RequestMapping(value="myadoptreviewboard", method= {RequestMethod.POST, RequestMethod.GET})
	public String list5(Criteria cri, Model model, HttpSession sess) {
		LoginImpl loginUser = (LoginImpl) sess.getAttribute("loginUser");
		String id = loginUser.getId();
		
		if(cri.getCurrentPage() == 0) cri.setCurrentPage(1);
		if(cri.getRowPerpage() == 0) cri.setRowPerpage(10);
		
		List<Board> board = ms.list5(cri, id);
		System.out.println("보드!!!!!!!! : " + board.size());
		
		model.addAttribute("pageMaker", new Page(ms.getTotalRec(cri), cri));
		model.addAttribute("board", board); //요청
		return"/member/myboard";
	}

	@RequestMapping(value="myreply", method= {RequestMethod.POST, RequestMethod.GET})
	public String list6(Criteria cri, Model model, HttpSession sess) {
		LoginImpl loginUser = (LoginImpl) sess.getAttribute("loginUser");
		String id = loginUser.getId();
		
		if(cri.getCurrentPage() == 0) cri.setCurrentPage(1);
		if(cri.getRowPerpage() == 0) cri.setRowPerpage(10);
		
		List<Board> board = ms.list6(cri, id);
		System.out.println("보드!!!!!!!! : " + board.size());
		
		model.addAttribute("pageMaker", new Page(ms.getTotalRec(cri), cri));
		model.addAttribute("board", board); //요청
		return"/member/myreply";
	}

	@RequestMapping(value="myqnareplyboard", method= {RequestMethod.POST, RequestMethod.GET})
	public String list7(Criteria cri, Model model, HttpSession sess) {
		LoginImpl loginUser = (LoginImpl) sess.getAttribute("loginUser");
		String id = loginUser.getId();
		
		if(cri.getCurrentPage() == 0) cri.setCurrentPage(1);
		if(cri.getRowPerpage() == 0) cri.setRowPerpage(10);
		
		List<Board> board = ms.list7(cri, id);
		System.out.println("보드!!!!!!!! : " + board.size());
		
		model.addAttribute("pageMaker", new Page(ms.getTotalRec(cri), cri));
		model.addAttribute("board", board); //요청
		return"/member/myreply";
	}
	


	/*
	 * if(cmd.equals("boardList.bo")) { //게시판 리스트 List<Board> board =
	 * boardService.list(); req.setAttribute("board", board); goView(req, resp,
	 * "/board/boardList.jsp");
	 * 
	 * } else
	 * 
	 * if(cmd.equals("myboard")) { //게시판 리스트 HttpSession sess = req.getSession();
	 * LoginImpl log = (LoginImpl)sess.getAttribute("loginuser"); List<MemInfo>
	 * board =
	 * memInfoService.myboard(((LoginImpl)sess.getAttribute("loginUser")).getId());
	 * req.setAttribute("board", board); goView(req, resp, "/member/myboard.jsp");
	 * 
	 * }else if(cmd.equals("myqnaboard")) { //게시판 리스트 HttpSession sess =
	 * req.getSession(); LoginImpl log = (LoginImpl)sess.getAttribute("loginuser");
	 * List<MemInfo> board =
	 * memInfoService.myqnaboard(((LoginImpl)sess.getAttribute("loginUser")).getId()
	 * ); req.setAttribute("board", board); goView(req, resp,
	 * "/member/myqnaboard.jsp"); }else if(cmd.equals("myadoptboard")) { //게시판 리스트
	 * HttpSession sess = req.getSession(); LoginImpl log =
	 * (LoginImpl)sess.getAttribute("loginuser"); List<MemInfo> board =
	 * memInfoService.myadoptboard(((LoginImpl)sess.getAttribute("loginUser")).getId
	 * ()); req.setAttribute("board", board); goView(req, resp,
	 * "/member/myadoptboard.jsp"); }else if(cmd.equals("myplantboard")) { //게시판 리스트
	 * HttpSession sess = req.getSession(); LoginImpl log =
	 * (LoginImpl)sess.getAttribute("loginuser"); List<MemInfo> board =
	 * memInfoService.myplantboard(((LoginImpl)sess.getAttribute("loginUser")).getId
	 * ()); req.setAttribute("board", board); goView(req, resp,
	 * "/member/myplantboard.jsp");
	 * 
	 * }else if(cmd.equals("myadoptreviewboard")) { //게시판 리스트 HttpSession sess =
	 * req.getSession(); LoginImpl log = (LoginImpl)sess.getAttribute("loginuser");
	 * List<MemInfo> board =
	 * memInfoService.myadoptreviewboard(((LoginImpl)sess.getAttribute("loginUser"))
	 * .getId()); req.setAttribute("board", board); goView(req, resp,
	 * "/member/myadoptreviewboard.jsp"); } else if(cmd.equals("myreply")) { //게시판
	 * 리스트 HttpSession sess = req.getSession(); LoginImpl log =
	 * (LoginImpl)sess.getAttribute("loginuser"); List<MemInfo> board =
	 * memInfoService.myreply(((LoginImpl)sess.getAttribute("loginUser")).getId());
	 * req.setAttribute("board", board); goView(req, resp, "/member/myreply.jsp"); }
	 * else if(cmd.equals("myqnareplyboard")) { //게시판 리스트 HttpSession sess =
	 * req.getSession(); LoginImpl log = (LoginImpl)sess.getAttribute("loginUser");
	 * List<MemInfo> board =
	 * memInfoService.myqnareplyboard(((LoginImpl)sess.getAttribute("loginUser")).
	 * getId()); req.setAttribute("board", board); goView(req, resp,
	 * "/member/myqnareplyboard.jsp"); }
	 * 
	 * else if(cmd.equals("mypage")) { //게시판 세부내용 List<Board> board = HttpSession
	 * sess = req.getSession(); LoginImpl log =
	 * (LoginImpl)sess.getAttribute("loginUser"); Plantmember board =
	 * memInfoService.mypage(((LoginImpl)sess.getAttribute("loginUser")).getId());
	 * System.out.println("컨트롤러 ID : " + board.getId()); req.setAttribute("board",
	 * board); goView(req, resp, "/member/mypage.jsp"); }
	 * 
	 * } void goView(HttpServletRequest req, HttpServletResponse resp, String
	 * viewPage) throws ServletException, IOException { RequestDispatcher rd =
	 * req.getRequestDispatcher(viewPage); rd.forward(req, resp);
	 * 
	 * }
	 */}