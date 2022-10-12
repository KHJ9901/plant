package com.plant.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.plant.common.LoginImpl;
import com.plant.dao.AdoptDao;
import com.plant.dto.Adopt;
import com.plant.dto.AdoptFile;
import com.plant.dto.Criteria;

@Service
public class AdoptServiceImp implements AdoptService {

	@Autowired
	AdoptDao adoptDao;
	
	private static final String CHARSET = "utf-8";
	
	@Override
	public List<Adopt> list(Criteria cri) {
		return adoptDao.adoptList(cri);
	}
	
	@Override
	public Adopt searchAdopt(String seqno) {
		return adoptDao.adetail(seqno);
	}
	
	@Override
	public String update(Adopt adopt, MultipartFile files) {
		AdoptFile adoptfile = null;
		AdoptFileService fileService = new AdoptFileServiceImp();
		adoptfile  = fileService.fileUpload(files);
		
		adoptDao.update(adopt, adoptfile);
		
		return adopt.getSeqno();
	}
	
	Adopt getFormParameter(FileItem item, Adopt adopt) {
		System.out.printf("필드이름: %s, 필드값:%s\n", item.getFieldName(), item.getString());
		if(item.getFieldName().equals("id")) {
			adopt.setId(item.getString());
		}
		if(item.getFieldName().equals("station")) {
			adopt.setStation(item.getString());
		}
		if(item.getFieldName().equals("content")) {
			adopt.setContent(item.getString());
		}
		if(item.getFieldName().equals("water")) {
			adopt.setWater(item.getString());
		}
		if(item.getFieldName().equals("place")) {
			adopt.setPlace(item.getString());
		}
		if(item.getFieldName().equals("temp")) {
			adopt.setTemp(item.getString());
		}
		if(item.getFieldName().equals("moist")) {
			adopt.setMoist(item.getString());
		}
		if(item.getFieldName().equals("pname")) {
			adopt.setPname(item.getString());
		}
		if(item.getFieldName().equals("mplant")) {
			adopt.setMplant_seqno(item.getString());
		}
		return adopt;
	}
	
	public int getTotalRec(Criteria criteria) {
		return adoptDao.getTotalRec(criteria);
	}
	
	@Override
	public void adoptdel(String seqno) {
		
		Map<String, String> map = adoptDao.adoptdel(seqno);
		
		String savefilename = map.get("savefilename");
		String filepath = map.get("filepath");
		String thumb_filename = map.get("thumb_filename");
		
		if(savefilename != null) {
			//첨부파일삭제
			File file = new File(filepath+savefilename);
			if(file.exists()) {
				file.delete();
			}
			
			//썸네일삭제
			if(thumb_filename != null) {
				File thumb_file = new File(filepath + "thumbnail/" + thumb_filename);
				if(thumb_file.exists()) {
					thumb_file.delete();
				}
			}
		}
	}

	@Override 
	public String insertAdopt(Adopt adopt, MultipartFile files) {
		
		AdoptFileService fileService = new AdoptFileServiceImp();
		return adoptDao.insert(adopt, fileService.fileUpload(files));
	}


	
	/*
	@Override
	public String insertAdopt(HttpServletRequest req, HttpServletResponse resp) {
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setDefaultCharset(CHARSET);
		ServletFileUpload upload = new ServletFileUpload(factory);
		 
		Adopt adopt = new Adopt();
		AdoptFile adoptfile = null;
		AdoptFileService adoptFileService = new AdoptFileServiceImp();
		
		try {
			List<FileItem> items = upload.parseRequest(req);
			for(FileItem item : items) {
				if(item.isFormField()) {
					adopt = getFormParameter(item, adopt);
					
				} else {
					adoptfile = adoptFileService.fileUpload(item);
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		LoginImpl login = (LoginImpl)req.getSession().getAttribute("loginUser");
		adopt.setId(login.getId());
		
		return adoptDao.insert(adopt, adoptfile);
	}
	*/
	
	/*
	@Override
	public String update(HttpServletRequest req, HttpServletResponse resp) {
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setDefaultCharset(CHARSET);
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		Adopt adopt = new Adopt();
		AdoptFile adoptfile = null;
		AdoptFileService adoptFileService = new AdoptFileServiceImp();
		
		try {
			List<FileItem> items = upload.parseRequest(req);
			for(FileItem item : items) {
				if(item.isFormField()) {
					adopt = getFormParameter(item, adopt);
				} else {
					adoptfile = adoptFileService.fileUpload(item);
				}
			}
		
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		LoginImpl login = (LoginImpl)req.getSession().getAttribute("loginUser");
		adopt.setId(login.getId());
		
		adoptDao.update(adopt, adoptfile);
		return adopt.getSeqno();
	}
	*/
	
	
	
	

}
