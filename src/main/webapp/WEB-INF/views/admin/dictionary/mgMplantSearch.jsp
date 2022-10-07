<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../mgHeader.jsp" %>

<%@ include file="../mgMenu.jsp" %>
<div class="row">
	<div class="leftcolumn">
		<div class="bdbox">
			<div class="subMenu">
				<div class="mgFreeBoard">
					<a href="mgDiction">사전 관리</a>
					<div>
						<a href="mgDiction">사전 전체목록</a>
						<a href="mplantList">회원이 등록한 식물 목록</a>
					</div>
				</div>
			</div>
			<div class="bdtitle">
				<h1>사전 관리</h1>
				<h3>회원이 등록한 식물 목록</h3>
				<select class="wholeCategory" onchange="#">
			  		<option value="식물이름">식물이름</option>
			  		<option value="작성자ID">작성자ID</option>
			  		<option value="작성자이름">작성자이름</option>
				</select>
				
				<div class="mgSearch">
					<form class="searchForm" action="mgMpSearch">
						<div class="searchText">
							<input class="searchInput" name="keyword" type="text" placeholder="검색해!">
		  					<div class="wrap">
								<input type="submit" class="mybutton">
							</div>
						</div>
					</form>
				</div>
			</div>
		
			<div class="bdbody">
				<div class="bdlist">
					<div class="top">
						<input type="checkbox" name="memselect" value=""> 모두선택
						<div class="plantNum">번호</div>
						<div class="plantName">식물이름</div>
						<div class="plantWdate">작성일</div>
						<div class="plantCount">조회수</div>
						<div class="memBdName">작성자이름</div>
						<div class="memBdId">작성자ID</div>
					</div>
							
					<c:forEach items="${mpsearch}" var="mp">
						<div class="bdBottom">
							<div>
								<input class="bdBottomBt" type="checkbox" name="memselect" value="">
							</div>
							<div class="plantNum">${mp.mplant_seqno}</div>
							<div class="plantName" id="post">${mp.name}</div>
							<div class="plantWdate">${mp.wdate}</div>
							<div class="plantCount">${mp.count}</div>
							<div class="memBdName">${mp.memname}</div>
							<div class="memBdId">${mp.id}</div>
						</div>
					</c:forEach>
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



