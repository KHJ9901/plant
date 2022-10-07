<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../mgHeader.jsp" %>

<%@ include file="../mgMenu.jsp" %>


<div class="row">
  <div class="leftcolumn">
	<div class="bdbox">
		<div class="subMenu">
			<div class="mgFreeBoard">
				<a href="freeBoard.jsp">자유게시판 관리</a>
				<div>
					<a href="freeBoard.jsp">등록된글 목록</a>
				</div>
			</div>
			<div class="mgQuestionBoard">
				<a href="questionBoard.jsp">질문란 관리</a>
				<div>
					<a href="questionBoard.jsp">등록된 질문 및 답변 목록</a>
				</div>
			</div>
			<div class="mgJournalBoard">
				<a href="journalBoard.jsp">식물일기 관리</a>
				<div>
					<a href="journalBoard.jsp">등록된 일기 목록</a>
				</div>
			</div>
		</div>
		<div class="bdtitle">
		
			<h1>자유게시판 관리</h1>
			<h3>등록된글 목록</h3>
			<div class="membdbt">
				<button class="membdbt1" type="button" onclick="">등록</button>
				<button class="membdbt2" type="button" onclick="">삭제</button>
				<button class="membdbt3" type="button" onclick="">수정</button>
			</div>
			<select class="wholeCategory" onchange="#">
			  		<option value="제목">제목</option>
			  		<option value="작성일">작성일</option>
			  		<option value="작성자ID">작성자이름</option>
			  		<option value="작성자이름">작성자ID</option>
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
						<div class="memBdNum">번호</div>
						<div class="memBdTitle">제목</div>
						<div class="memBdWdate">작성일</div>
						<div class="memBdCount">조회수</div>
						<div class="memBdName">작성자이름</div>
						<div class="memBdId">작성자ID</div>
					</div>
					
					<div class="bdBottom">
						<div>
							<input class="bdBottomBt" type="membdcheckbox" name="memselect" value="">
						</div>
						<div class="memBdNum">123</div>
						<div class="memBdTitle">글 제목란</div>
						<div class="memBdWdate">2022-08-01</div>
						<div class="memBdCount">33</div>
						<div class="memBdName">식물플래너</div>
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



