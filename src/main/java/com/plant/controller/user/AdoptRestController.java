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

import com.plant.dto.Adopt;
import com.plant.dto.Criteria;
import com.plant.service.AdoptService;

@RestController
@RequestMapping("/ad/")
public class AdoptRestController {
	
	private static final Logger log = LoggerFactory.getLogger(AdoptRestController.class);
	
	@Autowired
	AdoptService as;
	
	@GetMapping(value="getText", produces="text/plain; charset=utf-8")
	public String getText() {
		log.info("MIME TYPE:" + MediaType.TEXT_PLAIN_VALUE);
		return "hello, 안녕";
	}
	
	@GetMapping(value="getList")
	public List<Adopt> getList(){
		Criteria cri = new Criteria(1,5);
		return as.list(cri);	
	}
	
	@GetMapping(value="getMap")
	public Map<String, Adopt> getMap(){
		Map<String, Adopt> map = new HashMap<>();
		map.put("First", as.searchAdopt("1"));
		map.put("Second", as.searchAdopt("4"));
		return map;
	}

	@GetMapping(value="check", params= {"page", "record"})
	public ResponseEntity<List<Adopt>> check(Integer page, Integer record){
		
		List<Adopt> adoptList = as.list(new Criteria(page, record));

		ResponseEntity<List<Adopt>> result = null;
		
		if(page < 1 || record > 100) {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(adoptList);
		} else {
			result = ResponseEntity.status(HttpStatus.OK).body(adoptList);
		}
		
		return result;
	}
	
	@GetMapping("adopt/page/{pno}/record/{rno}/{key}/{value}")
	public List<Adopt> getSearchAdopt(
						@PathVariable("pno") Integer currPage,
						@PathVariable("rno") Integer rowPerPage,
						@PathVariable("key") String searchItem,
						@PathVariable("value") String searchValue){
		
		Criteria cri = new Criteria(currPage, rowPerPage);
		cri.setSearchField(searchItem);
		cri.setSearchText(searchValue);
		
		return as.list(cri);
		
	}
	
	@PostMapping("adopt")
	public Adopt convert(@RequestBody Adopt a) {
		log.info("convert adopt" + a);
		return a;
	}
	
}
