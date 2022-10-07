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

import com.plant.dto.Criteria;
import com.plant.dto.Qna;
import com.plant.dto.QnaReply;
import com.plant.dto.Qna_Img;
import com.plant.dto.Qna_Thumbnail;

import oracle.jdbc.OracleType;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;


@Repository
public class QnaDaoImp implements QnaDao {

	@Autowired
	private DataSource ds;
	
	
	public List<Qna> askList(Criteria cri) {
		
		Connection conn = null;
		CallableStatement stmt = null;
		List<Qna> ask = new ArrayList<Qna>();
		
		String search_content = null;
		String search_name = null;
		
		//관련내용검색
		if(cri.getSearchField() != null && cri.getSearchField().equals("content")) {
			search_content = cri.getSearchText();
		}
		//이름검색
		if(cri.getSearchField() != null && cri.getSearchField().equals("name")) {
			search_name = cri.getSearchText();
		}
		
		String sql = "call p_getqnalist(?,?,?,?,?)";
			  
		   try {
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);   
			stmt.setInt(1, cri.getCurrentPage());
			stmt.setInt(2, cri.getRowPerpage());
			stmt.setString(3, search_name);
			stmt.setString(4, search_content);
			stmt.registerOutParameter(5, OracleTypes.CURSOR);
			stmt.executeQuery();
			
			ResultSet rs =(ResultSet)stmt.getObject(5);
			
			while(rs.next()) {
				Qna a = new Qna();
				a.setNo(rs.getString("rn"));
				a.setSeqno(rs.getString("seqno"));
				a.setContent(rs.getString("content"));
				a.setId(rs.getString("id"));
				a.setReply(rs.getString("reply"));
				a.setCount(rs.getString("count"));
				a.setWdate(rs.getString("wdate"));
				ask.add(a);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				resourceClose(conn, stmt);
			}
			return ask;	
	}
	
	public int getTotalRec(Criteria cri) {
		int total = 0;
		String search_content = null;
		String search_name = null;
		
		//관련내용검색
		if(cri.getSearchField() != null && cri.getSearchField().equals("content")) {
			search_content = cri.getSearchText();
		}
		//이름검색
		if(cri.getSearchField() != null && cri.getSearchField().equals("name")) {
			search_name = cri.getSearchText();
		}
		
		String sql="call p_getqnatotal(?,?,?)";
		Connection conn = null;
		CallableStatement stmt = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			stmt.setString(1, search_name);
			stmt.setString(2, search_content);
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
	
	public void asklike(HttpServletRequest req) {
		String id = req.getParameter("id");
		String seqno = req.getParameter("seqno");
		String sql = "INSERT INTO qna_like(ql_seqno, id, seqno) VALUES (qna_like_seqno.NEXTVAL, ?,?)";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setString(2, seqno);
			ResultSet rs = stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resourceClose(conn, stmt);
        }
	}
	
	
	public Qna askDetail(String seqno) {
		
		Qna ask = new Qna();
		Connection conn = null;
		CallableStatement stmt = null;
		
		try {
			String sql = "call p_qna_detail(?,?,?,?)";
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			stmt.setInt(1, Integer.parseInt(seqno));
			stmt.registerOutParameter(2, OracleTypes.CURSOR);
			stmt.registerOutParameter(3, OracleTypes.CURSOR);
			stmt.registerOutParameter(4, OracleTypes.CURSOR);
			stmt.executeQuery();

			ResultSet rs = (ResultSet)stmt.getObject(2);
			rs.next();
			
			ask.setSeqno(seqno);
			ask.setContent(rs.getString("content"));
			ask.setId(rs.getString("id"));
			ask.setWdate(rs.getString("wdate"));
			ask.setCount(rs.getString("count"));
			ask.setReply(rs.getString("reply"));
			ask.setLike(rs.getString("l"));
					
			//댓글 목록
			List<QnaReply> askReply = new ArrayList<QnaReply>();
			rs = (ResultSet)stmt.getObject(3);
			
			while(rs.next()) {
				QnaReply r = new QnaReply();
				r.setContent(rs.getString("content"));
				r.setId(rs.getString("id"));
				r.setWdate(rs.getString("wdate"));
				askReply.add(r);
			}
			ask.setAskreply(askReply);
			
			//첨부파일 저장
			List<Qna_Img> qnaimg = new ArrayList<Qna_Img>();
			rs = (ResultSet)stmt.getObject(4);
			while(rs.next()) {
				Qna_Img q = new Qna_Img();
				q.setQiseqno(rs.getString("qi_seqno"));
				q.setUploadfile(rs.getString("uploadfile"));
				q.setSavefile(rs.getString("savefile"));
				q.setLocation(rs.getString("location"));
				q.setFilesize(rs.getString("filesize"));
				q.setType(rs.getString("type"));
				
				
				Qna_Thumbnail qt = new Qna_Thumbnail();
				qt.setNo(rs.getString("seqno"));
				qt.setFilename(rs.getString("thumb_name"));
				qt.setFilesize(rs.getString("thumb_size"));
				qt.setFilepath(rs.getString("thumb_path"));
				q.setThumbnail(qt);
					
				qnaimg.add(q);
			}
			ask.setQna_img(qnaimg);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resourceClose(conn, stmt);
        }
		return ask;
	}
	
	public String insert(Qna qna, Qna_Img qnaimg) {
		
		Connection conn = null;
		CallableStatement stmt = null;
		String seqno = null;
		
		try {
			String sql="call p_insert_qna(?,?,?)";
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
		    StructDescriptor st_qna = StructDescriptor.createDescriptor("OBJ_QNA",conn);
	         Object[] obj_qna = {qna.getContent(), qna.getId()};
	         STRUCT board_rec = new STRUCT(st_qna, conn, obj_qna);

	         stmt.setObject(1,board_rec);


	         ArrayDescriptor desc = ArrayDescriptor.createDescriptor("QNA_IMG_NT", conn);
	         ARRAY attach_arr = null;


	         if(qnaimg != null) {
	            StructDescriptor st_thumb = StructDescriptor.createDescriptor("OBJ_QNA_THUMB",conn);
	            Object[] obj_thumb = null;
	            STRUCT qna_thumb_rec = null;
	            if(qnaimg.getThumbnail() != null) {
	               obj_thumb = new Object[]{
	            		  qnaimg.getThumbnail().getFilename(),
	            		  qnaimg.getThumbnail().getFilesize(),
	            		  qnaimg.getThumbnail().getFilepath()
	            	};
	            } 

	            qna_thumb_rec = new STRUCT(st_thumb, conn, obj_thumb);

	            StructDescriptor st_attach = StructDescriptor.createDescriptor("OBJ_QNA_IMG",conn);
	            Object[] obj_qna_img = {qnaimg.getUploadfile(),qnaimg.getSavefile(),
	            	qnaimg.getFilesize(),qnaimg.getType(),
	            	qnaimg.getLocation(),
	            	qna_thumb_rec};
	            STRUCT[] attach_rec = new STRUCT[1];
	            attach_rec[0] = new STRUCT(st_attach, conn, obj_qna_img);


	            attach_arr = new ARRAY(desc, conn, attach_rec );
	         }else {

	            attach_arr = new ARRAY(desc, conn, null );

	         }
	         stmt.setArray(2, attach_arr);
	         stmt.registerOutParameter(3, OracleType.VARCHAR2);
	         stmt.executeQuery();

	         seqno = stmt.getString(3);
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         resourceClose(conn, stmt);
	      }

	      return seqno;
	}
	
	public void reply(HttpServletRequest req) {
	      
	      String content = req.getParameter("content");
	      String replyid = req.getParameter("replyid");
	      String qnaseqno = req.getParameter("qnaseqno");
	      String sql = "insert into qna_reply(qr_seqno, content, id, seqno) values (qna_reply_seqno.nextval,?,?,?)";
	      
	      Connection conn = null;
	      PreparedStatement stmt = null;
	      
	      try {
	    	 conn = ds.getConnection(); 
	         stmt = conn.prepareStatement(sql);
	         stmt.setString(1, content);
	         stmt.setString(2, replyid);
	         stmt.setString(3, qnaseqno);
	         ResultSet rs = stmt.executeQuery();	         	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
				resourceClose(conn, stmt);
	      }	      
	 
	}
	
	public Map<String, String> qnadel(String seqno) {
		  
		Map<String, String> map = new HashMap<String, String>();
		  Connection conn = null;
		  CallableStatement stmt = null;
		
	      try {
	    	 String sql = "call p_deleteQna(?,?,?,?)";
	    	 conn = ds.getConnection();
	    	 stmt = conn.prepareCall(sql);
	    	 stmt.setString(1, seqno);
	    	 stmt.registerOutParameter(2, OracleTypes.VARCHAR);
	    	 stmt.registerOutParameter(3, OracleTypes.VARCHAR);
	    	 stmt.registerOutParameter(4, OracleTypes.VARCHAR);
	         stmt.executeQuery();
	         
	         map.put("savefilename", stmt.getString(2));
	         map.put("filepath", stmt.getString(3));
	         map.put("thumb_file", stmt.getString(4));
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
		     resourceClose(conn, stmt);
		  }
		return map;	            
	 
	}
	
	public void insertQna_Thumbnail(String qnaimg_no, Qna_Img qnaimg) {
		//썸네일 저장
		String sql = "INSERT INTO qna_img_shumb(seqno, filename, filesize, filepath, qi_seqno) "
			+ " VALUES (qna_ing_thumb_seqno.NEXTVAL, ?,?,?,?)";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(sql);
			Qna_Thumbnail thumb = qnaimg.getThumbnail();
			stmt.setString(1, thumb.getFilename());
			stmt.setString(2, thumb.getFilesize());
			stmt.setString(3, thumb.getFilepath());
			stmt.setString(4, qnaimg_no);
			stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resourceClose(conn, stmt);
		}
		
	}
	
	public String insertQna_img(String seqno, Qna_Img qnaimg) {
		//첨부파일저장
		String sql= " INSERT INTO qna_img(qi_seqno, uploadfile, savefile, filesize, type, location, seqno)"
				  + " VALUES (qna_img_seqno.NEXTVAL, ?,?,?,?,?,?)";	
		Connection conn = null;
		PreparedStatement stmt = null;
		String qnaimg_no = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, qnaimg.getUploadfile());
			stmt.setString(2, qnaimg.getSavefile());
			stmt.setString(3, qnaimg.getFilesize());
			stmt.setString(4, qnaimg.getType());
			stmt.setString(5, qnaimg.getLocation());
			stmt.setString(6, seqno);
			stmt.executeQuery();
			
			sql = "SELECT max(qi_seqno) FROM qna_img";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			qnaimg_no =rs.getString(1);
			 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resourceClose(conn, stmt);
		}
		
		return qnaimg_no;
	}

	public void update(Qna qna, Qna_Img qnaimg) {
		//보드 update
		String sql = "call p_updateqna(?,?)";
		Connection conn = null;
		CallableStatement stmt = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			stmt.setString(1, qna.getContent());
			stmt.setString(2, qna.getSeqno());
			stmt.executeUpdate();
			
			//첨부파일 insert
			//첨부파일
			if(qnaimg != null) {
				String qnaimg_no = insertQna_img(qna.getSeqno(), qnaimg);
				String fileType = qnaimg.getType();
				
				//썸네일
				if(fileType.substring(0, fileType.indexOf("/")).equals("image")) {
					insertQna_Thumbnail(qnaimg_no, qnaimg);
				}
			}
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
