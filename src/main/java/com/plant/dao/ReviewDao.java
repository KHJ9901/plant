package com.plant.dao;

import java.util.List;
import java.util.Map;

import com.plant.dto.Criteria;
import com.plant.dto.Review;

public interface ReviewDao {
	
	public List<Review> reviewList(Criteria cri);

	public Review reviewDetail(String seqno);

	public String insert(Review review);
	
	public String insertReview(Review review);
	
	
	public void update(Review review);
	
	public int getTotalRec (Criteria cri);

	public Map<String, String> reviewdel(String seqno);
}
