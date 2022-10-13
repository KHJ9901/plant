package com.plant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.plant.common.OracleConn;


public class AdoptFileDao {
	private final Connection conn = OracleConn.getInstance().getConn();
	
	public int deleteByNo(String no) {
		PreparedStatement stmt = null;
		int rs = 0;
		
		//첨부파일 레코드삭제
		String sql = "DELETE FROM adoptfile_thumb WHERE attach_no = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, no);
			stmt.executeUpdate();
			
			sql = "DELETE FROM adoptfile WHERE no = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, no);
			rs = stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//썸네일 레코드 삭제
		return rs;
	}

}
