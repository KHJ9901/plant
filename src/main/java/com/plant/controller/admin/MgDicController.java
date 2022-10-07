//package com.plant.controller.admin;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import service.*;
//
//import java.io.IOException;
//import java.util.List;
//
//import dto.*;
//
//@WebServlet(urlPatterns = {"/mg/*"} )
//public class MgDicController extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	
//    public MgDicController() {
//        super();
//    }
//
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		doAction(req, resp);
//	}
//	
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		doAction(req, resp);
//	}
//
//	private void doAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String uri = req.getRequestURI();
//		String cmd = uri.substring(uri.lastIndexOf("/")+1);// 디렉토리 경로 지정할 필요없이 바로 .jsp 하면됨 , 마지막에 있는 슬러쉬
//		
//		MgDicService mds = new MgDicServiceImp();
//		
//		if(cmd.equals("mgDiction")) {
//			//게시판 리스트
//			List<MgDictionary> dic = mds.list();
//			req.setAttribute("dic", dic); //요청
//			goView(req, resp, "/dictionary/mgDictionary.jsp");
//
//		}	else if(cmd.equals("mplantList.mg")) {
//			//회원 식물 리스트
//				List<MgMplant> mplant = mds.mplantList();
//				req.setAttribute("mp", mplant); //요청
//				goView(req, resp, "/dictionary/mgMplant.jsp");
//
//		}	else if(cmd.equals("mgDicDetail")) {
//			//게시판 세부내용
//				String seqno = req.getParameter("seqno"); //boardDetail.bo?seqno= 이 seqno 임
//				
//				if(seqno == null) {
//					seqno = (String)req.getAttribute("seqno");
//				} 
//				req.setAttribute("dt", mds.dicDetail(seqno));
//				
//				String page = req.getParameter("page");
//				
//				if(page != null) {
//					goView(req, resp, "/dictionary/mgDicModify.jsp");
//				}	else {
//					goView(req, resp, "/dictionary/mgDicDetail.jsp");
//				}
//				
//		}	else if(cmd.equals("mgMpDetail")) {
//				//게시판 세부내용
//				String seqno = req.getParameter("seqno"); //boardDetail.bo?seqno= 이 seqno 임
//				
//				if(seqno == null) {
//					seqno = (String)req.getAttribute("seqno");
//				} 
//				
//				req.setAttribute("mpd", mds.mpDetail(seqno));
//				goView(req, resp, "/dictionary/mgMpDetail.jsp");
//			
//		}	else if(cmd.equals("mgDicRegForm")) {
//				goView(req, resp, "/dictionary/mgDicRegForm.jsp");
//				
//		}	else if(cmd.equals("mgDicReg")) {
//				req.setAttribute("seqno", mds.insertDiction(req, resp));
//				goView(req, resp, "/dictionary/mgDiction.mg");
//			
//		}	else if(cmd.equals("mgDicModify")) {
//				req.setAttribute("seqno", mds.update(req, resp));
//				goView(req, resp, "/dictionary/mgDicDetail.mg");
//			
//		}	else if(cmd.equals("mgDicSearch")) {
//				String keyword = req.getParameter("keyword");
//				
//				List<MgDictionary> search = mds.searchDic(keyword);
//				
//				req.setAttribute("dicsearch", search);
//				
//				goView(req, resp, "/dictionary/mgDictionarySearch.jsp");
//			
//		}	else if(cmd.equals("mgMpSearch")) {
//				String keyword = req.getParameter("keyword");
//				
//				List<MgMplant> search = mds.searchMplant(keyword);
//				
//				req.setAttribute("mpsearch", search);
//				
//				goView(req, resp, "/dictionary/mgMplantSearch.jsp");
//				
//		}
//			/*		} else if (cmd.equals("boardDetail.bo")) {
//			//게시판 세부내용 출력
//			
//			String seqno = req.getParameter("seqno"); //boardDetail.bo?seqno= 이 seqno 임
//			if(seqno == null) {
//				seqno = (String)req.getAttribute("seqno");
//			}
//			
//			req.setAttribute("board", bsi.searchBoard(seqno));// (변수이름, " " ) 여기서 만든메소드 => 서비스에서 호출
//			goView(req, resp, "/board/detail.jsp");
//			
//		}	else if (cmd.equals("bdWriteForm.bo")) {
//			goView(req, resp, "/board/bdWriteForm.jsp");
//			
//		}	else if(cmd.equals("bdWrite.bo")) {
//			req.setAttribute("seqno", bsi.insertBoard(req, resp));
//			goView(req, resp, "boardDetail.bo");
//		}*/
//		
//	}
//		void goView(HttpServletRequest req, HttpServletResponse resp, String viewPage) throws ServletException, IOException {
//			RequestDispatcher rd = req.getRequestDispatcher(viewPage);// 보여줄 페이지를 jsp 로 페이지 던져주기만 함.(안돌려받음)
//			rd.forward(req, resp);
//	}
//}
