package com.plant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.plant.dto.*;

public interface DictionaryMapper {
	
//--------------------------사전 관련-----------------------------------
	//리스트
	public Dictionary getKname(String seqno); //MemberMapper.xml 에 있는 select id
	
	public List<Dictionary> dictionList(@Param("cri") Criteria cri);
	
	public List<Dictionary> dictionInList(@Param("cri") Criteria cri);
	
	public List<Dictionary> dictionOutList(@Param("cri") Criteria cri);
	
	//상세
	public Dictionary dictionDetail(String seqno);
	
	//삽입
	public int dictionInsert(Dictionary diction, DicImg dicImgUpload);
	
	//수정
	public int dictionUpdate(Dictionary diction, DicImg dicImgUpload);
	
	//삭제
	public int dictionDelete(String seqno);
	
	
//--------------------------회원 식물 관련-----------------------------------
	//리스트
	public List<Mplant> mplantList(@Param("cri") Criteria cri);
	
	//상세
	public Mplant mplantDetail(String seqno);

	//삽입
	public int mplantInsert(Mplant mplant, MplantImg mpImgUpload);
	
	//수정
	public int mplantUpdate(Mplant mplant, MplantImg mpImgUpload);
	
	//삭제
	public int mplantDelete(String seqno);
	

//---------------------------검색 결과 관련RestController--------------------------------------
	//전체
	public List<Dictionary> dicListSearch(@Param("cri") Criteria cri);
	
	public List<Dictionary> dicInListSearch(@Param("cri") Criteria cri);
	
	public List<Dictionary> dicOutListSearch(@Param("cri") Criteria cri);
	
	public List<Mplant> MpListSearch(@Param("cri") Criteria cri);
	
}
