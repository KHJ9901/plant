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
import com.plant.dto.Adopt;
import com.plant.dto.Criteria;
import com.plant.dto.Page;
import com.plant.service.AdoptService;

@Controller
@RequestMapping(value="/adopt/")
public class AdoptController {

	@Autowired
	AdoptService adoptService;
	
	//리스트
	@RequestMapping(value="list", method= {RequestMethod.POST, RequestMethod.GET})
	private String list(Criteria cri, Model model)  {

		if(cri.getCurrentPage() == 0) cri.setCurrentPage(1);
		if(cri.getRowPerpage() == 0) cri.setRowPerpage(8);
		
		List<Adopt> adopt = adoptService.list(cri);
		
		model.addAttribute("pageMaker", new Page(adoptService.getTotalRec(cri), cri));
		model.addAttribute("adopt", adopt);
		return "/adopt/adoptList";
	}
	
	//디테일
	@GetMapping("adetail")
	public String adetail(@ModelAttribute("seqno") String seqno,
						  @ModelAttribute("page") String page, 
						  Model model) {
		
		model.addAttribute("adopt", adoptService.searchAdopt(seqno));
		if(page.equals("aModify")) {
			return "/adopt/aModify";
			} else {
			return "/adopt/adetail";
			}
	}
	
	//등록 폼
	@GetMapping("aRegForm")
	public String aRegForm() { 
		return "/adopt/aRegForm";
	}
	
	//등록
	@PostMapping("aRegister")
	public String aRegister(Adopt adopt,
						   MultipartFile filename,
						   HttpSession sess,
						   RedirectAttributes rttr) {
		
		adopt.setId(((LoginImpl)sess.getAttribute("loginUser")).getId());
		
		rttr.addFlashAttribute("seqno", adoptService.insertAdopt(adopt));
		
		return "redirect:/adopt/adetail";
	}
	
	//삭제
	@GetMapping("adelete")
	public String adelete(@ModelAttribute("seqno") String seqno) {
		
		adoptService.adoptdel(seqno);
		return "redirect:list";
	}


	//수정
	@RequestMapping(value="aUpdate", method= RequestMethod.POST)
	public String update(Adopt adopt,
			   			 MultipartFile filename,
			   			 HttpSession sess,
			   			 Model model) throws Exception {
		
		LoginImpl login = (LoginImpl)sess.getAttribute("loginUser");	
		adopt.setId(login.getId());
		
		model.addAttribute("seqno", adoptService.update(adopt));
		return "redirect:adetail";
		
	}

	
}
