package com.plant.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plant.service.*;

@Controller
@RequestMapping(value="/file/")
public class DicFileController {
   
	@Autowired
	DicImgFileService fileService;

	private void doAction(HttpServletRequest request, HttpServletResponse response)  {
		String uri = request.getRequestURI();
		String cmd = uri.substring(uri.lastIndexOf("/")+1);
		
		switch(cmd) {
			case "fileDel" :		
				String no = request.getParameter("no");
				String savefilename = request.getParameter("savefilename");
				String filepath = request.getParameter("filepath");
				String thumb_filename = request.getParameter("thumb_filename");
				
				//System.out.println("-------파일삭제");
				//System.out.println("no : " + no + ",savefilename :" + savefilename);
				//System.out.println("filepaht: " + filepath + ",thumb_filename:" + thumb_filename);
				int rs = fileService.mpImgDelete(no, savefilename, filepath, thumb_filename);
			
				System.out.println("파일삭제결과:" + rs);
				
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






