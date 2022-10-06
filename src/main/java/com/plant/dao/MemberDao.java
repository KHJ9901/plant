package com.plant.dao;

import java.util.Map;

import com.plant.dto.Plantmember;

public interface MemberDao {

	
	public Map<String, String> loginProc(String id, String pw);
	
	public int insertMember(Plantmember member);
	
	public Map<String, String> pwfind(String id, String name, String email);
	
	public Plantmember mypageEdit(Plantmember member);
}
