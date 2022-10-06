package com.plant.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plant.common.OracleConn;

@Service
public class DicImgDao {
	
	@Autowired
	private DataSource ds;

	public int dicImgDel(String no) {
		Connection conn = null;
		CallableStatement stmt = null;
		int rs = 0;
		//첨부파일 레코드 삭제
		String sql = "DELETE FROM DICTIONARY_IMG_THUMB WHERE dicimg_seqno = ?";
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			
			stmt.setNString(1, no);
			stmt.executeUpdate();
			
			sql = "DELETE FROM dictionary_IMG WHERE seqno = ?";
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			stmt.setString(1, no);
			rs = stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//썸네일 레코드 삭제
		
		return rs;
	}
	public int mpImgDel(String no) {
		Connection conn = null;
		CallableStatement stmt = null;
		int rs = 0;
		//첨부파일 레코드 삭제
		String sql = "DELETE FROM MPLANT_IMG_THUMB WHERE mpi_seqno = ?";
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			stmt.setNString(1, no);
			stmt.executeUpdate();
			
			sql = "DELETE FROM MPLANT_IMG WHERE MPI_SEQNO = ?";
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			stmt.setString(1, no);
			rs = stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//썸네일 레코드 삭제
		
		return rs;
	}
}
