package com.plant.service;

import java.util.List;


import org.apache.commons.fileupload.FileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.plant.dao.DicDao;
import com.plant.dto.*;
import com.plant.mapper.*;

@Service
public class DicServicelmp implements DicService{
	
	private static final Logger log = LoggerFactory.getLogger(DicServicelmp.class);
	
	@Autowired
	private DictionaryMapper dicmapper; 
	@Autowired
	private DicImgFileService mpfs;
	@Autowired
	private DicDao dicDao;
	
	
	//---------------리스트 조회--------------------//
	@Override
	public List<Dictionary> list(Criteria cri) {
		return dicmapper.dictionList(cri);
	}
	
	@Override
	public List<Dictionary> inList(Criteria cri) {
		return dicmapper.dictionInList(cri);
	}

	@Override
	public List<Dictionary> outList(Criteria cri) {
		return dicmapper.dictionOutList(cri);
	}

	@Override
	public List<Mplant> mpList(Criteria cri) {
		return dicmapper.mplantList(cri);
	}
	
	
	
	
	//---------------상세페이지--------------------//
	@Override
	public Dictionary DicDetail(String seqno) {
		return dicmapper.dictionDetail(seqno);
	}
	
	@Override
	public Mplant MpDetail(String seqno) {
		return dicmapper.mplantDetail(seqno);
	}
	
	
	
	
	//---------------삽입--------------------//	
	@Override
	public int insertDic(Dictionary diction, MultipartFile files) {
		
		return dicmapper.dictionInsert(diction, mpfs.dicImgUpload(files));
	}
	
	@Override
	public int insertMp(Mplant mplant, MultipartFile files) {
		
		return dicmapper.mplantInsert(mplant, mpfs.mpImgUpload(files));
	}
	
	
	
	//---------------수정--------------------//
	@Override
	public int updateDic(Dictionary diction, MultipartFile files) {
		
		return dicmapper.dictionUpdate(diction, mpfs.dicImgUpload(files));
		
	}
	
	@Override
	public int updateMp(Mplant mplant, MultipartFile files) {
		
		return dicmapper.mplantUpdate(mplant, mpfs.mpImgUpload(files));
	}
		
	
	//---------------삭제--------------------//
	@Override
	public int deleteDic(String seqno) {
		
		return dicmapper.dictionDelete(seqno);
	}
	
	@Override
	public int deleteMp(String seqno) {

		return dicmapper.mplantDelete(seqno);
	}

	
	//=================검색======================================
	@Override
	public List<Dictionary> dicSearch(Criteria cri) {
		return dicmapper.dicListSearch(cri);
	}
	@Override
	public List<Dictionary> dicInSearch(Criteria cri) {
		return dicmapper.dicInListSearch(cri);
	}
	@Override
	public List<Dictionary> dicOutSearch(Criteria cri) {
		return dicmapper.dicOutListSearch(cri);
	}
	@Override
	public List<Mplant> MplantSearch(Criteria cri) {
		return dicmapper.MpListSearch(cri);
	}
	//---------------파라미터--------------------//
	private Mplant getFormParameter(FileItem item, Mplant mplant) {
		System.out.printf("필드이름 : %s, 필드값: %s\n", item.getFieldName() /*input 타입의 필드 이름불러옴*/, item.getString());
		
		if(item.getFieldName().equals("reg_pname")) {
			mplant.setName(item.getString());
		}
		if(item.getFieldName().equals("reg_pcate")) {
			mplant.setCate(item.getString());
		}
		if(item.getFieldName().equals("reg_plevel")) {
			mplant.setPlevel(item.getString());
		}
		if(item.getFieldName().equals("seqno")) {
			mplant.setMplant_seqno(item.getString());
		}
		if(item.getFieldName().equals("reg_pwater")) {
			mplant.setWater(item.getString());
		}
		if(item.getFieldName().equals("reg_pplace")) {
			mplant.setPlace(item.getString());
		}
		if(item.getFieldName().equals("reg_ptemp")) {
			mplant.setTemp(item.getString());
		}
		if(item.getFieldName().equals("reg_pmoist")) {
			mplant.setMoist(item.getString());
		}
		if(item.getFieldName().equals("reg_petc")) {
			mplant.setEtc(item.getString());
		}
		
		return mplant;
	}
	
	private Dictionary getFormParameter(FileItem item, Dictionary diction) {
		System.out.printf("필드이름 : %s, 필드값: %s\n", item.getFieldName() /*input 타입의 필드 이름불러옴*/, item.getString());
		
		if(item.getFieldName().equals("reg_pkname")) {
			diction.setKname(item.getString());
		}
		if(item.getFieldName().equals("reg_pename")) {
			diction.setEname(item.getString());
		}
		if(item.getFieldName().equals("reg_pcate")) {
			diction.setCate(item.getString());
		}
		if(item.getFieldName().equals("seqno")) {
			diction.setSeqno(item.getString());
		}
		if(item.getFieldName().equals("reg_pwater")) {
			diction.setWater(item.getString());
		}
		if(item.getFieldName().equals("reg_pplace")) {
			diction.setPlace(item.getString());
		}
		if(item.getFieldName().equals("reg_ptemp")) {
			diction.setTemp(item.getString());
		}
		if(item.getFieldName().equals("reg_pmoist")) {
			diction.setMoist(item.getString());
		}
		if(item.getFieldName().equals("reg_petc")) {
			diction.setEtc(item.getString());
		}
		
		return diction;
	}

	@Override
	public int getDicRec(Criteria cri) {
		return dicDao.getDictionaryRec(cri);
	}

	@Override
	public int getDicInRec(Criteria cri) {
		return dicDao.getDictionaryInRec(cri);
	}

	@Override
	public int getDicOutRec(Criteria cri) {
		return dicDao.getDictionaryOutRec(cri);
	}

	@Override
	public int getMplantRec(Criteria cri) {
		return dicDao.getMplantRec(cri);
	}



}