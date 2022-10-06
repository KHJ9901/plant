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
import com.plant.dto.Diary;
import com.plant.dto.Page;
import com.plant.service.DiaryService;

@Controller
@RequestMapping(value="/diary/")

public class DiaryController {

	@Autowired
	DiaryService diaryService;
	
	@RequestMapping(value="list", method= {RequestMethod.POST, RequestMethod.GET})
	private String list(Criteria cri, Model model)  {

		if(cri.getCurrentPage() == 0) cri.setCurrentPage(1);
		if(cri.getRowPerpage() == 0) cri.setRowPerpage(8);
		
		List<Diary> diary = diaryService.list(cri);
		
		model.addAttribute("pageMaker", new Page(diaryService.getTotalRec(cri), cri));
		model.addAttribute("diary", diary);
		return "/diary/diary";
	}

	@GetMapping("diaryDetail")
	public String diaryDetail(@ModelAttribute("seqno") String seqno,
						  	  @ModelAttribute("page") String page, 
						  	  Model model) {
		
		model.addAttribute("diary", diaryService.searchDiary(seqno));
		if(page.equals("diaryModify")) {
			return "/diary/diaryModify";
			} else {
			return "/diary/diary";
			}
	}
	
	@GetMapping("diaryReg")
	public String diaryReg() { 
		return "/diary/diaryReg";
	}
	
	@PostMapping("diaryRegister")
	public String diaryRegister(Diary diary,
							   MultipartFile filename,
							   HttpSession sess,
							   RedirectAttributes rttr) {
		
		diary.setId(((LoginImpl)sess.getAttribute("loginUser")).getId());
		
		rttr.addFlashAttribute("diary_seqno", diaryService.insertDiary(diary));
		
		return "redirect:list";
	}
	
	@GetMapping("diaryDelete")
	public String diaryDelete(@ModelAttribute("seqno") String seqno) {
		
		diaryService.diarydel(seqno);
		return "redirect:list";
	}

	@RequestMapping(value="diaryUpdate", method= RequestMethod.POST)
	public String update(Diary diary,
			   			 MultipartFile filename,
			   			 HttpSession sess,
			   			 Model model) throws Exception {
		
		LoginImpl login = (LoginImpl)sess.getAttribute("loginUser");	
		diary.setId(login.getId());
		
		model.addAttribute("diary_seqno", diaryService.update(diary));
		return "redirect:list";
		
	}

}
