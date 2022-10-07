package com.plant.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plant.common.OracleConn;
import com.plant.dto.Adopt;
import com.plant.dto.Criteria;
import com.plant.dto.Review;

import oracle.jdbc.OracleType;
import oracle.jdbc.OracleTypes;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

@Repository
public class ReviewDaoImp implements ReviewDao {
	
	@Autowired
	private DataSource ds;
	
	public List<Review> reviewList(Criteria cri) {
		
		Connection conn = null;
		CallableStatement stmt = null;
		List<Review> review = new ArrayList<Review>();
		
		String search_content = null;
		String search_id = null;
		
		if(cri.getSearchField() != null && cri.getSearchField().equals("content")) {
			search_content = cri.getSearchText();
		}
		if(cri.getSearchField() != null && cri.getSearchField().equals("id")) {
			search_id = cri.getSearchText();
		}
		
		//후기리스트
		String sql = "call p_getreviewlist(?,?,?,?,?)";
			  
			   
			try {
				conn = ds.getConnection();
				stmt = conn.prepareCall(sql);
				stmt.setInt(1, cri.getCurrentPage());
				stmt.setInt(2, cri.getRowPerpage());
				stmt.setString(3, search_id);
				stmt.setString(4, search_content);
				stmt.registerOutParameter(5, OracleTypes.CURSOR);
				stmt.executeQuery();
				
				ResultSet rs = (ResultSet)stmt.getObject(5);
				
				while(rs.next()) {
					
					Review r = new Review();
					
					r.setNo(rs.getString("rn"));
					r.setSeqno(rs.getString("seqno"));
					r.setContent(rs.getString("content"));
					r.setWdate(rs.getString("wdate"));
					r.setId(rs.getString("id"));
					r.setCount(rs.getString("count"));
					review.add(r);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				//	resourceClose(conn, stmt);	
			} return review;
	}
	
	
	public int getTotalRec(Criteria cri) {
		
		Connection conn = null;
		CallableStatement stmt = null;
		
		int total = 0;
		String search_content = null;
		String search_id = null;
		
		if(cri.getSearchField() != null && cri.getSearchField().equals("content")) {
			search_content = cri.getSearchText();
		}

		if(cri.getSearchField() != null && cri.getSearchField().equals("id")) {
			search_id = cri.getSearchText();
		}
		
		String sql="call p_getreviewtotal(?,?,?)";
		
		try {
			conn = ds.getConnection();
			stmt= conn.prepareCall(sql);
			stmt.setString(1, search_content);
			stmt.setString(2, search_id);
			stmt.registerOutParameter(3, OracleTypes.INTEGER);
			stmt.executeQuery();
			
			total = stmt.getInt(3);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resourceClose(conn, stmt);	
		}
		
		return total;
	}

	
	public Review reviewDetail(String seqno) {
		
		Connection conn = null;
		CallableStatement stmt = null;
		Review review = new Review();
		
		try {
			String sql = "call p_review_detail(?,?)";
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			stmt.setInt(1, Integer.parseInt(seqno));
			stmt.registerOutParameter(2, OracleTypes.CURSOR);
			stmt.executeUpdate();
			
			ResultSet rs = (ResultSet)stmt.getObject(2);
			
			//게시글
			rs.next();
			review.setSeqno(seqno);
			review.setId(rs.getString("id"));
			review.setApply_seqno(rs.getString("apply_seqno"));
			review.setWdate(rs.getString("wdate"));
			review.setCount(rs.getString("count"));
			review.setSold(rs.getString("sold"));
			review.setContent(rs.getString("content"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resourceClose(conn, stmt);	
		}
		
		return review;
	}

	public String insert(Review review) {
		
		Connection conn = null;
		CallableStatement stmt = null;
		String seqno = null;
	
		try {
			String sql = "call p_insert_review(?,?)";
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			
			StructDescriptor st_review = StructDescriptor.createDescriptor("OBJ_REVIEW", conn);
			Object[] obj_review = {review.getId(), review.getContent()};
			STRUCT review_rec = new STRUCT(st_review, conn, obj_review);

			stmt.setObject(1, review_rec);
			
			stmt.registerOutParameter(2, OracleType.VARCHAR2);
			stmt.executeQuery();
			
			seqno = stmt.getString(2);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resourceClose(conn, stmt);	
		}
		
		return seqno;
		
		}

	public void update(Review review) {
		String sql = "call p_update_review(?,?)";
		Connection conn = null;
		CallableStatement stmt = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			/* stmt.setString(1, review.getApply_seqno()); */
			stmt.setString(1, review.getContent());
			stmt.setString(2, review.getSeqno());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resourceClose(conn, stmt);	
		}
	}

	public Map<String, String> reviewdel(String seqno) {
		
		Map<String, String> map = new HashMap<String, String>();
		Connection conn = null;
		CallableStatement stmt = null;
		
		try {
			String sql = "call p_delete_review(?)";
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			stmt.setString(1, seqno);
			stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resourceClose(conn, stmt);	
		} 
		
		return map;
	}
	
	
	//자원반납
	private void resourceClose(Connection conn, PreparedStatement stmt) {
		try {
			
			if(stmt != null || conn !=null) {
				stmt.close();
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	//자원반납
	private void resourceClose(Connection conn, CallableStatement stmt) {
		try {
			
			if(stmt != null || conn != null) {
				stmt.close();
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	@Override
	public String insertReview(Review review) {
		
		CallableStatement stmt = null;
		Connection conn = null;
		String seqno = null;
		
		try {
			
			String sql = "call p_insert_review(?,?)";
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
						
			StructDescriptor st_review = StructDescriptor.createDescriptor("OBJ_REVIEW", conn);
			Object[] obj_review = {review.getId(), review.getApply_seqno(), review.getContent()};
			STRUCT review_rec = new STRUCT(st_review, conn, obj_review);

			stmt.setObject(1, review_rec);
			stmt.registerOutParameter(2, OracleType.VARCHAR2);
			stmt.executeQuery();
			
			seqno = stmt.getString(2);
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resourceClose(conn, stmt);	
		}
		
		return seqno;
	}
	
	
}
