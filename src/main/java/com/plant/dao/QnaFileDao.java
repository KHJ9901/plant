package com.plant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.plant.common.OracleConn;

@Repository
public class QnaFileDao {
	private final Connection conn = OracleConn.getInstance().getConn();
	
	public int deleteByNO(String no) {
		int rs = 0;
		
		//첨부파일 레코드 삭제
		String sql = " DELETE FROM qna_img_shumb WHERE qi_seqno =? ";
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setNString(1, no);
			stmt.executeUpdate();
			
			sql = " DELETE FROM qna_img WHERE qi_seqno = ? ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, no);
			rs = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}
}
