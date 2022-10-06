package com.plant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.plant.dto.*;

public interface MplantReplyMapper {


	public List<MplantReply> getList( //매개변수가 1개 이상일때 param으로 지정 해줘야
								@Param("cri") Criteria cri, 
								@Param("bno") Long bno);

	public MplantReply getOne(Long rno);
	
	public int insert(MplantReply reply);

	public int update(MplantReply vo);

	public int delete(Long rno);
	
	public int getCountByBno(Long bno);

}
