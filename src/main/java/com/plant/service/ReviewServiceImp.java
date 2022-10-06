package com.plant.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plant.common.LoginImpl;
import com.plant.dao.ReviewDao;
import com.plant.dto.Adopt;
import com.plant.dto.Criteria;
import com.plant.dto.Review;

@Service
public class ReviewServiceImp implements ReviewService {
	
	@Autowired
	ReviewDao reviewDao;
	
	private static final String CHARSET = "utf-8";
	
	@Override
	public List<Review> list(Criteria cri) {
		return reviewDao.reviewList(cri);
	}

	@Override
	public Review searchReview(String seqno) {
		return reviewDao.reviewDetail(seqno);
	}
	
	@Override
	public String insertReview(HttpServletRequest req, HttpServletResponse resp) {
		
		Review review = new Review();
		
		review.setId(req.getParameter("id"));
		review.setWdate(req.getParameter("wdate"));
		review.setSold(req.getParameter("sold"));
		review.setCount(req.getParameter("count"));
		review.setContent(req.getParameter("content"));
		review.setApply_seqno(req.getParameter("apply_seqno"));
		
		return reviewDao.insert(review);
	}

	public String update(HttpServletRequest req, HttpServletResponse resp) {
		
		Review review = new Review();
		
		review.setApply_seqno(req.getParameter("apply_seqno"));
		review.setContent(req.getParameter("content"));
		review.setSeqno(req.getParameter("seqno"));
		
		LoginImpl login = (LoginImpl)req.getSession().getAttribute("loginUser");
		review.setId(login.getId());
		
		reviewDao.update(review);
		return review.getSeqno();
	}
	

	Review getFormParameter(FileItem item, Review review) {
		System.out.printf("필드이름: %s, 필드값:%s\n", item.getFieldName(), item.getString());
		if(item.getFieldName().equals("id")) {
			review.setId(item.getString());
		}
		if(item.getFieldName().equals("apply_seqno")) {
			review.setApply_seqno(item.getString());
		}
		if(item.getFieldName().equals("content")) {
			review.setContent(item.getString());
		}
		return review;
	}

	
	public int getTotalRec(Criteria criteria) {
		return reviewDao.getTotalRec(criteria);
	}
	
	public void reviewdel(String seqno) {
		reviewDao.reviewdel(seqno);
	}

	@Override
	public String insertReview(Review review) {
		return reviewDao.insert(review);
	}

	@Override
	public String update(Review review) {
		reviewDao.update(review);
		return review.getSeqno();
	}

}
