package com.plant.service;

import java.util.Map;

import com.plant.dto.Plantmember;


public interface MemberService {

	Map<String, String> login(String id, String pw);
	
	public int insertMember(Plantmember member);
	
	public Map<String, String> pwfind(String id, String name, String email);
	
	public Plantmember mypageEdit(Plantmember member);

	public Plantmember editview(String id);

	public void memdel(String id);

	int insertmember(Plantmember member);




}
