<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html>
<html>
<head>
	<%@ include file="../common.jsp" %>
	<link rel="stylesheet" href="/css/adoptReview.css">
	<link rel="stylesheet" href="/css/ask.css">
</head>

<body>
<div class="wholePage">
	<div class="headContainer">
		<div class="headCategory">
			<a href="list">분양</a>  
			<a href="/ro/review">분양후기</a>
		</div>
	</div>
	
	<div class="headContainer2">
		<div class="headNotice">
			<h1>분 양 후 기</h1>
			<c:if test="${loginuser != null}">
			<div class="wrap">
			<a href="/ro/reviewRegForm" class="mybutton">등록</a>
			</div>
			</c:if>			
		</div>
	</div>

	<div class="reviewContainer">
		<ul class="ulline">
		
		<form name="search" method="POST" action="/ro/review">
			<input type="hidden" name="currentPage" value="${pageMaker.cri.currentPage}">
			
			<select name="search_field">
			  <option value="title" 
			  <c:if test="${pageMaker.cri.searchField == 'title'}">selected</c:if>>내용</option>		
			  <option value="name"
			  <c:if test="${pageMaker.cri.searchField == 'name'}">selected</c:if>>아이디</option>
			</select>
		
			<input type="text" name="search_text" placeholder="search..." value="${pageMaker.cri.searchText}">	
			<input type="submit" value="검색">	
			
			<select name="rowPerpage" onchange="goAction()" >
			<c:forEach var="i" begin="5" end="40" step="5">
			<option value="${i}"
			 <c:if test="${i == pageMaker.cri.rowPerpage}">selected</c:if>
			 >${i}개씩</option>
			</c:forEach>
			</select>		
		</form>	
		
		<script>
		function goAction(){
			document.forms["search"].submit();
		}
		</script>
		
		<c:forEach items="${review}" var="review">
		<li class="ak_item">
			<div class="li_item">
				<p>${review.id}</p>
				<p><a href="/ro/reviewDetail?seqno=${review.seqno}">${review.content}</a></p>
				<p class="li_status">
				<span class="li_date">${review.wdate}</span>
				<span class="li_date2">조회수${review.count}</span> 
				</p>
			</div>
		</li>
		</c:forEach>
			
		</ul>
	</div>

	<p>총 레코드 갯수 : ${pageMaker.total}</p>
	<div class="pageNum">
		<div class="pageNumDetail">
			<c:if test="${pageMaker.prev}">
				<a href="/ro/review?currentPage=${pageMaker.startPage-1}&rowPerpage=${pageMaker.cri.rowPerpage}">&laquo;</a>						
			</c:if>
				  
			<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
			<a href="/ro/review?currentPage=${num}&rowPerpage=${pageMaker.cri.rowPerpage}"
				class="${pageMaker.cri.currentPage == num? "active" : " " }">${num}</a>
			</c:forEach>
			
			<c:if test="${pageMaker.next}">
				<a href="/ro/review?currentPage=${pageMaker.endPage+1}&rowPerpage=${pageMaker.cri.rowPerpage}">&raquo;</a>
			</c:if>
		</div>
	</div>
	
</div>

<%@include file = "../footer.jsp" %>
</body>
</html>