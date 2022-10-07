<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../mgHeader.jsp" %>

<%@ include file="../mgMenu.jsp" %>

<div class="row">
	<div class="leftcolumn">
		<div class="bdbox">
			<div class="subMenu">
				<div class="mgFreeBoard">
					<a href="adoptBoard.jsp">분양 관리</a>
					<div>
						<a href="adoptBoard.jsp">분양 등록 목록</a>
						<a href="adoptRequestBoard.jsp">분양 신청 목록</a>
						<a href="adoptReviewBoard.jsp">분양 후기 목록</a>
					</div>
				</div>
			</div>
			<div class="bdtitle">
				<h1>분양 관리</h1>
				<h3>분양 등록 목록</h3>
				<div class="membdbt">
					<button class="membdbt1" type="button" onclick="">등록</button>
					<button class="membdbt2" type="button" onclick="">삭제</button>
					<button class="membdbt3" type="button" onclick="">수정</button>
				</div>
				<select class="wholeCategory" onchange="#">
			  		<option value="실내">실내</option>
			  		<option value="야외">야외</option>
				</select>
				<select class="wholeCategory" onchange="#">
			  		<option value="식물이름">식물이름</option>
			  		<option value="등록일">등록일</option>
			  		<option value="등록자이름">등록자이름</option>
			  		<option value="등록자ID">등록자ID</option>
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
						<div class="plantNum">번호</div>
						<div class="plantName">식물이름</div>
						<div class="plantWdate">등록일</div>
						<div class="plantCount">조회수</div>
						<div class="memBdName">등록자이름</div>
						<div class="memBdId">등록자ID</div>
					</div>
							
					<div class="bdBottom">
						<div>
							<input class="bdBottomBt" type="membdcheckbox" name="memselect" value="">
						</div>
						<div class="plantNum">1</div>
						<div class="plantName" id="post">첫번째 식물</div>
						<div class="plantWdate">2022-08-01</div>
						<div class="plantCount">22</div>
						<div class="memBdName">관리자</div>
						<div class="memBdId">planner22</div>
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



