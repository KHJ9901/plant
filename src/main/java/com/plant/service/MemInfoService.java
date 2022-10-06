package com.plant.service;

import java.util.List;

import com.plant.dto.Board;
import com.plant.dto.Criteria;
import com.plant.dto.MemInfo;
import com.plant.dto.Plantmember;

public interface MemInfoService {
	
	

	public MemInfo searchBoard(String seqno);


	List<MemInfo> myboard(String id);
	
	List<MemInfo> myqnaboard(String id);
	
	List<MemInfo> myadoptboard(String id);

	List<MemInfo> myplantboard(String id);

	List<MemInfo> myadoptreviewboard(String id);

	List<MemInfo> myreply(String id);

	List<MemInfo> myqnareplyboard(String id);

	public Plantmember mypage(String id);


	public List<Board> list(Criteria cri);


	public int getTotalRec(Criteria cri);


	
	
}
