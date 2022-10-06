package com.plant.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.plant.dto.Adopt;
import com.plant.dto.Criteria;

public interface AdoptService {
	
	public List<Adopt> list(Criteria cri);

	public int getTotalRec(Criteria criteria);
	
	public Adopt searchAdopt(String seqno);
	
	public String insertAdopt(HttpServletRequest req, HttpServletResponse resp);
	
	public String insertAdopt(Adopt adopt);
	
	public String update(HttpServletRequest req, HttpServletResponse resp);
	
	public String update(Adopt adopt);

	public void adoptdel(String seqno);


}
