package com.plant.service;

import java.util.List;

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
import com.plant.dao.QnaDao;
import com.plant.dto.Criteria;
import com.plant.dto.Qna;
import com.plant.dto.Qna_Img;

@Service
public class QnaServiceImp implements QnaService {
	
	@Autowired
	QnaDao askDao;
	
	private static final String CHARSET = "UTF_8"; 
	
	@Override
	public List<Qna> list(Criteria cri) {
		return askDao.askList(cri);
	}

	@Override
	public Qna searchAsk(String seqno) {
		return askDao.askDetail(seqno);
	}

//	@Override
//	public String Qnanew(HttpServletRequest req, HttpServletResponse resp) {
//		DiskFileItemFactory factory = new DiskFileItemFactory();
//		factory.setDefaultCharset(CHARSET);
//		ServletFileUpload upload = new ServletFileUpload(factory);
//		
//		Qna qna = new Qna();
//		Qna_Img qnaimg = null;
//		QnaFileService fileService = new  QnaFileServicImp();
//		
//		try {
//			List<FileItem> items = upload.parseRequest(req);
//			for(FileItem item : items) {
//				if(item.isFormField()) {
//					qna = getFormPararmeter(item, qna);
//				} else {
//					qnaimg = fileService.fileUpload(item);
//				}
//			}			
//		} catch (FileUploadException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		LoginImpl login = (LoginImpl)req.getSession().getAttribute("loginUser");	
//		qna.setId(login.getId());
//		
//		return askDao.insert(qna, qnaimg);
//	}
	
	
	public String update(Qna qna, MultipartFile filename) {
		Qna_Img qnaimg = null;
		QnaFileService fileService = new  QnaFileServicImp();
		
			qnaimg = fileService.fileUpload(filename);
			
		askDao.update(qna, qnaimg);
		return qna.getSeqno();
	}
	
	Qna getFormPararmeter(FileItem item, Qna qna) {
		if(item.getFieldName().equals("content")) {
			qna.setContent(item.getString());
		}
		if(item.getFieldName().equals("seqno")) {
			qna.setSeqno(item.getString());
		}
			
		return qna;
	}

	public void reply(HttpServletRequest req) {
		askDao.reply(req);
	}
	
	public void qnadel(String seqno) {
		askDao.qnadel(seqno);
	}
	
	public int getTotalRec(Criteria cri) {
		return askDao.getTotalRec(cri);
	}

	@Override
	public void asklike(HttpServletRequest req) {
		askDao.asklike(req);
		
	}

	@Override
	public String insert(Qna qna, MultipartFile qnaimg) {
		QnaFileService fileService = new QnaFileServicImp();
		return askDao.insert(qna, fileService.fileUpload(qnaimg));
	}

	
	
}
