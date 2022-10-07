<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../mgHeader.jsp" %>
<%@ include file="../mgMenu.jsp" %>

<div class="row">
	<div class="leftcolumn">
		<div class="bdbox">
			<div class="subMenu">
				<div class="mgFreeBoard">
					<a href="mgDiction">식물 관리</a>
					<div>
						<a href="mgDiction">식물 전체목록</a>
						<a href="mplantList">회원이 등록한 식물 목록</a>
					</div>
				</div>
			</div>
			<div class="bdtitle">
				<h1>사전 관리</h1>
				<h3>전체목록 목록</h3>
				<div class="membdbt">
					<button class="membdbt1" type="button" onclick="location.href='mgDicRegForm';">등록</button>
					<button class="membdbt2" type="button" onclick="">삭제</button>
				</div>
				<select class="wholeCategory" onchange="#">
			  		<option value="실내">실내</option>
			  		<option value="야외">야외</option>
				</select>
				<select class="wholeCategory" onchange="#">
			  		<option value="제목">식물이름</option>
				</select>
				
				<div class="mgSearch">
					<form class="searchForm" action="mgDicSearch">
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
					</div>
							
					<c:forEach items="${dic}" var="dic">
						<div class="bdBottom">
								<div>
									<input class="bdBottomBt" type="checkbox" name="dicSelect" value="n">
								</div>
								<div class="plantNum">${dic.seqno}</div>
								<div class="plantName" id="post">
									<a href="mgDicDetail?seqno=${dic.seqno}&page=modify">
										${dic.kname}
									</a>
								</div>
								<div class="plantWdate">${dic.wdate}</div>
								<div class="plantCount">${dic.count}</div>
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



