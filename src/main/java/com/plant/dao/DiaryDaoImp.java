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
import com.plant.dto.Criteria;
import com.plant.dto.Diary;

import oracle.jdbc.OracleType;
import oracle.jdbc.OracleTypes;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;


@Repository
public class DiaryDaoImp implements DiaryDao{
		
	@Autowired
	private DataSource ds;

	public List<Diary> diaryList(Criteria cri) {

		Connection conn = null;
		CallableStatement stmt = null;
		List<Diary> diary = new ArrayList<Diary>();
		
		String sql = "call p_getdiarylist(?,?,?) ";
			   
			   try {
				  conn = ds.getConnection();
				  stmt = conn.prepareCall(sql);
				  stmt.setInt(1, cri.getCurrentPage());
				  stmt.setInt(2, cri.getRowPerpage());
				  stmt.registerOutParameter(3, OracleTypes.CURSOR);
				  stmt.executeQuery();
				  
				  ResultSet rs = (ResultSet)stmt.getObject(3);
				  
				  while(rs.next()) {
					  
					Diary d = new Diary();
					
					d.setNo(rs.getString("rn"));
					d.setDiary_seqno(rs.getString("diary_seqno"));
					d.setId(rs.getString("id"));
					d.setName(rs.getString("name"));
					d.setWdate(rs.getString("wdate"));
					d.setContent(rs.getString("content"));
					diary.add(d);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
			
			}
			return diary;
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
		
		String sql = "call p_getdiarytotal(?, ?, ?)";
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
	
	public Diary diaryDetail(String diary_seqno) {
		
		Connection conn = null;
		CallableStatement stmt = null;
		Diary diary = new Diary();
		
		try {
			
			String sql = "call p_diary_detail(?,?)";
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			stmt.setInt(1, Integer.parseInt(diary_seqno));
			stmt.registerOutParameter(2, OracleTypes.CURSOR);
			stmt.executeUpdate();
			
			ResultSet rs = (ResultSet)stmt.getObject(2);
			
			rs.next();
			diary.setDiary_seqno(diary_seqno);
			diary.setId(rs.getString("id"));
			diary.setContent(rs.getString("content"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resourceClose(conn, stmt);	
		} return diary;
	}


	public String insert(Diary diary) {
			
		Connection conn = null;
		CallableStatement stmt = null;
		String diary_seqno = null;
		
		try {
			String sql = "call p_insert_diary(?,?)";
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			
			StructDescriptor st_diary = StructDescriptor.createDescriptor("OBJ_DIARY", conn);
			Object[] obj_diary = {diary.getId(), diary.getContent()};
			STRUCT diary_rec = new STRUCT(st_diary, conn, obj_diary);

			stmt.setObject(1, diary_rec);
					
			stmt.registerOutParameter(2, OracleType.VARCHAR2);
			stmt.executeQuery();
			
			diary_seqno = stmt.getString(2);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resourceClose(conn, stmt);	
		}

		return diary_seqno;
	}

	public void update(Diary diary) {
		String sql = "call p_update_diary(?,?)";
		Connection conn = null;
		CallableStatement stmt = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			//stmt.setString(1, diary.getId());
			stmt.setString(1, diary.getContent());
			stmt.setString(2, diary.getDiary_seqno());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resourceClose(conn, stmt);	
		}
	}

	public Map<String, String> diarydel(String diary_seqno) {
		
		Map<String, String> map = new HashMap<String, String>();
		Connection conn = null;
		CallableStatement stmt = null;
		
		try {
			String sql = "call p_delete_diary(?)";
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			stmt.setString(1, diary_seqno);
			stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resourceClose(conn, stmt);	
		} 
		
		return map;
	}
	
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
	public String insertDiary(Diary diary) {
		
		CallableStatement stmt = null;
		Connection conn = null;
		String diary_seqno = null;
		
		try {
			String sql = "call p_insert_diary(?,?)";
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			
			StructDescriptor st_diary = StructDescriptor.createDescriptor("OBJ_DIARY", conn);
			Object[] obj_diary = {diary.getId(), diary.getContent()};
			STRUCT diary_rec = new STRUCT(st_diary, conn, obj_diary);

			stmt.setObject(1, diary_rec);
					
			stmt.registerOutParameter(2, OracleType.VARCHAR2);
			stmt.executeQuery();
			
			diary_seqno = stmt.getString(2);
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resourceClose(conn, stmt);	
		}
		
		return diary_seqno;
	}

}
