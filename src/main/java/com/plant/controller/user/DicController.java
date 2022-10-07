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
import com.plant.dto.*;
import com.plant.service.*;

@Controller
@RequestMapping(value="/dic/")
public class DicController {

	@Autowired
	DicService ds;
	
//-----------------------------리스트--------------------------------------------
	@RequestMapping(value="dictionary", method= {RequestMethod.POST, RequestMethod.GET})
	public String Diclist(Criteria cri, Model model) {
		
		if(cri.getCurrentPage() == 0) cri.setCurrentPage(1);
		if(cri.getRowPerpage() == 0) cri.setRowPerpage(30);
		
		List<Dictionary> diction = ds.list(cri);
		
		model.addAttribute("pageMaker", new Page(ds.getDicRec(cri), cri));			
		model.addAttribute("diction", diction);
		return "/dictionary/dictionary";
	}
	
	@RequestMapping(value="dictionaryIn", method= {RequestMethod.POST, RequestMethod.GET})
	public String DicInlist(Criteria cri, Model model) {
		if(cri.getCurrentPage() == 0) cri.setCurrentPage(1);
		if(cri.getRowPerpage() == 0) cri.setRowPerpage(40);
		
		List<Dictionary> dictionin = ds.inList(cri);
		
		model.addAttribute("pageMaker", new Page(ds.getDicInRec(cri), cri));			
		model.addAttribute("dictionin", dictionin);
		return "/dictionary/dictionaryIn";
		
	}
	
	@RequestMapping(value="dictionaryOut", method= {RequestMethod.POST, RequestMethod.GET})
	public String DicOutlist(Criteria cri, Model model) {
		if(cri.getCurrentPage() == 0) cri.setCurrentPage(1);
		if(cri.getRowPerpage() == 0) cri.setRowPerpage(40);
		
		List<Dictionary> dictionout = ds.outList(cri);
		
		model.addAttribute("pageMaker", new Page(ds.getDicOutRec(cri), cri));			
		model.addAttribute("dictionout", dictionout);
		return "/dictionary/dictionaryOut";
		
	}
	
	@RequestMapping(value="mplant", method= {RequestMethod.POST, RequestMethod.GET})
	public String list(Criteria cri, Model model) {
		if(cri.getCurrentPage() == 0) cri.setCurrentPage(1);
		if(cri.getRowPerpage() == 0) cri.setRowPerpage(40);
		
		List<Mplant> mplant = ds.mpList(cri);
		
		model.addAttribute("pageMaker", new Page(ds.getMplantRec(cri), cri));			
		model.addAttribute("mplant", mplant);
		return "/dictionary/memPlant";
		
	}
	
	
	
//-----------------------------상세페이지--------------------------------------------
	@RequestMapping(value="dicDetail", method= {RequestMethod.POST, RequestMethod.GET})
	public String dicdetail(@ModelAttribute("seqno") String seqno, 
												     Model model) {
		
		model.addAttribute("dictiondetail", ds.DicDetail(seqno));
		 
		return "/dictionary/detailPage";
	}
	
	@RequestMapping(value="mplantDetail", method= {RequestMethod.POST, RequestMethod.GET}) 
	public String mpdetail(@ModelAttribute("seqno") String seqno, 
												    Model model) {
		
		model.addAttribute("mplantdetail", ds.MpDetail(seqno));
		
		return "/dictionary/memPlantDetail";
	}
	
	
	
	
//-----------------------------뷰페이지 이동--------------------------------------------
	//회원 식물 등록 페이지//
	@GetMapping("mpInsertForm")
	public String insertForm() {
		return "/dictionary/mpInsertForm";
	}
	
	//회원 식물 수정 페이지//
	@GetMapping("mpModifyForm")
	public String modifyForm(@ModelAttribute("seqno") String seqno,
													Model model) {
		model.addAttribute("mplantdetail", ds.MpDetail(seqno));
		
		return "/dictionary/mpModifyForm";
	}
	
	
	
	
//-----------------------------회원 식물 삽입--------------------------------------------
	@PostMapping("insertMplant")
	public String register(Mplant mplant, 
						   MultipartFile filename,
						   HttpSession sess,
						   RedirectAttributes rttr) { //리다이렉트 할때 필요함
		
		mplant.setId(((LoginImpl)sess.getAttribute("loginUser")).getId());
		
		rttr.addFlashAttribute("seqno", ds.insertMp(mplant, filename)); 
			// addFlash는 객체를 담음
		
		return "redirect:mplantDetail";
	}

	
	
//-----------------------------회원 식물 수정--------------------------------------------
	@PostMapping("updateMplant")
	public String update (Mplant mplant, 
						   MultipartFile filename,
						   HttpSession sess,
						   RedirectAttributes rttr) { //리다이렉트 할때 필요함
		mplant.setId(((LoginImpl)sess.getAttribute("loginUser")).getId());
		rttr.addFlashAttribute("seqno", ds.updateMp(mplant, filename)); 
			// addFlash는 객체를 담음
		
		return "redirect:memPlantDetail";
	}

	
	
//-----------------------------회원 식물 삭제--------------------------------------------
	@PostMapping("deleteMplant")
	public String register(Mplant mplant, 
						   String seqno,
						   HttpSession sess) {
		
		mplant.setId(((LoginImpl)sess.getAttribute("loginUser")).getId());
		
		ds.deleteMp(seqno); 
		
		return "redirect:memPlant";
	}
}