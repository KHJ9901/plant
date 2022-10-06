package com.plant.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RestController;

import com.plant.dto.Criteria;
import com.plant.dto.Qna;
import com.plant.service.QnaService;

@RestController
@RequestMapping("/qn/")
public class QnaRestController {

	private static final Logger log = LoggerFactory.getLogger(QnaRestController.class);
	
	@Autowired
	QnaService as;
	
	@GetMapping(value="getText", produces="text/plain; charset=utf-8")
	public String getText() {
		log.info("MIME TYPE:" + MediaType.TEXT_PLAIN_VALUE);
		return "hello, 안녕";
	}
	
	@GetMapping(value="getList")
	public List<Qna> getList(){
		Criteria cri = new Criteria(1,5);
		return as.list(cri);	
	}
	
	@GetMapping(value="getMap")
	public Map<String, Qna> getMap(){
		Map<String, Qna> map = new HashMap<>();
		map.put("First", as.searchAsk("1"));
		map.put("Second", as.searchAsk("4"));
		return map;
	}

	@GetMapping(value="check", params= {"page", "record"})
	public ResponseEntity<List<Qna>> check(Integer page, Integer record){
		
		List<Qna> adoptList = as.list(new Criteria(page, record));

		ResponseEntity<List<Qna>> result = null;
		
		if(page < 1 || record > 100) {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(adoptList);
		} else {
			result = ResponseEntity.status(HttpStatus.OK).body(adoptList);
		}
		
		return result;
	}
	
	@GetMapping("qna/page/{pno}/record/{rno}/{key}/{value}")
	public List<Qna> getSearchAdopt(
						@PathVariable("pno") Integer currPage,
						@PathVariable("rno") Integer rowPerPage,
						@PathVariable("key") String searchItem,
						@PathVariable("value") String searchValue){
		
		Criteria cri = new Criteria(currPage, rowPerPage);
		cri.setSearchField(searchItem);
		cri.setSearchText(searchValue);
		
		return as.list(cri);
		
	}
	
	@PostMapping("qna")
	public Qna convert(@RequestBody Qna q) {
		log.info("convert adopt" + q);
		return q;
	}
	
}
