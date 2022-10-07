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

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plant.common.OracleConn;
import com.plant.dto.Board;
import com.plant.dto.BoardReply;
import com.plant.dto.Criteria;

import oracle.jdbc.OracleType;
import oracle.jdbc.OracleTypes;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

@Repository
public class BoardDaoImp implements BoardDao{
	
	@Autowired
	private DataSource ds;
	
	public List<Board> boardList(Criteria cri) {
		
		Connection conn = null;
		CallableStatement stmt = null;
		List<Board> board = new ArrayList<Board>();
		
		String search_title = null;
		String search_name = null;
		
		//제목검색
		if(cri.getSearchField() != null && cri.getSearchField().equals("title")) {
			search_title = cri.getSearchText();
		}
		//이름검색
		if(cri.getSearchField() != null && cri.getSearchField().equals("name")) {
			search_name = cri.getSearchText();
		}
		
		String sql = "call p_getboardlist(?,?,?,?,?)";
		
	  	try {
		  conn = ds.getConnection();
		  stmt = conn.prepareCall(sql);
		  stmt.setInt(1, cri.getCurrentPage());
		  stmt.setInt(2, cri.getRowPerpage());
		  stmt.setString(3, search_name);
		  stmt.setString(4, search_title);
		  stmt.registerOutParameter(5, OracleTypes.CURSOR);
		  stmt.executeQuery();
			
		  ResultSet rs =(ResultSet)stmt.getObject(5);
		  while(rs.next()) {
			  Board b = new Board();
			  b.setRn(rs.getNString("rn"));
			  b.setSeqno(rs.getString("seqno"));
			  b.setTitle(rs.getString("title"));
			  b.setWdate(rs.getString("wdate"));
			  b.setName(rs.getString("name"));
			  b.setCount(rs.getString("count"));
			  b.setId(rs.getString("id"));
			  board.add(b);
		  }
		} catch (SQLException e) {	
			e.printStackTrace();
		} finally {
			resourceClose(conn, stmt);
		}
	  	return board;
	}
	
	public int getTotalRec(Criteria cri) {
		int total = 0;
		String search_title = null;
		String search_name = null;
		
		//게시물 제목 검색
		if(cri.getSearchField() != null && cri.getSearchField().equals("title")) {
			search_title = cri.getSearchText();
		}
		//이름검색
		if(cri.getSearchField() != null && cri.getSearchField().equals("name")) {
			search_name = cri.getSearchText();
		}
		
		String sql="call p_getboardtotal(?,?,?)";
		Connection conn = null;
		CallableStatement stmt = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			stmt.setString(1, search_name);
			stmt.setString(2, search_title);
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
	
	public Board boardDetail(String seqno) {
		
		Connection conn = null;
		CallableStatement stmt = null;
		Board board = new Board();
		
		try {
			String sql = "call p_board_detail(?,?,?)";
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			stmt.setInt(1, Integer.parseInt(seqno));
			stmt.registerOutParameter(2, OracleTypes.CURSOR);
			stmt.registerOutParameter(3, OracleTypes.CURSOR);
			stmt.executeQuery();
			
			ResultSet rs = (ResultSet)stmt.getObject(2);
			rs.next();
			
			board.setSeqno(seqno);
			board.setTitle(rs.getString("title"));
			board.setContent(rs.getString("content"));
			board.setId(rs.getString("id"));
			board.setWdate(rs.getString("wdate"));
			board.setCount(rs.getString("count"));
			board.setName(rs.getString("name"));
			
			//댓글 목록
			List<BoardReply> reply_arr = new ArrayList<BoardReply>();
			rs = (ResultSet)stmt.getObject(3);
			
			while(rs.next()) {
				BoardReply r = new BoardReply();
				r.setContent(rs.getString("content"));
				r.setId(rs.getString("id"));
				r.setWdate(rs.getString("wdate"));
				reply_arr.add(r);
			}
			board.setBoardreply(reply_arr);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resourceClose(conn, stmt);
        }
		return board;
	}
	
	public String boardnew(Board board) {
		Connection conn = null;
		CallableStatement stmt = null;
		String seqno = null;

		try {
			String sql = "call p_insert_board(?,?)";
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			StructDescriptor st_board = StructDescriptor.createDescriptor("OBJ_BOARD",conn);
	        Object[] obj_board = {board.getTitle(), board.getContent(),  board.getId()};
	        STRUCT board_rec = new STRUCT(st_board, conn, obj_board);

	        stmt.setObject(1,board_rec);
	        stmt.registerOutParameter(2, OracleType.VARCHAR2);
			
			stmt.executeQuery();
			seqno = stmt.getString(2);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
	         resourceClose(conn, stmt);
	      }
		return seqno;
		
	}
	
	public void reply(HttpServletRequest req){
		
		String content = req.getParameter("asd_content");
		String seqno = req.getParameter("asd_board_seqno");
		String id = req.getParameter("asd_replyid");
		String sql =" insert into board_reply(br_seqno, content, seqno, id) values (board_reply_seqno.nextval,?,?,?)";	
		Connection conn = null;
	    PreparedStatement stmt = null;
	      
		try {
		   conn = ds.getConnection(); 
	       stmt = conn.prepareStatement(sql);
		   stmt.setString(1, content);
		   stmt.setString(2, seqno);
		   stmt.setString(3, id);
		   ResultSet rs = stmt.executeQuery();		   
		} catch (SQLException e) {
	         e.printStackTrace();
	    } finally {
			resourceClose(conn, stmt);
	    }	      
	}
	
	public Map<String, String> boarddel(String seqno) {
		
		Map<String, String> map = new HashMap<String, String>();
		  Connection conn = null;
		  CallableStatement stmt = null;
		  
		try {
			String sql = "call p_delete_board(?,?)";
			 conn = ds.getConnection();
	    	 stmt = conn.prepareCall(sql);
	    	 stmt.setString(1, seqno);
	    	 stmt.registerOutParameter(2, OracleTypes.VARCHAR);
	         stmt.executeQuery();
	    	 
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
			     resourceClose(conn, stmt);
		  }
		return map;
		
	}
	
	public void update(Board board) {
		String sql = " call p_updateboard(?,?,?)";
		Connection conn = null;
		CallableStatement stmt = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			stmt.setString(1, board.getTitle());
			stmt.setString(2, board.getContent());
			stmt.setString(3, board.getSeqno());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resourceClose(conn, stmt);
		}
		
	}
	
	private void resourceClose(Connection conn, PreparedStatement stmt) {
		try {
			if(stmt !=null || conn != null) {
			stmt.close();
			conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void resourceClose(Connection conn, CallableStatement stmt) {
		try {
			if(stmt !=null || conn != null) {
			stmt.close();
			conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
}