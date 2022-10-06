package com.plant.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plant.dto.Criteria;
import com.plant.dto.Review;

public interface ReviewService {

	public List<Review> list(Criteria cri);

	public int getTotalRec(Criteria criteria);
	
	public Review searchReview(String seqno);
	
	public String insertReview(HttpServletRequest req, HttpServletResponse resp);
	
	public String insertReview(Review review);
	
	public String update(HttpServletRequest req, HttpServletResponse resp);

	public String update(Review review);
	
	public void reviewdel(String seqno);

}
