package com.plant.dao;

import java.util.List;

import com.plant.dto.Board;
import com.plant.dto.Criteria;
import com.plant.dto.MemInfo;
import com.plant.dto.Plantmember;

public interface MemInfoDao {

	public List<Board> myboard(Criteria cri, String id);
	public List<Board> myqnaboard(Criteria cri, String id);
	public List<Board> myadoptboard(Criteria cri, String id);
	public List<Board> myadoptreviewboard(Criteria cri, String id);
	public List<Board> myplantboard(Criteria cri, String id);
	public List<Board> myreply(Criteria cri, String id);
	public List<Board> myqnareplyboard(Criteria cri, String id);
	
	/*
	 * public List<MemInfo> myqnaboard(String id);
	 * 
	 * public List<MemInfo> myadoptboard(String id);
	 * 
	 * public List<MemInfo> myadoptreviewboard(String id);
	 * 
	 * public List<MemInfo> myplantboard(String id);
	 * 
	 * public List<MemInfo> myreply(String id);
	 * 
	 * public List<MemInfo> myqnareplyboard(String id);
	 */
	
	public Plantmember mypage(String id);
	
	//public MemInfo boardDetail(String seqno);
	

}
