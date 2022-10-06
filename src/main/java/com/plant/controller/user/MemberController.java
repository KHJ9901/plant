package com.plant.controller.user;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.plant.dto.Plantmember;
import com.plant.service.MemberService;


@Controller
@RequestMapping("/lo/")
public class MemberController {
	
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	@Autowired 
	private MemberService ms;
//	
//	@PostMapping(value="add",
//			consumes = "application/json",
//			produces = "text/plain; charset=utf-8")
//	public ResponseEntity<String> create(@RequestBody Plantmember member){
//		log.info("MemberController create() called.." + member);
//		int rs = ms.insertmember(member);
//		return rs == 1 ? new ResponseEntity<>("회원등록완료", HttpStatus.OK)
//				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	
	

	@GetMapping("loginview")
	public String loginview() {
		return "/member/login";
	}

	@RequestMapping("memRegForm")
	public String memRegForm() {
		return "/member/memregform";
	}
	
	@RequestMapping("pwlost")
	public String pwlost() {
		return "/member/pwlost";
	}
	
	@RequestMapping("signup")
	public String signup(Plantmember member) {

		ms.insertMember(member);
		return "/member/login";
	}
	
	@RequestMapping("editmypage")
	public String editmypage(HttpSession sess, Model model) {
		
		String id = (String)sess.getAttribute("sess_id");
		
		Plantmember board = ms.editview(id);
		
		model.addAttribute("board", board);
		return "/member/mypageEdit";
	};
	
	@RequestMapping("editsuccese")
	public String editsuccese(Plantmember member,Model model) {
		ms.mypageEdit(member);
		
		model.addAttribute("id",ms.mypageEdit(member).getId());
		return "redirect:/me/mypageview";
	};


	@RequestMapping(value="logout", method = RequestMethod.GET)
	public String logoutMainGET(HttpServletRequest req) throws Exception{
		log.info("logoutMainGET메서드 진입");

		HttpSession session = req.getSession();

		session.invalidate();

		return "redirect:/"; 
	}

	
	
	
	@RequestMapping("pwfind")
	public String pwfind(Plantmember member) {
		ms.pwfind(member.getId(), member.getName(), member.getEmail());
		return"/member/pwfind";
	}
	
	@RequestMapping("memberdelete")
	public String memberdelete(HttpSession sess) {
		String id = (String)sess.getAttribute("sess_id");
		ms.memdel(id);
		sess.invalidate();
		return"redirect:/";
	}
	

	
}






/*
 * else if(cmd.equals("pwlost")) { goView(req, resp, "/member/pwlost.jsp"); }
 * 
 * else if(cmd.equals("memberReg")) { System.out.println("회원등록 요청");
 * memberService.insert(req); req.setAttribute("msg", "memberOk"); goView(req,
 * resp, "/member/login.jsp"); } else if(cmd.equals("pwfind")) {
 * 
 * 
 * 
 * String id = req.getParameter("pwlostId"); String name =
 * req.getParameter("pwlostname"); String email =
 * req.getParameter("pwlostemail"); Map<String, String> find =
 * memberService.pwfind(id, name, email);
 * 
 * String viewPage=null; if(find.get("find").equals("pwfind")) {
 * req.setAttribute("member", memberService.pwfind(id, name, email));
 * //req.setAttribute("pw", find.get("pw")); viewPage="/member/pwfind.jsp";
 * }else {
 * 
 * viewPage="/member/pwlost.jsp"; } req.setAttribute("find", find.get("find"));
 * goView(req, resp, viewPage);
 * 
 * }
 * 
 * else if(cmd.equals("mypageEditpage")) { String id = null;
 * 
 * HttpSession sess = req.getSession(); LoginImpl log =
 * (LoginImpl)sess.getAttribute("loginUser"); Plantmember member =
 * memberService.mypageEditpage(id); req.setAttribute("member", member);
 * goView(req, resp, "/member/mypageEdit.jsp"); } } }
 */
