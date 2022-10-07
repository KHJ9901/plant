//package com.plant.controller.admin;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import service.MemberServiceImp;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Map;
//
//@WebServlet(urlPatterns = { "*.do" } )
//public class MemberController extends HttpServlet {
//	
//	private static final long serialVersionUID = 1L;
//	
//	private MemberServiceImp msi = new MemberServiceImp();
//       
//    public MemberController() {
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
//	private void doAction(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//		resp.setContentType("text/html; charset=utf8"); //http의 헤드정보임으로 가장 맨위에 있어야!!
//		req.setCharacterEncoding("utf-8");
//      
//		String uri = req.getRequestURI(); //mapping 정보를 추출하기 위한 uri와 path 정보를 불러와야 (*.do)
//		String path = req.getContextPath();
//		
//		//PrintWriter out = resp.getWriter();
//		//out.append("uri = " + uri + ", path = " + path);
//		// substr()
//		
//		String cmd = uri.substring(uri.lastIndexOf("/")+1);
//		// out.println(cmd);
//		// out.append(", c md = " + cmd);
//		
//		//out.println("아이디 값은 : " + id);
//		//out.println("비밀번호 값은 :" + pw);
//			
//		//login.do을 요청하면 MemberService의 login() 메소드를 호출하는 if문으로
//		if(cmd.equals("login.do")) {
//			String id = req.getParameter("id");
//			String pw = req.getParameter("pw");
//			String name = req.getParameter("name");
//			
//			Map<String, String> status = msi.login(id, pw);
//			switch(status.get("login")) {
//				case "ok" : //로그인 성공
//					//세션설정
//					HttpSession sess = req.getSession();
//						sess.setAttribute("sess_id", id);
//						sess.setAttribute("sess_name", status.get("name"));
//						
//						req.setAttribute("msg", "loginOk");
//						goView(req, resp, "/mgHome.jsp");
//						//resp.sendRedirect("/index.jsp?msg=loginOk");// 샌드리다이렉트 방법
//						
//						// setAttribute 회원가입, 로그인 등 상태값을 던져줘야 하는데, 
//						// 그걸 변수로 req.setAttribute("msg", "loginOk") 설정해서 담아 jsp 로 전달하는 것임
//					break;
//				default : //로그인 실패
//					//req.setAttribute("변수", "값"); //setAttribute가 변수를 설정해줌
//					req.setAttribute("msg", "loginFail");
//					//goView(req, resp, "/index.jsp?stat=1");
//			}
//			
//			goView(req, resp, "/");
//		} else if(cmd.equals("logout.do")) {
//			//세션설정
//			req.getSession().invalidate();
//			goView(req, resp, "/");
//			
//		} else if(cmd.equals("memRegForm.do")) {
//			goView(req, resp, "/member/memRegForm.jsp");
//			
//		} else if(cmd.equals("memberReg.do")) {
//			System.out.println("회원등록 요청");
//			msi.insert(req);
//			
//			req.setAttribute("msg", "memberOk");
//			goView(req, resp, "/");
//		} 
//	}
//		void goView(HttpServletRequest req, HttpServletResponse resp, String viewPage) throws ServletException, IOException {
//			RequestDispatcher rd = req.getRequestDispatcher(viewPage);// 보여줄 페이지를 jsp 로 페이지 던져주기만 함.(안돌려받음)
//			rd.forward(req, resp);
//		}
//}
