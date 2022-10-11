package com.plant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.plant.dao.MemInfoDao;
import com.plant.dao.MemInfoDaoImp;

import com.plant.dto.Board;
import com.plant.dto.Criteria;
import com.plant.dto.MemInfo;
import com.plant.dto.Plantmember;

@Service
public class MemInfoServicelmp implements MemInfoService{
	
	@Autowired
	private MemInfoDao mdo;	
	
	@Override
	public List<MemInfo> myqnaboard(String id) {
		return mdo.myqnaboard(id);
	}

	@Override
	public List<MemInfo> myadoptboard(String id) {
		return mdo.myadoptboard(id);
	}
	
	@Override
	public List<MemInfo> myplantboard(String id) {
		return mdo.myplantboard(id);
	}
	@Override
	public List<MemInfo> myadoptreviewboard(String id) {
		return mdo.myadoptreviewboard(id);
	}

	@Override
	public List<MemInfo> myreply(String id) {
		return mdo.myreply(id);
	}

	public List<MemInfo> myqnareplyboard(String id) {
		return mdo.myqnareplyboard(id);
	}

	public Plantmember mypage(String id) {
		return mdo.mypage(id);
	}

	@Override
	public MemInfo searchBoard(String seqno) {
		return mdo.boardDetail(seqno);
	}
	@Override
	public List<Board> list(Criteria cri, String id) {
		return mdo.myboard(cri, id);
	}
	@Override
	public int getTotalRec(Criteria cri) {
		// TODO Auto-generated method stub
		return 0;
	}


}
