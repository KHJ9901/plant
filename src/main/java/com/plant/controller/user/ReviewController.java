package com.plant.controller.user;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.plant.common.LoginImpl;
import com.plant.dto.Criteria;
import com.plant.dto.Page;
import com.plant.dto.Review;
import com.plant.service.ReviewService;

@Controller
@RequestMapping(value="/review/")
public class ReviewController {
	
	@Autowired
	ReviewService reviewService;
	
	//리스트
	@RequestMapping(value="list", method= {RequestMethod.POST, RequestMethod.GET})
	private String list(Criteria cri, Model model)  {

		if(cri.getCurrentPage() == 0) cri.setCurrentPage(1);
		if(cri.getRowPerpage() == 0) cri.setRowPerpage(8);
		
		List<Review> review = reviewService.list(cri);
		
		model.addAttribute("pageMaker", new Page(reviewService.getTotalRec(cri), cri));
		model.addAttribute("review", review);
		return "/adopt/reviewList";
	}
	
	//디테일
	@GetMapping("rdetail")
	public String rdetail(@ModelAttribute("seqno") String seqno,
						  @ModelAttribute("page") String page, 
						  Model model) {
		
		model.addAttribute("review", reviewService.searchReview(seqno));
		if(page.equals("rModify")) {
			return "/adopt/rModify";
			} else {
			return "/adopt/rdetail";
			}
	}
	
	//등록 폼
	@GetMapping("rRegForm")
	public String rRegForm() { 
		return "/adopt/rRegForm";
	}
	
	//등록
	@PostMapping("rRegister")
	public String rRegister(Review review,
						   MultipartFile filename,
						   HttpSession sess,
						   RedirectAttributes rttr) {
		
		review.setId(((LoginImpl)sess.getAttribute("loginUser")).getId());
		
		rttr.addFlashAttribute("seqno", reviewService.insertReview(review));
		
		return "redirect:rdetail";
	}
	
	//삭제
	@GetMapping("rdelete")
	public String rdelete(@ModelAttribute("seqno") String seqno) {
		
		reviewService.reviewdel(seqno);
		return "redirect:list";
	}
	
	//수정
	@RequestMapping(value="rUpdate", method= RequestMethod.POST)
	public String update(Review review,
			   			 MultipartFile filename,
			   			 HttpSession sess,
			   			 Model model) throws Exception {
		
		LoginImpl login = (LoginImpl)sess.getAttribute("loginUser");	
		review.setId(login.getId());
		
		model.addAttribute("seqno", reviewService.update(review));
		return "redirect:rdetail";
		
	}
	
	
}
	
	
	
	
/*
	@WebServlet(urlPatterns = {"/ro/*"})
	public class ReviewController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	public ReviewController() {
		super();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doAction(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doAction(req, resp);
	}
	
	private void doAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//req.setCharacterEncoding("utf-8");
		String uri = req.getRequestURI();	
		String cmd = uri.substring(uri.lastIndexOf("/")+1);
		
		System.out.println("cmd=" + cmd);

		ReviewServiceImp reviewService = new ReviewServiceImp();
		
		if(cmd.equals("review")) {
			//리스트
			String currentPage = req.getParameter("currentPage");
			String rowPerPage = req.getParameter("rowPerpage");
			if(currentPage == null) currentPage = "1";
			if(rowPerPage == null) rowPerPage = "5";
			Criteria cri = new Criteria(Integer.parseInt(currentPage), Integer.parseInt(rowPerPage));
			
			List<Review> review = reviewService.list(cri);
			req.setAttribute("pageMaker", new Page(reviewService.getTotalRec(), cri));
			req.setAttribute("review", review);
			goView(req, resp, "/adopt/review.jsp");

		} else if(cmd.equals("reviewDetail")) {
			//게시판 세부내용
			String seqno = req.getParameter("seqno");
			if(seqno == null) {
				seqno = (String)req.getAttribute("seqno");
			}
			req.setAttribute("review", reviewService.searchReview(seqno));
			
			String page = req.getParameter("page");
			
			if(page != null) {
					goView(req, resp, "/adopt/reviewModify.jsp");
				} else {
					goView(req, resp, "/adopt/reviewDetail.jsp");
				}
			} else if(cmd.equals("reviewRegForm")) {
				goView(req,resp,"/adopt/reviewReg.jsp");
			} else if(cmd.equals("reviewReg")) {
				req.setAttribute("seqno", reviewService.insertReview(req,resp));
				goView(req, resp, "/ro/reviewDetail");
			} else if(cmd.equals("reviewModify")) {
				req.setAttribute("seqno", reviewService.update(req,resp));
				goView(req, resp, "/ro/reviewDetail");
			} else if(cmd.equals("reviewDelete")) {
				reviewService.reviewdel(req.getParameter("seqno"));
				goView(req, resp, "/ro/review");
			}
	}
	
	void goView(HttpServletRequest req, HttpServletResponse resp, String viewPage) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher(viewPage);
		rd.forward(req, resp);
	}
}
*/
