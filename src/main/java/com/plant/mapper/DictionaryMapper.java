package com.plant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.plant.dto.*;

public interface DictionaryMapper {
	
//--------------------------사전 관련-----------------------------------
	//리스트
	public DictionaryVO getKname(String seqno); //MemberMapper.xml 에 있는 select id
	
	public List<DictionaryVO> dictionList(@Param("cri") Criteria cri);
	
	public List<DictionaryVO> dictionInList(@Param("cri") Criteria cri);
	
	public List<DictionaryVO> dictionOutList(@Param("cri") Criteria cri);
	
	//상세
	public DictionaryVO dictionDetail(String seqno);
	
	//삽입
	public int dictionInsert(@Param("diction")Dictionary diction,
							 @Param("filename")DicImg dicImgUpload);
	
	//수정
	public int dictionUpdate(Dictionary diction, DicImg dicImgUpload);
	
	//삭제
	public int dictionDelete(String seqno);
	
	
//--------------------------회원 식물 관련-----------------------------------
	//리스트
	public List<MplantVO> mplantList(@Param("cri") Criteria cri);
	
	//상세
	public MplantVO mplantDetail(String seqno);

	//삽입
	public int mplantInsert(MplantVO mplant);
	
	//수정
	public int mplantUpdate(MplantVO mplant, MplantImg mpImgUpload);
	
	//삭제
	public int mplantDelete(String seqno);
	

//---------------------------검색 결과 관련RestController--------------------------------------
	//전체
	public List<DictionaryVO> dicListSearch(@Param("cri") Criteria cri);
	
	public List<DictionaryVO> dicInListSearch(@Param("cri") Criteria cri);
	
	public List<DictionaryVO> dicOutListSearch(@Param("cri") Criteria cri);
	
	public List<MplantVO> MpListSearch(@Param("cri") Criteria cri);
	
}
