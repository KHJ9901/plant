package com.plant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.plant.dto.Criteria;
import com.plant.dto.QnaReplyVo;


public interface QnaReplyMapper {
	//매개변수로 지정을 해줘야 전달이됨.
	public int insert(QnaReplyVo reply);

	public List<QnaReplyVo> getList(
			@Param("cri") Criteria cri, 
			@Param("bno") Long bno);

	public QnaReplyVo read(Long rno);

	public int update(QnaReplyVo vo);

	public int delete(Long rno);
	
	public int getCountByBno(Long bno);
}
