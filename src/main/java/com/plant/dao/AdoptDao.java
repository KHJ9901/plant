package com.plant.dao;

import java.util.List;
import java.util.Map;

import com.plant.dto.Adopt;
import com.plant.dto.AdoptFile;
import com.plant.dto.Criteria;

public interface AdoptDao {
	
	public List<Adopt> adoptList(Criteria cri);
	
	public Adopt adetail(String seqno);
		
	public String insert(Adopt adopt);
	
	public String insertAdopt(Adopt adopt);
	
	void insertAdoptThumb (String attach_no, AdoptFile adoptfile);
	
	String insertAdoptFile(String seqno, AdoptFile adoptfile);
	
	public void update(Adopt adopt);
			
	public int getTotalRec(Criteria cri);
	
	public Map<String, String> adoptdel(String seqno);
	
		
}
