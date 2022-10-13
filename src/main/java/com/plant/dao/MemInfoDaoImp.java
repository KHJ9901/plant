package com.plant.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.plant.dto.*;

import oracle.jdbc.OracleTypes;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;




@Repository
public class MemInfoDaoImp implements MemInfoDao{
	@Autowired
	private DataSource ds;

	public List<Board> myboard(Criteria cri, String id) {
		CallableStatement stmt = null;
		Connection conn = null;
		List<Board> Board = new ArrayList<Board>();
		System.out.println("아이디 : " + id);

		String sql = "call p_getmyboardlist(?,?,?,?,?)";

		if(cri.getSearchText() ==  null) {
			cri.setSearchText("");
		}
		cri.setCurrentPage(1);
		cri.setRowPerpage(10);
		try {
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			stmt.setInt(1, cri.getCurrentPage());
			stmt.setInt(2, cri.getRowPerpage());
			stmt.setString(3, cri.getSearchText());
			stmt.setString(4, id);
			stmt.registerOutParameter(5, OracleTypes.CURSOR);
			stmt.executeQuery();
			ResultSet rs = (ResultSet)stmt.getObject(5);
			while(rs.next()) {
				Board board = new Board();
				board.setRn(rs.getString("rn"));
				board.setTitle(rs.getString("title"));
				board.setId(rs.getString("id"));
				board.setWdate(rs.getString("wdate"));
				board.setCount(rs.getString("count"));
				Board.add(board);
			}
			
		} catch (SQLException e) {	
			e.printStackTrace();
		}finally {
			resourceClose(conn, stmt);
		}
		return Board;
	}
	public List<Board> myqnaboard(Criteria cri,String id) {
		PreparedStatement stmt = null;
		Connection conn = null;
		List<Board> board = new ArrayList<Board>();
		System.out.println("아이디 : " + id);

		String sql = " select rownum, z.seqno as seqno, z.content as content, z.wdate as wdate, z.id as id, z.count as count";
		sql += " from(select q.seqno, q.content, q.count, q.wdate, q.id";
		sql += " from qna q, members m";
		sql += " where q.id = m.id)z";
		sql += " where z.id = ?";

		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			stmt.setString(1, id);
			//stmt.setString(2, id);

			ResultSet rs = stmt.executeQuery();
			Board b = null;
			while(rs.next()) {
				b = new Board();
				System.out.println("제목님아 나오셈" +rs.getString("content"));
				b.setRn(rs.getString("rownum"));
				b.setSeqno(rs.getString("seqno"));
				b.setContent(rs.getString("content"));
				b.setWdate(rs.getString("wdate"));
				b.setId(rs.getString("id"));
				b.setCount(rs.getString("count"));
				board.add(b);
			}
		} catch (SQLException e) {	
			e.printStackTrace();
		}finally {
			resourceClose(conn, stmt);
		}
		return board;
	}
	public List<Board> myadoptboard(Criteria cri,String id) {

		PreparedStatement stmt = null;
		Connection conn = null;
		List<Board> board = new ArrayList<Board>();
		System.out.println("아이디 : " + id);

		String sql = " select rownum, z.seqno as seqno, z.content as content, z.wdate as wdate, z.id as id, z.count as count";
		sql += " from(select a.seqno, a.content, a.count, a.wdate, a.id";
		sql += " from adopt a, members m";
		sql += " where a.id = m.id)z";
		sql += " where z.id = ?";

		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			stmt.setString(1, id);
			//stmt.setString(2, id);

			ResultSet rs = stmt.executeQuery();
			Board b = null;
			while(rs.next()) {
				b = new Board();
				b.setRn(rs.getString("rownum"));
				b.setSeqno(rs.getString("seqno"));
				b.setContent(rs.getString("content"));
				b.setWdate(rs.getString("wdate"));
				b.setId(rs.getString("id"));
				b.setCount(rs.getString("count"));
				board.add(b);
			}
		} catch (SQLException e) {	
			e.printStackTrace();
		}finally {
			resourceClose(conn, stmt);
		}
		return board;
	}
	public List<Board> myadoptreviewboard(Criteria cri,String id) {
		PreparedStatement stmt = null;
		Connection conn = null;
		List<Board> board = new ArrayList<Board>();
		System.out.println("아이디 : " + id);

		String sql = " select rownum, z.seqno as seqno, z.content as content, z.wdate as wdate, z.id as id, z.count as count";
		sql += " from(select ar.seqno, ar.content, ar.count, ar.wdate, ar.id";
		sql += " from adopt_review ar, members m";
		sql += " where ar.id = m.id)z";
		sql += " where z.id = ?";

		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			stmt.setString(1, id);
			//stmt.setString(2, id);

			ResultSet rs = stmt.executeQuery();
			Board b = null;
			while(rs.next()) {
				b = new Board();
				b.setRn(rs.getString("rownum"));
				b.setSeqno(rs.getString("seqno"));
				b.setContent(rs.getString("content"));
				b.setWdate(rs.getString("wdate"));
				b.setId(rs.getString("id"));
				b.setCount(rs.getString("count"));
				board.add(b);
			}
		} catch (SQLException e) {	
			e.printStackTrace();
		}finally {
			resourceClose(conn, stmt);
		}
		return board;
	}
	public List<Board> myplantboard(Criteria cri,String id) {
		PreparedStatement stmt = null;
		Connection conn = null;
		List<Board> board = new ArrayList<Board>();
		System.out.println("아이디 : " + id);

		String sql = " select rownum, z.mplant_seqno as seqno, z.etc as etc, z.wdate as wdate, z.id as id, z.count as count";
		sql += " from(select mp.mplant_seqno, mp.etc, mp.count, mp.wdate, mp.id";
		sql += " from mplant mp, members m";
		sql += " where mp.id = m.id)z";
		sql += " where z.id = ?";

		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			stmt.setString(1, id);
			//stmt.setString(2, id);

			ResultSet rs = stmt.executeQuery();
			Board b = null;
			while(rs.next()) {
				b = new Board();
				//System.out.println("이티씨 나오셈" +rs.getString("etc"));
				b.setRn(rs.getString("rownum"));
				b.setSeqno(rs.getString("seqno"));
				b.setEtc(rs.getString("etc"));
				b.setWdate(rs.getString("wdate"));
				b.setId(rs.getString("id"));
				b.setCount(rs.getString("count"));
				board.add(b);
			}
		} catch (SQLException e) {	
			e.printStackTrace();
		}finally {
			resourceClose(conn, stmt);
		}
		return board;
	}
	public List<Board> myreply(Criteria cri,String id) {
		PreparedStatement stmt = null;
		Connection conn = null;
		List<Board> board = new ArrayList<Board>();
		System.out.println("아이디 : " + id);

		String sql = " select rownum, b.seqno, b.title, b.count, b.wdate, b.id";
		       sql += " from board b, board_reply br ";
			   sql += " where b.seqno = br.seqno and br.id= ?";

		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			stmt.setString(1, id);
			//stmt.setString(2, id);

			ResultSet rs = stmt.executeQuery();
			Board b = null;
			while(rs.next()) {
				b = new Board();
				//System.out.println("이티씨 나오셈" +rs.getString("etc"));
				b.setRn(rs.getString("rownum"));
				b.setSeqno(rs.getString("seqno"));
				b.setTitle(rs.getString("title"));
				b.setWdate(rs.getString("wdate"));
				b.setId(rs.getString("id"));
				b.setCount(rs.getString("count"));
				board.add(b);
			}
		} catch (SQLException e) {	
			e.printStackTrace();
		}finally {
			resourceClose(conn, stmt);
		}
		return board;
	}
	public List<Board> myqnareplyboard(Criteria cri,String id) {
		PreparedStatement stmt = null;
		Connection conn = null;
		List<Board> board = new ArrayList<Board>();
		System.out.println("아이디 : " + id);
		String sql = " select rownum, z.seqno as seqno, z.content as content, z.count as count, z.wdate as wdate, z.id as id";
		sql += " from(select q.seqno,q.content, q.count, q.wdate, q.id";
		sql += " from qna q, qna_reply qr, members m";
		sql += " where q.seqno = qr.seqno";
		sql += " and q.seqno = qr.seqno)z";
		sql += " where z.id = ?";

		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			stmt.setString(1, id);
			//stmt.setString(2, id);

			ResultSet rs = stmt.executeQuery();
			Board b = null;
			while(rs.next()) {
				b = new Board();
				//System.out.println("이티씨 나오셈" +rs.getString("etc"));
				b.setRn(rs.getString("rownum"));
				b.setSeqno(rs.getString("seqno"));
				b.setContent(rs.getString("content"));
				b.setWdate(rs.getString("wdate"));
				b.setId(rs.getString("id"));
				b.setCount(rs.getString("count"));
				board.add(b);
			}
		} catch (SQLException e) {	
			e.printStackTrace();
		}finally {
			resourceClose(conn, stmt);
		}
		return board;
	}

	public Plantmember mypage(String id) {
		CallableStatement stmt = null;
		Connection conn = null;
		Plantmember member = new Plantmember();


		try {
			conn = ds.getConnection();
			String sql = "call p_mypage(?,?,?,?)";

			stmt = conn.prepareCall(sql);
			stmt.setObject(1, id);
			stmt.registerOutParameter(2, OracleTypes.CURSOR);
			stmt.registerOutParameter(3, OracleTypes.INTEGER);
			stmt.registerOutParameter(4, OracleTypes.INTEGER);
			stmt.executeQuery();

			ResultSet rs = (ResultSet)stmt.getObject(2);
			if(rs.next()) {
				member.setNickname(rs.getString("nickname"));
				member.setWdate(rs.getString("wdate"));
				member.setEmail(rs.getString("email"));
				member.setPhone(rs.getString("phone"));
			}
			member.setCount(stmt.getString(3));

			member.setReply(stmt.getString(4));



		} catch (SQLException e) {	
			e.printStackTrace();
		}finally {
			resourceClose(conn, stmt);
		}
		return member;
	}

	/*
	 * public List<Board> boardDetail(String seqno) { PreparedStatement stmt = null;
	 * Connection conn = null; Board board = new Board();
	 * 
	 * try { conn = ds.getConnection(); //조회수 String sql =
	 * "update qna set count = count+1 where seqno =" + seqno; stmt.executeUpdate();
	 * 
	 * //게시물 세부내용 sql = " select seqno, title, content, id,"; sql
	 * +="	To_char(b.wdate, 'YYYY-MM-DD(DY) HH:MI:SS PM') wdate,"; sql
	 * +=" count, (select name from members m where m.id = b.id) name"; sql
	 * +="	from board b"; sql +="	where b.seqno = ?"; sql +="	union all"; sql
	 * +="	select br_seqno, '', content, id,"; sql
	 * +=" TO_CHAR(r.wdate, 'YYYY-MM-DD(DY) HH:MI:SS PM') wdate,"; sql
	 * +=" 0, (select name from members m where m.id = r.id) name"; sql
	 * +="	from board_reply r"; sql +="	where r.seqno = ?"; sql +="	union all";
	 * sql +="	select bi_seqno, '', '', '', '', 0, ''"; sql
	 * +="	from board_img i"; sql +="	where i.seqno = ?";
	 * 
	 * stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
	 * ResultSet.CONCUR_UPDATABLE); stmt.setString(1, seqno); stmt.setString(2,
	 * seqno); stmt.setString(3, seqno); ResultSet rs = stmt.executeQuery();
	 * 
	 * rs.next();
	 * 
	 * board.setSeqno(seqno); board.setTitle(rs.getString("title"));
	 * board.setContent(rs.getString("content")); board.setId(rs.getString("id"));
	 * board.setWdate(rs.getString("wdate")); board.setCount(rs.getString("count"));
	 * board.setName(rs.getString("name"));
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); }finally {
	 * resourceClose(conn, stmt); }
	 * 
	 * return board; }
	 */
	private void resourceClose(Connection conn, PreparedStatement stmt) {
		//자원반납
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void resourceClose(Connection conn, CallableStatement stmt) {
		//자원반납
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}