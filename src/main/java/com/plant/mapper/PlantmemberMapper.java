package com.plant.mapper;

import com.plant.dto.*;

public interface PlantmemberMapper {
	


	public Plantmember getById(String id); //MemberMapper.xml 에 있는 select id

	//멤버 매퍼
	public int insertmember(Plantmember member);

	public int get(String id);
	
	public int idDoubleCheck(String id);

	public void memdel(String id);
	
}
