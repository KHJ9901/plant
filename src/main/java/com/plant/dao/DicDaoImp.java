package com.plant.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plant.dto.*;

import oracle.jdbc.OracleType;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

@Repository
public class DicDaoImp implements DicDao {
	
	@Autowired
	private DataSource ds;

	//---------------------------리스트------------------------------------------------------------------------------
	public List<Dictionary> dictionList(Criteria cri) {
		
		Connection conn = null;
		CallableStatement stmt = null;

		List<Dictionary> diction = new ArrayList<Dictionary>();
		
		String search_kname = cri.getSearchText();
		
		String sql	= "call p_diction_list(?,?,?,?)";
	
		try {
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			
			stmt.setInt(1, cri.getCurrentPage());
			stmt.setInt(2, cri.getRowPerpage());
			stmt.setString(3, search_kname);
			stmt.registerOutParameter(4, OracleTypes.CURSOR);			
			stmt.executeQuery();
			
			ResultSet rs = (ResultSet)stmt.getObject(4);
			
			while(rs.next()) {
					  
				Dictionary d = new Dictionary();
				DicThumb thumb = new DicThumb();
						  
				d.setSeqno(rs.getString("seqno"));
				d.setKname(rs.getString("kname"));

				thumb.setFileName(rs.getString("filename"));
				thumb.setFilePath(rs.getString("filepath"));
				thumb.setFileSize(rs.getString("filesize"));
				thumb.setFileType(rs.getString("filetype"));
							  
				d.setDicthumb(thumb);
						  
				diction.add(d);
						 
			}
				  
		} catch (SQLException e) {	
			e.printStackTrace();
		} finally {
			resourceClose(conn, stmt);
		}
		return diction;
		
	}
	
	public List<Dictionary> dictionInList(Criteria cri) {
		
		Connection conn = null;
		CallableStatement stmt = null;

		List<Dictionary> diction = new ArrayList<Dictionary>();
		
		String search_kname = cri.getSearchText();
		
		String sql	= "call p_diction_inlist(?,?,?,?)";
	
		try {
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			
			stmt.setInt(1, cri.getCurrentPage());
			stmt.setInt(2, cri.getRowPerpage());
			stmt.setString(3, search_kname);
			stmt.registerOutParameter(4, OracleTypes.CURSOR);			
			stmt.executeQuery();
			
			ResultSet rs = (ResultSet)stmt.getObject(4);
			
			while(rs.next()) {
					  
				Dictionary d = new Dictionary();
				DicThumb thumb = new DicThumb();
						  
				d.setSeqno(rs.getString("seqno"));
				d.setKname(rs.getString("kname"));
				thumb.setFileName(rs.getString("filename"));
				thumb.setFilePath(rs.getString("filepath"));
				thumb.setFileSize(rs.getString("filesize"));
				thumb.setFileType(rs.getString("filetype"));
							  
				d.setDicthumb(thumb);
						  
				diction.add(d);
			}
				  
		} catch (SQLException e) {	
			e.printStackTrace();
		} finally {
			resourceClose(conn, stmt);
		}
		return diction;
	}
	
	public List<Dictionary> dictionOutList(Criteria cri) {
		
		Connection conn = null;
		CallableStatement stmt = null;

		List<Dictionary> diction = new ArrayList<Dictionary>();
		
		String search_kname = cri.getSearchText();
		
		String sql	= "call p_diction_outlist(?,?,?,?)";
	
		try {
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			
			stmt.setInt(1, cri.getCurrentPage());
			stmt.setInt(2, cri.getRowPerpage());
			stmt.setString(3, search_kname);
			stmt.registerOutParameter(4, OracleTypes.CURSOR);			
			stmt.executeQuery();
			
			ResultSet rs = (ResultSet)stmt.getObject(4);
			
			while(rs.next()) {
					  
				Dictionary d = new Dictionary();
				DicThumb thumb = new DicThumb();
						  
				d.setSeqno(rs.getString("seqno"));
				d.setKname(rs.getString("kname"));

				  
				thumb.setFileName(rs.getString("filename"));
				thumb.setFilePath(rs.getString("filepath"));
				thumb.setFileSize(rs.getString("filesize"));
				thumb.setFileType(rs.getString("filetype"));
							  
				d.setDicthumb(thumb);
						  
				diction.add(d);
						 
			}
				  
		} catch (SQLException e) {	
			e.printStackTrace();
		} finally {
			resourceClose(conn, stmt);
		}
		return diction;
	}

	public List<Mplant> mplantList(Criteria cri) {
		
		Connection conn = null;
		CallableStatement stmt = null;

		List<Mplant> mp = new ArrayList<Mplant>();
		
		String search_name = cri.getSearchText();
		
		String sql	= "call p_mplant_list(?,?,?,?)";
  
		try {
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			
			stmt.setInt(1, cri.getCurrentPage());
			stmt.setInt(2, cri.getRowPerpage());
			stmt.setString(3, search_name);
			stmt.registerOutParameter(4, OracleTypes.CURSOR);			
			stmt.executeQuery();
	
			ResultSet rs = (ResultSet)stmt.getObject(4);
					
			while(rs.next()) {
				Mplant m = new Mplant();
				MplantThumb mt = new MplantThumb();
						  
				m.setMplant_seqno(rs.getString("mplant_seqno"));
				m.setName(rs.getString("name"));
				  
				mt.setFileName(rs.getString("filename"));
				mt.setFilePath(rs.getString("filepath"));
				mt.setFileSize(rs.getString("filesize"));
				mt.setFileType(rs.getString("filetype"));
						  
				m.setMplant_thumb(mt);
						  
				mp.add(m);
			}
				  
		} catch (SQLException e) {	
			e.printStackTrace();
		} finally {
			resourceClose(conn, stmt);
		}
			return mp;
	}
	
	//---------------------------상세페이지------------------------------------------------------------------------------
	public Dictionary dictionDetail(String seqno) {
		
		Connection conn = null;
		CallableStatement stmt = null;
		
		Dictionary detail = new Dictionary();
		
		try {
			conn = ds.getConnection();
			String sql = "call p_diction_detail(?,?,?)";
			stmt = conn.prepareCall(sql);
			stmt.setInt(1, Integer.parseInt(seqno));
			stmt.registerOutParameter(2, OracleTypes.CURSOR);
			stmt.registerOutParameter(3, OracleTypes.CURSOR);
			stmt.executeQuery();
			
			ResultSet rs = (ResultSet)stmt.getObject(2);
			rs.next();	
			detail.setSeqno(seqno);
  			detail.setKname(rs.getString("kname"));
  			detail.setEname(rs.getString("ename"));
  			detail.setWater(rs.getString("water"));
  			detail.setPlace(rs.getString("place"));
  			detail.setTemp(rs.getString("temp"));
  			detail.setMoist(rs.getString("moist"));
  			detail.setEtc(rs.getString("etc"));
  			detail.setCate(rs.getString("cate"));
  			List<DicImg> imgList = new ArrayList<DicImg>();
  			
			rs = (ResultSet)stmt.getObject(3);	
			while(rs.next()) {
				
				DicImg img = new DicImg();
				img.setNo(rs.getString("dicimg_seqno"));
				img.setFileName(rs.getString("uploadfile"));
				img.setSaveFileName(rs.getNString("savefile"));
				img.setFileSize(rs.getString("filesize"));
				img.setFileType(rs.getString("type"));
				img.setFilePath(rs.getString("location"));

				imgList.add(img);
			}
			detail.setDicimg(imgList);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return detail;
	}
	
	
	public Mplant mplantDetail(String seqno) {
		
		Connection conn = null;
		CallableStatement stmt = null;
		
		Mplant detail = new Mplant();
		
		try {
			conn = ds.getConnection();
			String sql = "call p_mplant_detail(?,?,?)";
			stmt = conn.prepareCall(sql);
			stmt.setInt(1, Integer.parseInt(seqno));
			stmt.registerOutParameter(2, OracleTypes.CURSOR);
			stmt.registerOutParameter(3, OracleTypes.CURSOR);
			stmt.executeQuery();
			
			ResultSet rs = (ResultSet)stmt.getObject(2);
			rs.next();	
			detail.setMplant_seqno(seqno);
  			detail.setName(rs.getString("name"));
  			detail.setWater(rs.getString("water"));
  			detail.setPlace(rs.getString("place"));
  			detail.setTemp(rs.getString("temp"));
  			detail.setMoist(rs.getString("moist"));
  			detail.setEtc(rs.getString("etc"));
  			detail.setCate(rs.getString("category"));
  			detail.setPlevel(rs.getString("plevel"));
  			detail.setId(rs.getString("id"));
			
  			List<MplantImg> imgList = new ArrayList<MplantImg>();
  			
			rs = (ResultSet)stmt.getObject(3);	
			while(rs.next()) {
				
				MplantImg img = new MplantImg();
				img.setNo(rs.getString("mpi_seqno"));
				img.setFileName(rs.getString("uploadfile"));
				img.setSaveFileName(rs.getNString("savefile"));
				img.setFileSize(rs.getString("filesize"));
				img.setFileType(rs.getString("type"));
				img.setFilePath(rs.getString("location"));

				imgList.add(img);
			}
			detail.setMpimg(imgList);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return detail;
	}

	//---------------------------삽입------------------------------------------------------------------------------
	@Override
	public String insertDiction(Dictionary diction, DicImg dicimg) {
		Connection conn = null;
		CallableStatement stmt = null;
		String seqno = null;
		
		try {
			 conn = ds.getConnection();
			 String sql="call p_insert_diction(?,?,?)";
			 stmt = conn.prepareCall(sql);
			 
			 StructDescriptor st_diction = StructDescriptor.createDescriptor("OBJ_DICTIONARY", conn);
			 Object[] obj_diction = {diction.getKname(), diction.getEname(), diction.getWater(), 
					 				diction.getPlace(), diction.getTemp(), diction.getMoist(), 
					 				diction.getEtc(), diction.getCate()};
			 STRUCT diction_rec = new STRUCT(st_diction, conn, obj_diction);
			 
			 stmt.setObject(1, diction_rec);

			 ArrayDescriptor desc = ArrayDescriptor.createDescriptor("DICTION_IMG_NT", conn);
			 ARRAY img_arr = null;
			 
			//첨부파일
			if(dicimg != null) {
				
			  StructDescriptor st_thumb = StructDescriptor.createDescriptor("OBJ_DICTION_THUMB",conn);	
			  STRUCT  img_thumb_rec = null;
			  Object[] obj_thumb = null;
			  if(dicimg.getDicthumb() != null) {	
				  obj_thumb = new Object[] { dicimg.getDicthumb().getFileName(),
						  					 dicimg.getDicthumb().getFileSize(),
						  					 dicimg.getDicthumb().getFilePath()};
			  }
			  
			  img_thumb_rec = new STRUCT(st_thumb, conn, obj_thumb);
								
			  StructDescriptor st_img = StructDescriptor.createDescriptor("OBJ_DICTION_IMG",conn);
			  			  			  
			  Object[] obj_img = {dicimg.getFileName(), dicimg.getSaveFileName(),
					  dicimg.getFileSize(), dicimg.getFileType(),
					  dicimg.getFilePath(), 
								  img_thumb_rec};
			  
			  STRUCT[] img_rec = new STRUCT[1];
			  img_rec[0] = new STRUCT(st_img, conn, obj_img);				
			  
			  img_arr = new ARRAY(desc, conn, img_rec);			  			  				
			} else {				
				img_arr = new ARRAY(desc, conn, null);
			}
			
			stmt.setArray(2, img_arr);
			
			stmt.registerOutParameter(3, OracleType.VARCHAR2);
			stmt.executeQuery();
			
			seqno = stmt.getString(3);
			
		} catch (Exception e) {									
			e.printStackTrace();
		} finally {
			resourceClose(conn, stmt);
		}
		return seqno; 
	}
	
	@Override
	public String insertMplant(Mplant mplant, MplantImg img) {
		
		Connection conn = null;
		CallableStatement stmt = null;
		String seqno = null;
		
		try {
			 conn = ds.getConnection();
			 String sql="call p_insert_mplant(?,?,?)";
			 stmt = conn.prepareCall(sql);
			 
			 StructDescriptor st_mplant = StructDescriptor.createDescriptor("OBJ_MPLANT", conn);
			 Object[] obj_mplant = {mplant.getName(), mplant.getWater(), mplant.getPlace(),
					 				mplant.getTemp(), mplant.getMoist(), mplant.getEtc(),
					 				mplant.getCate(), mplant.getPlevel(), mplant.getId() };
			 STRUCT mplant_rec = new STRUCT(st_mplant, conn, obj_mplant);
			 
			 stmt.setObject(1, mplant_rec);

			 ArrayDescriptor desc = ArrayDescriptor.createDescriptor("MPIMG_NT", conn);
			 ARRAY img_arr = null;
			 
			//첨부파일
			if(img != null) {
				
			  StructDescriptor st_thumb = StructDescriptor.createDescriptor("OBJ_MPLANT_THUMB",conn);	
			  STRUCT  img_thumb_rec = null;
			  Object[] obj_thumb = null;
			  if(img.getMpthumb() != null) {	
				  obj_thumb = new Object[] { img.getMpthumb().getFileName(),
						  					 img.getMpthumb().getFileSize(),
						  					 img.getMpthumb().getFilePath(),
						  					 img.getMpthumb().getFileType()};
			  }
			  
			  img_thumb_rec = new STRUCT(st_thumb, conn, obj_thumb);
								
			  StructDescriptor st_img = StructDescriptor.createDescriptor("OBJ_MPLANT_IMG",conn);
			  			  			  
			  Object[] obj_img = {img.getFileName(), img.getSaveFileName(),
								  img.getFileSize(), img.getFileType(),
								  img.getFilePath(), 
								  img_thumb_rec};
			  
			  STRUCT[] img_rec = new STRUCT[1];
			  img_rec[0] = new STRUCT(st_img, conn, obj_img);				
			  
			  img_arr = new ARRAY(desc, conn, img_rec);			  			  				
			} else {				
				img_arr = new ARRAY(desc, conn, null);
			}
			
			stmt.setArray(2, img_arr);
			
			stmt.registerOutParameter(3, OracleType.VARCHAR2);
			stmt.executeQuery();
			
			seqno = stmt.getString(3);
			
		} catch (Exception e) {									
			e.printStackTrace();
		} finally {
			resourceClose(conn, stmt);
		}
		
		return seqno; 
	}
	
	
	@Override
	public void insertDicThumbNail(String dicimg_seqno, DicImg dicimg) {
		Connection conn = null;
		CallableStatement stmt = null;
		//썸네일 저장
		String sql = "INSERT INTO dictionary_img_thumb(no, filename, filesize, filepath, dicimg_seqno) "
				   + " VALUES (DICIMG_THUMB_SEQ.NEXTVAL, ?,?,?,?)";
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			DicThumb dicthumb = dicimg.getDicthumb();
			stmt.setString(1, dicthumb.getFileName());
			stmt.setString(2, dicthumb.getFileSize());
			stmt.setString(3, dicthumb.getFilePath());
			stmt.setString(4, dicimg_seqno);
			stmt.executeQuery();
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			resourceClose(conn, stmt);
		}
	}
	
	@Override
	public String insertDicImgFile(String seqno, DicImg dicimg) {
		Connection conn = null;
		CallableStatement stmt = null;
		//첨부파일저장
		String sql = "INSERT INTO dictionary_img(no, uploadfile, savefile, filesize, type, location, seqno)"
				   + " VALUES (DICTIONARY_IMG_SEQNO.NEXTVAL, ?,?,?,?,?,?)";
		String dicimg_seqno = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			stmt.setString(1, dicimg.getFileName());
			stmt.setString(2, dicimg.getSaveFileName());
			stmt.setString(3, dicimg.getFileSize());
			stmt.setString(4, dicimg.getFileType());
			stmt.setString(5, dicimg.getFilePath());
			stmt.setString(6, seqno);
			stmt.executeQuery();
			
			sql = "SELECT max(no) FROM dictionary_img";
			stmt = conn.prepareCall(sql);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			dicimg_seqno =rs.getString(1);			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
			resourceClose(conn, stmt);
		}
		
		return dicimg_seqno;
	}		
	@Override
	public void insertMpThumbNail(String mpi_seqno, MplantImg mpimg) {
		Connection conn = null;
		CallableStatement stmt = null;
		//썸네일 저장
		String sql = "INSERT INTO mplant_img_thumb(no, filename, filesize, filepath, MPI_SEQNO) "
				   + " VALUES (MPLANT_THUMB_SEQ.NEXTVAL, ?,?,?,?)";
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			MplantThumb dicthumb = mpimg.getMpthumb();
			stmt.setString(1, dicthumb.getFileName());
			stmt.setString(2, dicthumb.getFileSize());
			stmt.setString(3, dicthumb.getFilePath());
			stmt.setString(4, mpi_seqno);
			stmt.executeQuery();
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			resourceClose(conn, stmt);
		}
	}
	
	@Override
	public String insertMpImgFile(String seqno, MplantImg mpimg) {
		Connection conn = null;
		CallableStatement stmt = null;
		//첨부파일저장
		String sql = "INSERT INTO mplant_img(no, uploadfile, savefile, filesize, type, location, seqno)"
				   + " VALUES (MPLANT_IMG_SEQNO.NEXTVAL, ?,?,?,?,?,?)";
		String mpi_seqno = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			stmt.setString(1, mpimg.getFileName());
			stmt.setString(2, mpimg.getSaveFileName());
			stmt.setString(3, mpimg.getFileSize());
			stmt.setString(4, mpimg.getFileType());
			stmt.setString(5, mpimg.getFilePath());
			stmt.setString(6, seqno);
			stmt.executeQuery();
			
			sql = "SELECT max(no) FROM mplant_img";
			stmt = conn.prepareCall(sql);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			mpi_seqno =rs.getString(1);			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
			resourceClose(conn, stmt);
		}
		
		return mpi_seqno;
	}
	
	//---------------------------수정------------------------------------------------------------------------------
	@Override
	public String updateDiction(Dictionary diction, DicImg dicimg) {
		Connection conn = null;
		CallableStatement stmt = null;
		String dicSeqno = null;
		//사전 update
		String sql="call p_update_diction(?,?,?,?,?,?,?,?,?,?)";
		try {
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			stmt.setString(1, diction.getKname());
			stmt.setString(2, diction.getEname());
			stmt.setString(3, diction.getLcate());
			stmt.setString(4, diction.getWater());
			stmt.setString(5, diction.getPlace());
			stmt.setString(6, diction.getTemp());
			stmt.setString(7, diction.getMoist());
			stmt.setString(8, diction.getEtc());
			stmt.setString(9, diction.getCate());
			stmt.setString(10, diction.getSeqno());
			stmt.executeQuery();

			if (dicimg != null) {
				
				String seqno = insertDicImgFile(diction.getSeqno(), dicimg);
				String fileType = dicimg.getFileType();
				
				//썸네일
				if (fileType.substring(0, fileType.indexOf("/")).equals("image")) {
					insertDicThumbNail(seqno, dicimg);
				}
			}
			dicSeqno = stmt.getString(10);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dicSeqno;

		//첨부파일

		// 썸네일 insert
	}
	
	public String updateMplant(Mplant mplant, MplantImg mpi) {
		Connection conn = null;
		CallableStatement stmt = null;
		String mpiSeqno = null;
		//사전 update
		String sql="call p_update_mplant(?,?,?,?,?,?,?,?,?,?)";
		try {
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			stmt.setString(1, mplant.getName());
			stmt.setString(2, mplant.getWater());
			stmt.setString(3, mplant.getPlace());
			stmt.setString(4, mplant.getTemp());
			stmt.setString(5, mplant.getMoist());
			stmt.setString(6, mplant.getEtc());
			stmt.setString(7, mplant.getCate());
			stmt.setString(8, mplant.getPlevel());
			stmt.setString(9, mplant.getMplant_seqno());
			stmt.executeQuery();

			if (mpi != null) {
				
				String mpi_seqno = insertMpImgFile(mplant.getMplant_seqno(), mpi);
				String fileType = mpi.getFileType();
				
				//썸네일
				if (fileType.substring(0, fileType.indexOf("/")).equals("image")) {
					insertMpThumbNail(mpi_seqno, mpi);
				}
			}
			mpiSeqno = stmt.getString(9);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mpiSeqno;
	}
	
	//---------------------------삭제------------------------------------------------------------------------------
	@Override
	public Map<String, String> deleteDiction(String seqno) {
		Connection conn = null;
		CallableStatement stmt = null;
		
		Map<String, String> map = new HashMap<String, String>();
		
		String sql = "call p_delete_diction(?,?,?,?)";
		try {
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			stmt.setString(1, seqno);
			stmt.registerOutParameter(2, OracleTypes.VARCHAR);
			stmt.registerOutParameter(3, OracleTypes.VARCHAR);
			stmt.registerOutParameter(4, OracleTypes.VARCHAR);
			stmt.executeQuery();
			map.put("savefilename", stmt.getString(2));
			map.put("filepath", stmt.getString(3));
			map.put("thumb_filename", stmt.getString(4));
		} catch (SQLException e) {			
			e.printStackTrace();
		}	finally {
			resourceClose(conn, stmt);
		}	
		
		return map;
	}
	
	@Override
	public Map<String, String> deleteMplant(String seqno) {
		Connection conn = null;
		CallableStatement stmt = null;
		
		Map<String, String> map = new HashMap<String, String>();
		
		String sql = "call p_delete_mplant(?,?,?,?)";
		try {
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			stmt.setString(1, seqno);
			stmt.registerOutParameter(2, OracleTypes.VARCHAR);
			stmt.registerOutParameter(3, OracleTypes.VARCHAR);
			stmt.registerOutParameter(4, OracleTypes.VARCHAR);
			stmt.executeQuery();
			map.put("savefilename", stmt.getString(2));
			map.put("filepath", stmt.getString(3));
			map.put("thumb_filename", stmt.getString(4));
		} catch (SQLException e) {			
			e.printStackTrace();
		}	finally {
			resourceClose(conn, stmt);
		}	
		
		return map;
	}
	
	//---------------------------총 개수------------------------------------------------------------------------------
	@Override
	public int getDictionaryRec(Criteria cri) {
		
		Connection conn = null;
		
		CallableStatement stmt = null;
		
		int total = 0;
		
		String search_kname = cri.getSearchText();
			
		String sql="call p_dic_total(?,?)";
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			stmt.setString(1, search_kname);
			stmt.registerOutParameter(2, OracleTypes.INTEGER);
			stmt.executeQuery();
			total = stmt.getInt(2);
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}	finally {
			resourceClose(conn, stmt);
		}	
		return total;
	}
	
	@Override
	public int getDictionaryInRec(Criteria cri) {
		Connection conn = null;
		CallableStatement stmt = null;
		int total = 0;
		String search_kname = null;
		//이름검색
		if(cri.getSearchField() != null && cri.getSearchField().equals("kname")) {
			search_kname = cri.getSearchText();
		}
		String sql="call p_dicin_total(?,?)";
		try {
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			stmt.setString(1, search_kname);
			stmt.registerOutParameter(2, OracleTypes.INTEGER);
			stmt.executeQuery();
			total = stmt.getInt(2);
		} catch (SQLException e) {			
			e.printStackTrace();
		}	finally {
			resourceClose(conn, stmt);
		}	
		return total;
	}
	@Override
	public int getDictionaryOutRec(Criteria cri) {
		Connection conn = null;
		CallableStatement stmt = null;
		int total = 0;
		String search_kname = null;
		//이름검색
		if(cri.getSearchField() != null && cri.getSearchField().equals("kname")) {
			search_kname = cri.getSearchText();
		}
		String sql="call p_dicout_total(?,?)";
		try {
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			stmt.setString(1, search_kname);
			stmt.registerOutParameter(2, OracleTypes.INTEGER);
			stmt.executeQuery();
			total = stmt.getInt(2);
		} catch (SQLException e) {			
			e.printStackTrace();
		}	finally {
			resourceClose(conn, stmt);
		}	
		return total;
	}
	@Override
	public int getMplantRec(Criteria cri) {
		Connection conn = null;
		CallableStatement stmt = null;
		int total = 0;
		String search_name = null;
		//이름검색
		if(cri.getSearchField() != null && cri.getSearchField().equals("name")) {
			search_name = cri.getSearchText();
		}
		String sql="call p_mplant_total(?,?)";
		try {
			conn = ds.getConnection();
			stmt = conn.prepareCall(sql);
			stmt.setString(1, search_name);
			stmt.registerOutParameter(2, OracleTypes.INTEGER);
			stmt.executeQuery();
			total = stmt.getInt(2);
		} catch (SQLException e) {			
			e.printStackTrace();
		}	finally {
			resourceClose(conn, stmt);
		}	
		return total;
	}

	//---------------------------자원반납------------------------------------------------------------------------------
	private void resourceClose(Connection conn, PreparedStatement stmt) {
		try {
			if(stmt != null || conn != null) {
				stmt.close();
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		}
		
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














}