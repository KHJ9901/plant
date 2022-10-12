<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file ="../common.jsp" %>

<link rel="stylesheet" href="/css/board.css">

<div class="wholePage">
	<div class="headContainer">
		<div class="headCategory">
			<a href="/qna/qnalist">질문·답변</a>  
			<a href="/board/boardlist">자유게시판</a>
		</div>
	</div>
	
	<div class="headContainer2">
		<div class="headNotice">
			<h1>자유게시판</h1>
			<div class="headSubTitle">자유게시판입니다!</div>
			
			<!-- 버튼이면 class="wrap" -->
			<!-- 검색이면 class="searchForm" -->
			
			<c:if test="${loginuser != null}">
				<div class="wrap">
				<a href="/board/boardnew" class="mybutton">게시글등록</a>
				</div>
			</c:if>
		</div>
	</div>
	<div class="search">
	<form name="search" method="POST" action="/board/boardlist">
		<input type="hidden" name="currentPage" value="${pageMaker.cri.currentPage}">
		
		<select name = "search_field">
			<option value="title" 
			<c:if test="${pageMaker.cri.searchField == 'title'}">selected</c:if>>제목</option>
			<option value="name"
			<c:if test="${pageMaker.cri.searchField == 'name'}">selected</c:if>>이름</option>
		</select>
		
		<input type="text" name="search_text" placeholder="Search..."
		value="${pageMaker.cri.searchText}">
		            		
		<input type="button" value="검색" onclick="document.forms['search'].submit()">
       	
       	<!-- 페이지당 레코드수 -->
		<select name="rowPerpage" onchange="goAction()">
			<c:forEach var="i" begin="5" end="40" step="5">
				<option value="${i}" <c:if test="${i == pageMaker.cri.rowPerpage}"> selected </c:if> >${i}개</option>
			</c:forEach>
		</select>
	</form>
	</div>
	<div class="bodyContainer">
	<table>
		<tr>
			<th class="adopt_no">순번</th>
			<th class="adopt_content">제목</th>
			<th class="adopt_id">작성자</th>	
			<th class="adopt_data">작성일</th>
			<th class="adopt_click">조회수</th>
		</tr>
		<c:forEach items="${board}" var="board">	
		<tr>
			<td class="re_no">${board.rn}</td>
			<td class="re_content"><a href="/board/detail?seqno=${board.seqno}">${board.title}</a></td>
			<td class="re_id">${board.name}</td>	
			<td class="re_data">${board.wdate}</td>
			<td class="re_click">${board.count}</td>
		</tr>
		</c:forEach>
	</table>
	</div>

	<div class="pagination">
		<c:if test="${pageMaker.prev}">
		 <a href="/bo/board?currentPage=${pageMaker.startPage-1}&rowPerpage=${pageMaker.cri.rowPerpage}">&laquo;</a>						
		</c:if>
		  
	    <c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
	 	  <a href="/board/boardlist?currentPage=${num}&rowPerpage=${pageMaker.cri.rowPerpage}"
	 	  class="${pageMaker.cri.currentPage == num? "active" : " " }">${num}</a>
	    </c:forEach>
		
		<c:if test="${pageMaker.next}">
		 <a href="/board/boardlist?currentPage=${pageMaker.endPage+1}&rowPerpage=${pageMaker.cri.rowPerpage}">&raquo;</a>
		</c:if>
	</div>
</div>

<script>
function goAction(){
	document.forms["search"].submit()
}
</script>	

<%@include file = "../footer.jsp" %>