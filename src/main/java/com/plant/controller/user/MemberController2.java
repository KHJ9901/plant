package com.plant.controller.user;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.plant.common.LoginImpl;
import com.plant.dto.Plantmember;
import com.plant.mapper.PlantmemberMapper;
import com.plant.service.MemberService;

@RestController
@RequestMapping("/member/")
public class MemberController2 {
	
	private static final Logger log = LoggerFactory.getLogger(MemberController2.class);
	@Autowired 
	private PlantmemberMapper mapper;
	
	@PostMapping(value="add",
			consumes = "application/json",
			produces = "text/plain; charset=utf-8")
	public ResponseEntity<String> create(@RequestBody Plantmember member){
		int rs = mapper.insertmember(member);
		return rs == 1 ? new ResponseEntity<>("회원등록완료", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
//	@GetMapping(value="{id}",
//		    produces = {MediaType.APPLICATION_XML_VALUE,
//		    		    MediaType.APPLICATION_JSON_UTF8_VALUE})
//	public ResponseEntity<String> get(@PathVariable("id") String id){
//	log.info("get id:" + id);
//	return mapper.get(id) == 1 ? 
//				new ResponseEntity<>("댓글 삭제 완료", HttpStatus.OK) 
//				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//}
	@RequestMapping("idDoubleCheck.do")
	public @ResponseBody int idDoubleCheck(String id) {
		int result = mapper.idDoubleCheck(id);
		System.out.println("idididididididididididididididididid : " + id);
		return result;
	}

	@RequestMapping(value="memdel.do", method=RequestMethod.POST)
	public String memdel(RedirectAttributes rttr,HttpSession session)throws Exception{
		LoginImpl loginUser = (LoginImpl) session.getAttribute("loginUser");
			
		String id = loginUser.getId();
		System.out.println("id : " + id);
		mapper.memdel(id);
		session.invalidate();
		rttr.addFlashAttribute("msg", "이용해주셔서 감사합니다.");

		return "redirect:/lo/loginview";
	}
	
}