package com.plant.controller.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.plant.common.LoginImpl;
import com.plant.dto.Criteria;
import com.plant.dto.Page;
import com.plant.dto.Qna;
import com.plant.service.QnaService;
import com.plant.service.QnaServiceImp;

@Controller
@RequestMapping(value="/qna/")
public class QnaController{
	
	@Autowired
	QnaService qnaservice;
	
	@RequestMapping(value="qnalist", method= {RequestMethod.GET, RequestMethod.POST})
	public String qnalist(Criteria cri, Model model) {
		
		if(cri.getCurrentPage() == 0) cri.setCurrentPage(1);
		if(cri.getRowPerpage() == 0) cri.setRowPerpage(5);
		
		List<Qna> qna = qnaservice.list(cri);
		
		model.addAttribute("pageMaker", new Page(qnaservice.getTotalRec(cri), cri));
		model.addAttribute("ask", qna);
		return "/board/qna";
	}
	
	@RequestMapping(value="detail", method= RequestMethod.GET)
	public String detail(@ModelAttribute("seqno") String seqno, @ModelAttribute("page") String page, Model model) {
		model.addAttribute("ask", qnaservice.searchAsk(seqno));
		if(page.equals("qnamodify")) {return "/board/qnamodify";}
		else {return "/board/qnaDetail";}
	}
	
	@RequestMapping(value="qnanew", method= RequestMethod.GET)
	public String qnanew() {
		
		return "/board/qnaReg";
	}
	
	@PostMapping("register")
	public String register(Qna qna,
						   MultipartFile qnaimg,
						   HttpSession sess,
						   RedirectAttributes rttr) {
		qna.setId(((LoginImpl)sess.getAttribute("loginUser")).getId());
		
		rttr.addFlashAttribute("seqno", qnaservice.insert(qna, qnaimg));
		
		return "redirect:/qna/detail";
	}
	
	@RequestMapping(value="qnaUpdate", method= RequestMethod.POST)
	public String update(HttpSession sess, Model model,
						 Qna qna, MultipartFile filename) throws Exception {
		
		LoginImpl login = (LoginImpl)sess.getAttribute("loginUser");	
		qna.setId(login.getId());
		model.addAttribute("seqno", qnaservice.update(qna, filename));
		return "redirect:detail";
	}
	
	@RequestMapping(value="qnaDelete", method= RequestMethod.GET)
	public String Delete(@ModelAttribute("seqno") String seqno) {
		qnaservice.qnadel(seqno);
		return "redirect:qnalist";
	}
//	//질문 수정
//} else if(cmd.equals("qnaupdate")) {
//	req.setAttribute("seqno", askService.update(req, resp));
//	goView(req, resp, "/qn/qnaDetail");
//
//	//질문 삭제
//} else if(cmd.equals("qnaDelete")) {
//	askService.qnadel(req.getParameter("seqno"));
//	
//	 resp.setContentType("text/html; charset=UTF-8");
//     PrintWriter writer = resp.getWriter();
//     writer.println("<script>alert('정말삭제하시겠습니까'); location.href='/qn/qna';</script>"); 
//     writer.close();
//	private void doAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		resp.setContentType("text/html; charset=utf8");
//		req.setCharacterEncoding("utf8");
//		
//		QnaServiceImp askService = new QnaServiceImp();
//		
//		if(cmd.equals("qna")) {
//			//질문 리스트
//			String currentPage = req.getParameter("currentPage");
//			String rowPerPage = req.getParameter("rowPerpage");
//			if(currentPage == null) currentPage = "1";
//			if(rowPerPage == null) rowPerPage = "5";
//			Criteria cri = new Criteria(Integer.parseInt(currentPage), Integer.parseInt(rowPerPage));
//			
//			List<Qna> ask = askService.list(cri);
//			req.setAttribute("pageMaker", new Page(askService.getTotalRec(), cri));
//			req.setAttribute("ask", ask);
//			goView(req, resp, "/board/qna.jsp");
//			
//		} else if (cmd.equals("qnaDetail")) {
//			//질문 세부내용
//			String seqno = req.getParameter("seqno");
//			if(seqno == null) {
//				seqno = (String)req.getAttribute("seqno");
//			}
//			
//			req.setAttribute("ask", askService.searchAsk(seqno));
//			String page = req.getParameter("page");
//			
//			if(page != null) {			
//				goView(req, resp, "/board/qnamodify.jsp");
//			} else {
//				goView(req, resp, "/board/qnaDetail.jsp");
//			}
//			
//			//질문 도움돼요
//		} else if (cmd.equals("qnaLike")){
//			askService.asklike(req);
//			String seqno = req.getParameter("seqno");
//			String id = req.getParameter("id");
//			req.setAttribute("like", askService.searchAsk(seqno));
//			req.setAttribute("id", id);
//			req.setAttribute("seqno", seqno);
//			goView(req, resp, "/qn/qnaDetail");
//			
//			//질문 등록
//		} else if (cmd.equals("qnawrite")) {
//			req.setAttribute("seqno", askService.Qnanew(req, resp));
//			goView(req, resp, "/qn/qnaDetail");
//			
//			
//			//질문 등록
//		} else if (cmd.equals("qnareg")) {
//			req.setAttribute("qna", askService.Qnanew(req, resp));
//			goView(req, resp, "/board/qnaReg.jsp");
//		
//			//질문 수정
//		} else if(cmd.equals("qnaupdate")) {
//			req.setAttribute("seqno", askService.update(req, resp));
//			goView(req, resp, "/qn/qnaDetail");
//		
//			//질문 삭제
//		} else if(cmd.equals("qnaDelete")) {
//			askService.qnadel(req.getParameter("seqno"));
//			
//			 resp.setContentType("text/html; charset=UTF-8");
//	         PrintWriter writer = resp.getWriter();
//	         writer.println("<script>alert('정말삭제하시겠습니까'); location.href='/qn/qna';</script>"); 
//	         writer.close();
//	        
//	         //댓글 등록
//		} else if(cmd.equals("reply")) {
//			askService.reply(req);
//			String seqno = req.getParameter("qnaseqno");
//			req.setAttribute("seq", askService.searchAsk(seqno));
//			req.setAttribute("seqno", seqno);
//			goView(req, resp, "/qn/qnaDetail");				
//		}
//		
//		
//	}
//		
//	
//	void goView(HttpServletRequest req, HttpServletResponse resp, String viewPage) throws ServletException, IOException {
//		RequestDispatcher rd = req.getRequestDispatcher(viewPage);
//		rd.forward(req, resp);
//
//	}
}