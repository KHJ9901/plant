<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="../common.jsp" %>
	<link rel="stylesheet" href="/css/adopt.css">
</head>

<body>
<div class="wholePage">
<!-- 메뉴 -->
	<div class="headContainer">
		<div class="headCategory">
			<%@ include file  = "../adopt/adoptMenu.jsp" %>
		</div>
	</div>

<!-- 박스 -->	
	<div class="headContainer2">
		<div class="headNotice">
			<h1>분 양</h1>
			<c:if test="${loginuser != null}">
			<div class="wrap">
			<a href="/adopt/aRegForm" class="mybutton">등록</a>
			</div>
			</c:if>			
		</div>
	</div>

<!-- 내용 -->
	<div class="adoptContainer">
		<ul class="cf">
		<div class="search">
			<p class="total">전체 게시글 &nbsp;${pageMaker.total}</p>
			<form name="search" method="POST" action="/adopt/list">
				<input type="hidden" name="currentPage" value="${pageMaker.cri.currentPage}">
				
				<select name="searchField">
				  <option value="content" 
				  <c:if test="${pageMaker.cri.searchField == 'content'}">selected</c:if>>내용</option>		
				  <option value="id"
				  <c:if test="${pageMaker.cri.searchField == 'id'}">selected</c:if>>아이디</option>
				</select>
			
				<input type="text" name="searchText" placeholder="search..." 
					   value="${pageMaker.cri.searchText}">	
				<input type="submit" value="검색">	
				
				<%-- <select name="rowPerpage" onchange="goAction()" >
					<c:forEach var="i" begin="5" end="20" step="5">
					<option value="${i}"
					 <c:if test="${i == pageMaker.cri.rowPerpage}">selected</c:if>
					 >${i}개씩</option>
					</c:forEach>
				</select> --%>
			</form>
		</div>
		
		<script>
		function goAction(){
			document.forms["search"].submit();
		}
		</script>	
		
		<c:forEach items="${adopt}" var="adopt">
		<li class="adoptList">
			<div class="aboptBox">
				<a href="/adopt/adetail?seqno=${adopt.seqno}">
			 	<img src="../img/plant2.jpg" alt="plant"></a>
				<p class="desc"> ${adopt.id}</p>
				<p class="desc"> ${adopt.wdate}</p>					
			</div>
		</li>
		</c:forEach>	

		</ul>
	</div>
	

	<div class="pageNum">
		<div class="pageNumDetail">
			<c:if test="${pageMaker.prev}">
				<a href="/adopt/list?currentPage=${pageMaker.startPage-1}&rowPerpage=${pageMaker.cri.rowPerpage}">&laquo;</a>						
			</c:if>
				  
			<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
			<a href="/adopt/list?currentPage=${num}&rowPerpage=${pageMaker.cri.rowPerpage}"
				class="${pageMaker.cri.currentPage == num? "active" : " " }">${num}</a>
			</c:forEach>
			
			<c:if test="${pageMaker.next}">
				<a href="/adopt/list?currentPage=${pageMaker.endPage+1}&rowPerpage=${pageMaker.cri.rowPerpage}">&raquo;</a>
			</c:if>
		</div>
	</div>

</div>

<%@include file = "../footer.jsp" %>
</body>
</html> 