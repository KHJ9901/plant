package com.plant.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plant.service.AdoptFileService;
import com.plant.service.AdoptFileServiceImp;

@WebServlet({ "/file/*" })
public class AdoptFileController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	AdoptFileService adoptFileService= new AdoptFileServiceImp();
	
	public AdoptFileController() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	
	private void doAction(HttpServletRequest request, HttpServletResponse response) {
		
		String uri = request.getRequestURI();
		String cmd = uri.substring(uri.lastIndexOf("/")+1);
		
		switch(cmd) {
		case"fileDown":
			adoptFileService.fileDown(request, response);
			break;
		case "fileDel":
			String no = request.getParameter("no");
			String savefilename = request.getParameter("savefilename");
			String filepath = request.getParameter("filepath");
			String thumb_filename = request.getParameter("thumb_filename");
			
			//System.out.println("no:"+no+"savefilename:"+savefilename);
			//System.out.println("filepath:"+filepath+"thumb_filename:"+thumb_file);
			int rs = adoptFileService.delete(no, savefilename, filepath, thumb_filename);
			
			System.out.println("파일삭제결과:"+rs);
			
			PrintWriter out;
			try {
				out = response.getWriter();
				out.print(rs);
			} catch (IOException e) {
				e.printStackTrace();
			}
				
				break;
			default:
		}
		
	}
	
	
	
}
