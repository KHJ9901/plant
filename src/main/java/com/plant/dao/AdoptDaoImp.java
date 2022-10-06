package com.plant.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plant.dto.Adopt;
import com.plant.dto.AdoptFile;
import com.plant.dto.AdoptReply;
import com.plant.dto.AdoptThumb;
import com.plant.dto.Criteria;

import oracle.jdbc.OracleType;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;


@Repository
public class AdoptDaoImp implements AdoptDao {
	
	@Autowired
	private DataSource ds;
	
	//리스트
	public List<Adopt> adoptList(Criteria cri){
		
		Connection conn = null;
		CallableStatement stmt = null;
		List<Adopt> adopt = new ArrayList<Adopt>();
		
		String search_content = null;
		String search_id = null;
		
		if(cri.getSearchField() != null && cri.getSearchField().equals("content")) {
			search_content = cri.getSearchText();
		}
		if(cri.getSearchField() != null && cri.getSearchField().equals("id")) {
			search_id = cri.getSearchText();
		}
		
		String sql = "call p_getadoptlist(?,?,?,?,?) ";
			  
		  try {
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			stmt.setInt(1, cri.getCurrentPage());
			stmt.setInt(2, cri.getRowPerpage());
			stmt.setString(3, search_id);
			stmt.setString(4, search_content);
			stmt.registerOutParameter(5, OracleTypes.CURSOR);
			stmt.executeQuery();
			
			ResultSet rs = (ResultSet)stmt.getObject(5);
			
			while(rs.next()) {
				
				Adopt a = new Adopt();
				a.setNo(rs.getString("rn"));
				a.setSeqno(rs.getString("seqno"));
				a.setContent(rs.getString("content"));
				a.setCount(rs.getString("count"));
				a.setWdate(rs.getString("wdate"));
				a.setId(rs.getString("id"));
				adopt.add(a);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//	resourceClose(conn, stmt);	
		}
		  return adopt;
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
		
		String sql = "call p_getadopttotal(?, ?, ?)";
		
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
	
	
	//디테일
	public Adopt adetail(String seqno) {
		
		Connection conn = null;
		CallableStatement stmt = null;
		Adopt adopt = new Adopt();

		try {
			String sql = "call p_adopt_detail(?,?,?)";
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			stmt.setInt(1, Integer.parseInt(seqno));
			stmt.registerOutParameter(2, OracleTypes.CURSOR);
			stmt.registerOutParameter(3, OracleTypes.CURSOR);
			//stmt.registerOutParameter(4, OracleTypes.CURSOR);
			stmt.executeUpdate();
			
			ResultSet rs = (ResultSet)stmt.getObject(2);
			
			rs.next();
			adopt.setSeqno(seqno);
			adopt.setId(rs.getString("id"));
			adopt.setStation(rs.getString("station"));
			adopt.setContent(rs.getString("content"));
			adopt.setCount(rs.getString("count"));
			adopt.setWdate(rs.getString("wdate"));
			adopt.setSdate(rs.getString("sdate"));
			adopt.setMplant_seqno(rs.getString("mplant_seqno"));
			adopt.setPname(rs.getString("pname"));
			adopt.setWater(rs.getString("water"));
			adopt.setPlace(rs.getString("place"));
			adopt.setTemp(rs.getString("temp"));
			adopt.setMoist(rs.getString("moist"));
			
			//댓글
			List<AdoptReply> adoptreply = new ArrayList<AdoptReply>();
			rs = (ResultSet)stmt.getObject(3);
			
			while(rs.next()){ 
				AdoptReply ar = new AdoptReply();
				ar.setComment(rs.getString("content")); 
				ar.setId(rs.getString("id"));
				ar.setWdate(rs.getString("wdate")); 
				adoptreply.add(ar);
				} 
				
				adopt.setAdoptreply(adoptreply);
			
			/*
			List<AdoptFile> fileList = new ArrayList<AdoptFile>();
			
			rs = (ResultSet)stmt.getObject(4);
			
			while(rs.next()) {
				AdoptFile adoptfile = new AdoptFile();
				adoptfile.setNo(rs.getString("no"));
				adoptfile.setFilename(rs.getString("filename"));
				adoptfile.setSavefilename(rs.getString("savefilename"));
				adoptfile.setFilesize(rs.getString("filesize"));
				adoptfile.setFiletype(rs.getString("filetype"));
				adoptfile.setFilepath(rs.getString("filepath"));
				
				AdoptThumb at = new AdoptThumb();
				at.setFileName(rs.getString("thumb_name"));
				at.setFileSize(rs.getString("thumb_size"));
				at.setFilePath(rs.getString("thumb_path"));
				adoptfile.setAdoptthumb(at);
				
				fileList.add(adoptfile);
			}
			
			adopt.setAdoptFile(fileList);
			*/
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resourceClose(conn, stmt);	
		}
		
		return adopt;
	}
	
	public String insert(Adopt adopt) {
		
		Connection conn = null;
		CallableStatement stmt = null;
		String seqno = null;
		
		try {
			String sql = "call p_insert_adopt(?,?)";
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			
			StructDescriptor st_adopt = StructDescriptor.createDescriptor("OBJ_ADOPT", conn);
			Object[] obj_adopt = {adopt.getId(), adopt.getStation(), adopt.getContent(), 
								  adopt.getWater(), adopt.getPlace(), adopt.getTemp(), adopt.getMoist(),
								  adopt.getPname(), adopt.getMplant_seqno()};
			STRUCT adopt_rec = new STRUCT(st_adopt, conn, obj_adopt);

			stmt.setObject(1, adopt_rec);
			
			/*
			ArrayDescriptor desc = ArrayDescriptor.createDescriptor("ADOPTFILE_NT", conn);
			ARRAY adoptfile_arr = null;
			
			if(adoptfile != null) {
				
				StructDescriptor st_adoptfile_thumb = StructDescriptor.createDescriptor("OBJ_ADOPTFILE_THUMB", conn);
				STRUCT adoptfile_thumb_rec = null;
				Object[] obj_adoptfile_thumb = null;
				
				if(adoptfile.getAdoptthumb() != null) {
					
					obj_adoptfile_thumb = new Object[] { adoptfile.getAdoptthumb().getFileSize(),
														 adoptfile.getAdoptthumb().getFilePath()};
				} 
				
				adoptfile_thumb_rec = new STRUCT(st_adoptfile_thumb, conn, obj_adoptfile_thumb);
				
				StructDescriptor st_adoptfile = StructDescriptor.createDescriptor("OBJ_ADOPTFILE", conn);
				
				Object[] obj_adoptfile = {adoptfile.getSavefilename(), adoptfile.getFilename(), adoptfile.getFilesize(), 
									   adoptfile.getFiletype(), adoptfile.getFilepath(), 
									   adoptfile_thumb_rec};
				
				STRUCT[] adoptfile_rec = new STRUCT[1];
				adoptfile_rec[0] = new STRUCT(st_adoptfile, conn, obj_adoptfile);
				
				adoptfile_arr = new ARRAY(desc, conn, adoptfile_rec);
				
			} else {
				adoptfile_arr = new ARRAY(desc, conn, null);
			}
			
			stmt.setArray(2, adoptfile_arr);
			*/
			
			stmt.registerOutParameter(2, OracleType.VARCHAR2);
			stmt.executeQuery();
			
			seqno = stmt.getString(2);
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resourceClose(conn, stmt);	
		}
		
		return seqno;
	}
	
	
	public void insertAdoptThumb (String attach_no, AdoptFile adoptfile) {
		
		PreparedStatement stmt = null;
		Connection conn = null;
		
		String sql = "INSERT INTO adoptfile_thumb(no, filename, filesize, filepath, attach_no)"
				   + "VALUES(ADOPTFILE_THUMB_SEQNO.NEXTVAL, ?, ?, ?, ?)";
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(sql);
			AdoptThumb thumb = adoptfile.getAdoptthumb();
			stmt.setString(1, thumb.getFileName());
			stmt.setString(2, thumb.getFileSize());
			stmt.setString(3, thumb.getFilePath());
			stmt.setString(4, attach_no);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resourceClose(conn, stmt);	
		}
	}
	
	
	public String insertAdoptFile(String seqno, AdoptFile adoptfile) {
		String sql = "INSERT INTO adoptfile(no, filename, savefilename, filesize, filetype, filepath, adopt_no)"
				   + "VALUES (ADOPTFILE_SEQNO.NEXTVAL, ?,?,?,?,?,?)";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		String attach_no = null;
		
			try {
				conn = ds.getConnection();
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, adoptfile.getFilename());
				stmt.setString(2, adoptfile.getSavefilename());
				stmt.setString(3, adoptfile.getFilesize());
				stmt.setString(4, adoptfile.getFiletype());
				stmt.setString(5, adoptfile.getFilepath());
				stmt.setString(6, seqno);
				stmt.executeQuery();
				
				sql = "SELECT max(no) FROM adoptfile";
				conn = ds.getConnection();
				ResultSet rs = stmt.executeQuery();
				rs.next();
				attach_no = rs.getString(1);
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				resourceClose(conn, stmt);	
			}
			
			return attach_no;
		
	}
	
	public void update(Adopt adopt) {
		String sql = "call p_update_adopt(?,?,?,?,?,?,?,?)";
		Connection conn = null;
		CallableStatement stmt = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			stmt.setString(1, adopt.getStation());
			stmt.setString(2, adopt.getContent());
//			stmt.setString(3, adopt.getMplant_seqno());
			stmt.setString(3, adopt.getPname());
			stmt.setString(4, adopt.getWater());
			stmt.setString(5, adopt.getPlace());
			stmt.setString(6, adopt.getTemp());
			stmt.setString(7, adopt.getMoist());
			stmt.setString(8, adopt.getSeqno());
			stmt.executeUpdate();
			
			/*
			if(adoptfile != null) {
				
				String adoptfile_no = insertAdoptFile(adopt.getSeqno(), adoptfile);
				String filetype = adoptfile.getFiletype();
				
				if(filetype.substring(0, filetype.indexOf("/")).equals("image")) {
					insertAdoptThumb(adoptfile_no, adoptfile);
				}
			}
			*/
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resourceClose(conn, stmt);	
		}
	}
	
	public Map<String, String> adoptdel(String seqno) {
		
		Map<String, String> map = new HashMap<String, String>();
		Connection conn = null;
		CallableStatement stmt = null;
		
		
		try {
			String sql = "call p_delete_adopt(?)";
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			stmt.setString(1, seqno);
			//stmt.registerOutParameter(2, OracleTypes.VARCHAR);
			//stmt.registerOutParameter(3, OracleTypes.VARCHAR);
			//stmt.registerOutParameter(4, OracleTypes.VARCHAR);
			stmt.executeQuery();		
			
			//map.put("savefilename", stmt.getString(2));
			//map.put("filepath", stmt.getString(3));
			//map.put("thumb_filename", stmt.getString(4));
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resourceClose(conn, stmt);	
		} 
	
		return map;
	}
	
	
	
	//자원반납
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
	
	//자원반납
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
	public String insertAdopt(Adopt adopt) {
		
		CallableStatement stmt = null;
		Connection conn = null;
		String seqno = null;
		
		try {
			String sql = "call p_insert_adopt(?,?)";
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			
			StructDescriptor st_adopt = StructDescriptor.createDescriptor("OBJ_BOARD", conn);
			Object[] obj_adopt = {adopt.getId(), adopt.getStation(), adopt.getContent(), 
								  adopt.getWater(), adopt.getPlace(), adopt.getTemp(), adopt.getMoist(),
								  adopt.getPname(), adopt.getMplant_seqno()};
			STRUCT adopt_rec = new STRUCT(st_adopt, conn, obj_adopt);

			stmt.setObject(1, adopt_rec);
			stmt.registerOutParameter(2, OracleType.VARCHAR2);
			stmt.executeQuery();
			
			seqno = stmt.getString(2);
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resourceClose(conn, stmt);	
		}
		
		return seqno;
	}

	
}
