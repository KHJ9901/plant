package com.plant.controller.user;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.plant.common.LoginImpl;
import com.plant.dto.*;
import com.plant.service.*;

@RestController
@RequestMapping("/dicsearch/")
public class RestDicController {

	private static final Logger log = LoggerFactory.getLogger("RestControllerSample.class");
	
	@Inject
	DicService ds;
	
	//요청방법 : http://localhost:8080/board/43/page/1/record/5/title/좋은날
//검색========================================================
	@GetMapping("dictionary/page/{pno}/record/{rno}/{key}/{value}") // => {sno} 시퀀스넘버, {pno} 페이지넘버, {rno} 레코드넘버
	public List<Dictionary> getSearchDiction(
			@PathVariable("pno") Integer currPage,
			@PathVariable("rno") Integer rowPerPage,
			@PathVariable("key") String searchItem,
			@PathVariable("value") String searchValue
			) {
		
		Criteria cri = new Criteria(currPage, rowPerPage);
		cri.setSearchField(searchItem);
		cri.setSearchText(searchValue);
		
		return ds.list(cri);
	}
	@GetMapping("dictionaryIn/page/{pno}/record/{rno}/{key}/{value}") // => {sno} 시퀀스넘버, {pno} 페이지넘버, {rno} 레코드넘버
	public List<Dictionary> getSearchDictionIn(
			@PathVariable("pno") Integer currPage,
			@PathVariable("rno") Integer rowPerPage,
			@PathVariable("key") String searchItem,
			@PathVariable("value") String searchValue
			) {
		
		Criteria cri = new Criteria(currPage, rowPerPage);
		cri.setSearchField(searchItem);
		cri.setSearchText(searchValue);
		
		return ds.inList(cri);
	}
	@GetMapping("dictionaryOut/page/{pno}/record/{rno}/{key}/{value}") // => {sno} 시퀀스넘버, {pno} 페이지넘버, {rno} 레코드넘버
	public List<Dictionary> getSearchDictionOut(
			@PathVariable("pno") Integer currPage,
			@PathVariable("rno") Integer rowPerPage,
			@PathVariable("key") String searchItem,
			@PathVariable("value") String searchValue
			) {
		
		Criteria cri = new Criteria(currPage, rowPerPage);
		cri.setSearchField(searchItem);
		cri.setSearchText(searchValue);
		
		return ds.outList(cri);
	}
	
	@GetMapping("mplant/page/{pno}/record/{rno}/{key}/{value}") // => {sno} 시퀀스넘버, {pno} 페이지넘버, {rno} 레코드넘버
	public List<Mplant> getSearchMplant(
			@PathVariable("pno") Integer currPage,
			@PathVariable("rno") Integer rowPerPage,
			@PathVariable("key") String searchItem,
			@PathVariable("value") String searchValue
			) {
		
		Criteria cri = new Criteria(currPage, rowPerPage);
		cri.setSearchField(searchItem);
		cri.setSearchText(searchValue);
		
		return ds.mpList(cri);
	}
	

}	
