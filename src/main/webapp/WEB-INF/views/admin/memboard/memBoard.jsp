<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../mgHeader.jsp" %>

<%@ include file="../mgMenu.jsp" %>
<div class="row">
  <div class="leftcolumn">
    <!-- <div class="card"> -->
	<div class="bdbox">
		<div class="subMenu">
			<a href="memBoard.jsp">회원관리</a>
			<a href="delMemBoard.jsp">탈퇴회원관리</a>
		</div>
		<div class="bdtitle">
			<h1>회원목록</h1>
			<div class="mgSearch">
				<input class="mgInput" type="text" placeholder="검색">
				<button class="mgSearchBt" type="button" onclick="">검색</button>
			</div>
		</div>
	
		<div class="bdbody">
			<div class="bdlist">
					<div class="top">
						<input type="checkbox" name="memselect" value=""> 모두선택
						<div class="memBdNum">회원번호</div>
						<div class="memBdWdate">가입날짜</div>
						<div class="memBdId">아이디</div>
						<div class="memBdName">이름</div>
						<div class="memBdPhone">연락처</div>
					</div>
					
					<div class="bdBottom">
						<div>
							<input class="bdBottomBt" type="membdcheckbox" name="memselect" value="">
						</div>
						<div class="memBdNum">13</div>
						<div class="memBdWdate" id="post">2022-08-01</div>
						<div class="memBdId">planner1234</div>
						<div class="memBdName">관리자</div>
						<div class="memBdPhone">01012345678</div>
					</div>
		
			</div>
			<div class="bdpage">
				<a href="#" class="bt_prev"><</a>
				<a href="#" class="num">1</a>
				<a href="board2.html" class="num">2</a>
				<a href="board2.html" class="bt_next">></a>
			</div>
		</div>	
    </div>
  </div>
</div>

<%@ include file="../mgFooter.jsp" %>

</body>
</html>



