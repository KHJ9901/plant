package com.plant.dao;

import java.util.List;
import com.plant.dto.MemInfo;
import com.plant.dto.Plantmember;

public interface MemInfoDao {

	public List<MemInfo> myboard(String id);
	
	public List<MemInfo> myqnaboard(String id);
	
	public List<MemInfo> myadoptboard(String id);
	
	public List<MemInfo> myadoptreviewboard(String id);
	
	public List<MemInfo> myplantboard(String id);
	
	public List<MemInfo> myreply(String id);
	
	public List<MemInfo> myqnareplyboard(String id);
	
	public Plantmember mypage(String id);
	
	public MemInfo boardDetail(String seqno);
	

}
