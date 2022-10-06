//package com.plant.dao;
//
//import java.sql.CallableStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import com.plant.common.OracleConn;
//import com.plant.dto.Board;
//import com.plant.dto.BoardReply;
//
//@Repository
//public class BoardDaoImp implements BoardDao{
//	
//	@Autowired
//	private DataSource ds;
//	
//	public List<Board> boardList() {
//		
//		Connection conn = null;
//		CallableStatement stmt = null;
//		List<Board> board = new ArrayList<Board>();
//		
//		String sql = " select seqno, rownum, title, wdate, count, name, id";
//			  sql += " FROM ( SELECT seqno, title,";
//			  sql += " TO_CHAR(b.wdate, 'YYYY\"년\"MM\"월\"DD\"일\"', 'nls_date_language = american') wdate,";  
//			  sql += " count, m.name, m.id";
//			  sql += " FROM board b, members m";
//			  sql += " WHERE b.id = m.id";
//			  sql += " ORDER BY wdate desc)";
//			  sql += " WHERE rownum BETWEEN 1 and 10000";
//		  	  try {
//		  		  conn = ds.getConnection();
//		  		  stmt = conn.prepareCall(sql);
//				  ResultSet rs = stmt.executeQuery();
//				
//				  while(rs.next()) {
//				  	  Board b = new Board();
//					  b.setNo(rs.getNString("rownum"));
//					  b.setSeqno(rs.getString("seqno"));
//					  b.setTitle(rs.getString("title"));
//					  b.setWdate(rs.getString("wdate"));
//					  b.setName(rs.getString("name"));
//					  b.setCount(rs.getString("count"));
//					  b.setId(rs.getString("id"));
//					  board.add(b);
//				  }
//				} catch (SQLException e) {	
//					e.printStackTrace();
//				}
//			  	return board;
//	}
//	
//	public Board boardDetail(String seqno) {
//		
//		Connection conn = null;
//		CallableStatement stmt = null;
//		Board board = new Board();
//		
//		try {
//			conn = ds.getConnection();
//			stmt = conn.prepareCall(sql);
//			
//			//조회수
//			String sql = "update board set count = count+1 where seqno ="  + seqno;
//			stmt.executeUpdate();
//			
//			//게시물 세부내용
//			sql = " select seqno, title, content, id,";
//			sql +="	To_char(b.wdate, 'YYYY-MM-DD HH:MI:SS') wdate,";
//			sql +=" count, (select name from members m where m.id = b.id) name,"; 
//			sql +=" (select count(*) from board_reply where seqno = ?) as reply";
//			sql +="	from board b";
//			sql +="	where b.seqno = ?";
//			sql +="	union all";
//		    sql +="	select br_seqno, '', content, id,";
//			sql +=" TO_CHAR(r.wdate, 'YYYY-MM-DD HH:MI:SS') wdate,";
//			sql +=" 0, (select name from members m where m.id = r.id) name, 0 ";
//			sql +="	from board_reply r";
//			sql +="	where r.seqno = ?";
//
//			
//			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
//					ResultSet.CONCUR_UPDATABLE);
//			stmt.setString(1, seqno);
//			stmt.setString(2, seqno);
//			stmt.setString(3, seqno);
//			ResultSet rs = stmt.executeQuery();
//			
//			rs.next();
//			
//			board.setSeqno(seqno);
//			board.setTitle(rs.getString("title"));
//			board.setContent(rs.getString("content"));
//			board.setId(rs.getString("id"));
//			board.setWdate(rs.getString("wdate"));
//			board.setCount(rs.getString("count"));
//			board.setName(rs.getString("name"));
//			board.setReply(rs.getString("reply"));
//			
//			List<BoardReply> reply_arr = new ArrayList<BoardReply>();
//			
//			while(rs.next()) {
//				BoardReply r = new BoardReply();
//				r.setContent(rs.getString("content"));
//				r.setId(rs.getString("id"));
//				r.setWdate(rs.getString("wdate"));
//				reply_arr.add(r);
//			}
//			board.setBoardreply(reply_arr);
//			
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return board;
//	}
//	
//	public String boardnew(Board board) {
//		String sql = " insert into board(seqno,title,content,id)values(board_seqno.nextval,?,?,?)";
//		PreparedStatement stmt;
//		String seqno = null;
//
//		try {
//			stmt = conn.prepareStatement(sql);
//			stmt.setString(1, board.getTitle());
//			stmt.setString(2, board.getContent());
//			stmt.setString(3, board.getId());
//			stmt.executeQuery();
//			
//			
//			sql = "SELECT max(seqno) as seqno FROM board";
//			stmt = conn.prepareStatement(sql);
//			ResultSet rs = stmt.executeQuery();
//			rs.next();
//			seqno = rs.getString("seqno");
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return seqno;
//		
//	}
//	
//	public void reply(HttpServletRequest req){
//		
//		String content = req.getParameter("asd_content");
//		String seqno = req.getParameter("asd_board_seqno");
//		String id = req.getParameter("asd_replyid");
//		String sql =" insert into board_reply(br_seqno, content, seqno, id) values (board_reply_seqno.nextval,?,?,?)";	
//		try {
//		   PreparedStatement stmt = conn.prepareStatement(sql);
//		   stmt.setString(1, content);
//		   stmt.setString(2, seqno);
//		   stmt.setString(3, id);
//		   ResultSet rs = stmt.executeQuery();		   
//		} catch (SQLException e) {
//	         e.printStackTrace();
//	    }	      
//	}
//	
//	public void boarddel(String seqno) {
//		try {
//			String sql = "delete from board_reply where seqno = ?";
//	    	 PreparedStatement stmt = conn.prepareStatement(sql);
//	    	 stmt.setString(1, seqno);     
//	         stmt.executeQuery();
//	    	 
//	    	 sql = "delete from board where seqno = ?";
//	         stmt = conn.prepareStatement(sql);
//	         stmt.setString(1, seqno);     
//	         stmt.executeQuery();     
//	      } catch (SQLException e) {
//	         e.printStackTrace();
//	      }
//		
//	}
//	
//	public void update(Board board) {
//		String sql = " UPDATE board set title = ?, id = ?, content = ? WHERE seqno =?";
//		PreparedStatement stmt;
//		try {
//			stmt = conn.prepareStatement(sql);
//			stmt.setString(1, board.getTitle());
//			stmt.setString(2, board.getId());
//			stmt.setString(3, board.getContent());
//			stmt.setString(4, board.getSeqno());
//			stmt.executeQuery();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//	}
//	
//}