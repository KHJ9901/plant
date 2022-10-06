package com.plant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.plant.dto.AdoptReply;
import com.plant.dto.AdoptReplyVO;
import com.plant.dto.Criteria;

public interface AdoptReplyMapper {
	
	public int insert(AdoptReply adoptreply);

	public List<AdoptReplyVO> getList(
			@Param ("cri") Criteria cri, 
			@Param ("bno") Long bno);

	public AdoptReplyVO read(Long rno);

	public int update(AdoptReplyVO vo);

	public int delete(Long rno);
	
	public int getCoungByBno(Long bno);
	
}
