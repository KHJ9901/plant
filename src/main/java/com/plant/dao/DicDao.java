package com.plant.dao;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.plant.dto.Criteria;
import com.plant.dto.DicImg;
import com.plant.dto.Dictionary;
import com.plant.dto.Mplant;
import com.plant.dto.MplantImg;

public interface DicDao {
	//---------------리스트 조회--------------------//
		public List<Dictionary> dictionList(Criteria cri);
		
		public List<Dictionary> dictionInList(Criteria cri);
		
		public List<Dictionary> dictionOutList(Criteria cri);
		
		public List<Mplant> mplantList(Criteria cri);

		
		
	//---------------상세페이지--------------------//
		public Dictionary dictionDetail(String seqno);
		
		public Mplant mplantDetail(String seqno);
		

		
	//---------------삽입--------------------//	
		public String insertMplant(Mplant mplant, MplantImg mpimg);
		
		public String insertDiction(Dictionary diction, DicImg dicimg);
		
		public void insertDicThumbNail(String dicimg_seqno, DicImg dicimg);
		
		public String insertDicImgFile(String seqno, DicImg dicimg);
		
		public void insertMpThumbNail(String mpi_seqno, MplantImg mpimg);
		
		public String insertMpImgFile(String seqno, MplantImg mpimg);

		
		
	//---------------수정--------------------//
		public String updateDiction(Dictionary diction, DicImg dicimg);
		
		public String updateMplant(Mplant mplant, MplantImg mpimg);


	//---------------삭제--------------------//
		Map<String, String> deleteDiction(String seqno);

		Map<String, String> deleteMplant(String seqno);

		
		
	//---------------총 개수--------------------//
		int getDictionaryOutRec(Criteria cri);

		int getMplantRec(Criteria cri);

		int getDictionaryInRec(Criteria cri);

		int getDictionaryRec(Criteria cri);
		
	}
