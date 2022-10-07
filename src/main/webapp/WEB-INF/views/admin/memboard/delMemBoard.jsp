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
			<h1>탈퇴회원 목록</h1>
			<div class="membdbt">
				<button class="membdbt2" type="button" onclick="">삭제</button>
			</div>
			<select class="wholeCategory" onchange="#">
		  		<option value="회원번호">회원번호</option>
		  		<option value="가입날짜">가입날짜</option>
		  		<option value="탈퇴날짜">탈퇴날짜</option>
		  		<option value="아이디">아이디</option>
		  		<option value="이름">이름</option>
		  		<option value="연락처">연락처</option>
			</select>
			<div class="mgSearch">
				<input class="mgInput" type="text" placeholder="검색">
				<button class="mgSearchBt" type="button" onclick="">검색</button>
			</div>
		</div>
	
		<div class="bdbody">
			<div class="bdlist">
					<div class="top">
						<input type="checkbox" name="memselect" value=""> 모두선택
						<div class="delMemNum">회원번호</div>
						<div class="delMemWdate">가입날짜</div>
						<div class="delMemDdate">탈퇴날짜</div>
						<div class="delMemId">아이디</div>
						<div class="delMemName">이름</div>
						<div class="delMemPhone">연락처</div>
					</div>
					
					<div class="bdBottom">
						<div>
							<input class="bdBottomBt" type="membdcheckbox" name="memselect" value="">
						</div>
						<div class="delMemNum">13</div>
						<div class="delMemWdate" id="post"> 2022-08-01</div>
						<div class="delMemDdate">2022-08-05</div>
						<div class="delMemId">planner1234</div>
						<div class="delMemName">관리자</div>
						<div class="delMemPhone">01012345678</div>
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



