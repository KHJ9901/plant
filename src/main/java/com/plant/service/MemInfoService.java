package com.plant.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.plant.dto.Board;
import com.plant.dto.Criteria;
import com.plant.dto.MemInfo;
import com.plant.dto.Plantmember;

public interface MemInfoService {
	
	

	public MemInfo searchBoard(String seqno);

	
	List<MemInfo> myqnaboard(Criteria cri, Model model, HttpSession sess);
	
	List<MemInfo> myadoptboard(Criteria cri, Model model, HttpSession sess);

	List<MemInfo> myplantboard(Criteria cri, Model model, HttpSession sess);

	List<MemInfo> myadoptreviewboard(Criteria cri, Model model, HttpSession sess);

	List<MemInfo> myreply(Criteria cri, Model model, HttpSession sess);

	List<MemInfo> myqnareplyboard(Criteria cri, Model model, HttpSession sess);

	public Plantmember mypage(String id);


	public List<Board> list(Criteria cri, String id);


	public int getTotalRec(Criteria cri);

	public List<Board> myqnaboard(Criteria cri, String id);




	
	
}
