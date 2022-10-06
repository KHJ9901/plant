package com.plant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.plant.common.OracleConn;

public class MpImgDao {
	
	private final Connection conn = OracleConn.getInstance().getConn();

	public int mpImgDel(String no) {
		int rs = 0;
		//첨부파일 레코드 삭제
		String sql = "DELETE FROM MPLANT_IMG_THUMB WHERE mpi_seqno = ?";
		PreparedStatement stmt;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setNString(1, no);
			stmt.executeUpdate();
			
			sql = "DELETE FROM MPLANT_IMG WHERE MPI_SEQNO = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,  no);
			rs = stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//썸네일 레코드 삭제
		
		return rs;
	}
}
