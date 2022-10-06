package com.plant.dao;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plant.dto.Plantmember;

import oracle.jdbc.OracleTypes;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;



@Repository
public class MemberDaoImp implements MemberDao{
	
	@Autowired
	private DataSource ds;
	
	public Map<String, String> loginProc(String id, String pw) {

		PreparedStatement stmt = null;
		Connection conn = null;
		Map<String, String> map = new HashMap<String, String>();
		//2.sql문작성
		String sql = "select * from members where id = ?";
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(sql);			
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();			
			
			if(rs.next()) {
				if(rs.getString("pw").equals(pw)){					
					map.put("login", "ok"); //로그인 성공
					map.put("name", rs.getString("name"));
				} else {
					map.put("login", "pwFail"); //패스워드 틀림
				}				
			} else {
				map.put("login", "fail");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resourceClose(conn, stmt);
		}


		return map;
	}
	



public int insertMember(Plantmember member) {
		
		 String email = member.getEmail() + "@" + member.getDomain();
		 

		//sql문장 작성
		Connection conn = null;
		CallableStatement stmt = null;
		int rs = 0;
		try {
			conn = ds.getConnection();
			String sql = "call p_insertMember(?,?)";
			stmt = conn.prepareCall(sql);
			
			StructDescriptor st_desc = StructDescriptor.createDescriptor("OBJ_MEMBER",conn);
			Object[] obj_member = {member.getId(),
								   member.getPw(),
								   member.getName(),
								   email,
								   member.getNickname(),
								   member.getPhone()
								   };
			STRUCT member_rec = new STRUCT(st_desc, conn, obj_member);
			
			stmt.setObject(1, member_rec);
			stmt.registerOutParameter(2, OracleTypes.INTEGER);
			stmt.executeQuery();
			
			rs = stmt.getInt(2);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resourceClose(conn,stmt);
		}

		return rs;
	}
	

	public Map<String, String> pwfind(String id, String name, String email) {
		
		PreparedStatement stmt = null;
		Connection conn = null;
		Map<String, String> map = new HashMap<String, String>();
		
	      String sql = "select pw from members where id = ? "
	      		+ "and email= ? "
	      		+ "and name = ?";
			/* Plantmember p = new Plantmember(); */
	         try {
	        	conn = ds.getConnection();
	            stmt = conn.prepareStatement(sql);
	            stmt.setString(1, id);
	            stmt.setString(2, email);
	            stmt.setString(3, name);

	            ResultSet rs = stmt.executeQuery();
	            
	            if(rs.next()) {
                  //비밀번호 찾기 성공
                  map.put("find", "pwfind");
                  map.put("pw", rs.getString("pw"));
                  
               } else {
                  //비밀번호 찾기 실패
                  map.put("find", "pwlost");
               }
	            
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }finally {
	 			resourceClose(conn,stmt);
	 		}
	         return map;

	}






	/*
	 * public int mypageEdit(HttpServletRequest req) { String pw =
	 * req.getParameter("loginPw"); String nickname =
	 * req.getParameter("editnickname"); String email =
	 * req.getParameter("editemail"); String phone =
	 * req.getParameter("cellphoneNo"); String id = req.getParameter("mypageId");
	 * 
	 * 
	 * String sql =
	 * "update members set pw=?, nickname=?, email=?, phone=? where id=?"; int rs =
	 * 0; Statement stmt; try {
	 * 
	 * stmt = conn.prepareStatement(sql); stmt.setString(1, pw); stmt.setString(2,
	 * nickname); stmt.setString(3, email); stmt.setString(4, phone);
	 * stmt.setString(5, id);
	 * 
	 * 
	 * 
	 * rs = stmt.executeUpdate(sql);
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); }
	 * 
	 * return rs;
	 * 
	 * }
	 */



public Plantmember mypageEdit(Plantmember member) {
	CallableStatement stmt = null;
	Connection conn = null;
	String 	sql = "call p_update_mypage(?,?,?,?,?)";
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);

			stmt.setString(1, member.getId());
			stmt.setString(2, member.getPw());
			stmt.setString(3, member.getNickname());
			stmt.setString(4, member.getEmail());
			stmt.setString(5, member.getPhone());
			
			stmt.executeQuery();
		} catch (SQLException e) {	
			e.printStackTrace();
		}finally {
 			resourceClose(conn,stmt);
 		}
		return member;
	}

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




public Plantmember editview(String id) {
	PreparedStatement stmt = null;
	Connection conn = null;
	
	Plantmember member = new Plantmember();
	String 	sql = "select id,nickname,email,phone from members where id = ?";
		
		try {
			
			conn = ds.getConnection();
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
			member.setId(rs.getString("id"));
			member.setNickname(rs.getString("nickname"));
			member.setEmail(rs.getString("email"));
			member.setPhone(rs.getString("phone"));
			
			}
			
		} catch (SQLException e) {	
			e.printStackTrace();
		}finally {
 			resourceClose(conn,stmt);
 		}
		
		return member;
}




public void memdel(String id) {
	CallableStatement stmt = null;
	Connection conn = null;
	String 	sql = "call p_update_memdel(?)";
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			stmt.setString(1, id);
			
			stmt.executeQuery();
		} catch (SQLException e) {	
			e.printStackTrace();
		}finally {
 			resourceClose(conn,stmt);
 		}
}



	
	
	
	
	
	
	
	
}
