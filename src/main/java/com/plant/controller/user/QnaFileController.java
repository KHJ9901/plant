package com.plant.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plant.service.QnaFileServicImp;
import com.plant.service.QnaFileService;



@WebServlet("/qfile/*")
public class QnaFileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	QnaFileService fileservice = new QnaFileServicImp();
 
    public QnaFileController() {
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
		System.out.println(cmd);
		switch(cmd) {
			case "fileDown" :
				fileservice.fileDown(request, response);
				
				break;
			case "fileDel" :
				String no = request.getParameter("no");
				String savefile = request.getParameter("savaFileName");
				String location = request.getParameter("filePath");
				String thumbnail = request.getParameter("thumb_file");
				
//				System.out.println("no:" +no+ ",savaFileName:" +savefilename);
//				System.out.println("filePath:"+filepath+",thumb_file:"+thumb_file);
				int rs = fileservice.delete(no, savefile, location, thumbnail);
				
				System.out.println("파일삭제결과:" +rs);
			
				
				PrintWriter out;
				try {
					out = response.getWriter();
					out.print(rs);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;
			default :
		}
	}

}
