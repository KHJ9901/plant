package com.plant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.plant.dao.MemInfoDao;

import com.plant.dto.Board;
import com.plant.dto.Criteria;
import com.plant.dto.MemInfo;
import com.plant.dto.Plantmember;

@Service
public class MemInfoServicelmp implements MemInfoService{
	
	@Autowired
	private MemInfoDao mdo;	
	
	//고영재 마이페이지 뷰단 2022-09-20 	
	public Plantmember mypage(String id) {
		return mdo.mypage(id);
	}

	//고영재 화면분활 개발중  
	@Override
	public int getTotalRec(Criteria cri) {
		return 0;
	}
	
	//고영재 내가작성한글 뷰단 2022-09-22
	@Override
	public List<Board> list(Criteria cri, String id) {
		return mdo.myboard(cri, id);
	}

	//고영재 마이페이지 뷰단 2022-09-20
	@Override
	public List<Board> list2(Criteria cri, String id) {
		// TODO Auto-generated method stub
		return mdo.myqnaboard(cri,id);
	}

	@Override
	public List<Board> list3(Criteria cri, String id) {
		// TODO Auto-generated method stub
		return mdo.myadoptboard(cri, id);
	}

	@Override
	public List<Board> list4(Criteria cri, String id) {
		// TODO Auto-generated method stub
		return mdo.myplantboard(cri, id);
	}

	@Override
	public List<Board> list5(Criteria cri, String id) {
		// TODO Auto-generated method stub
		return mdo.myadoptreviewboard(cri, id);
	}
	@Override
	public List<Board> list6(Criteria cri, String id) {
		// TODO Auto-generated method stub
		return mdo.myreply(cri, id);
	}

	@Override
	public List<Board> list7(Criteria cri, String id) {
		// TODO Auto-generated method stub
		return mdo.myqnareplyboard(cri, id);
	}

	@Override
	public MemInfo searchBoard(String seqno) {
		// TODO Auto-generated method stub
		return null;
	}




}
