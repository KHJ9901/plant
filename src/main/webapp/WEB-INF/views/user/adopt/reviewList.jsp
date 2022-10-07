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
			<%@ include file  = "../adopt/adoptMenu.jsp" %>
		</div>
	</div>
<!-- 박스 -->		
	<div class="headContainer2">
		<div class="headNotice">
			<h1>분 양 후 기</h1>
			<c:if test="${loginuser != null}">
			<div class="wrap">
			<a href="/review/rRegForm" class="mybutton">등록</a>
			</div>
			</c:if>			
		</div>
	</div>
<!-- 내용 -->
	<div class="reviewContainer">
		<ul class="cf">
		<div class="search">
			<p class="total">전체 게시글 &nbsp;${pageMaker.total}</p>
			<form name="search" method="POST" action="/review/list">
				<input type="hidden" name="currentPage" value="${pageMaker.cri.currentPage}">
				
				<select name="search_field">
				  <option value="content" 
				  <c:if test="${pageMaker.cri.searchField == 'content'}">selected</c:if>>내용</option>		
				  <option value="id"
				  <c:if test="${pageMaker.cri.searchField == 'id'}">selected</c:if>>아이디</option>
				</select>
			
				<input type="text" name="search_text" placeholder="search..." 
					   value="${pageMaker.cri.searchText}">	
				<input type="submit" value="검색">
				
				<select name="rowPerpage" onchange="goAction()" >
					<c:forEach var="i" begin="5" end="30" step="5">
					<option value="${i}"
					 <c:if test="${i == pageMaker.cri.rowPerpage}">selected</c:if>
					 >${i}개씩</option>
					</c:forEach>
				</select>		
			</form>	
		</div>
		
		<script>
		function goAction(){
			document.forms["search"].submit();
		}
		</script>
		
		<c:forEach items="${review}" var="review">
		<li class="ak_item">
			<div class="li_item">
				<p>${review.id}</p>
				<p><a href="/review/rdetail?seqno=${review.seqno}">${review.content}</a></p>
				<p class="li_status">
				<span class="li_date">${review.wdate}</span>
				<span class="li_date2">조회수${review.count}</span> 
				</p>
			</div>
		</li>
		</c:forEach>
			
		</ul>
	</div>

	<div class="pageNum">
		<div class="pageNumDetail">
			<c:if test="${pageMaker.prev}">
				<a href="/review/list?currentPage=${pageMaker.startPage-1}&rowPerpage=${pageMaker.cri.rowPerpage}">&laquo;</a>						
			</c:if>
				  
			<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
			<a href="/review/list?currentPage=${num}&rowPerpage=${pageMaker.cri.rowPerpage}"
				class="${pageMaker.cri.currentPage == num? "active" : " " }">${num}</a>
			</c:forEach>
			
			<c:if test="${pageMaker.next}">
				<a href="/review/list?currentPage=${pageMaker.endPage+1}&rowPerpage=${pageMaker.cri.rowPerpage}">&raquo;</a>
			</c:if>
		</div>
	</div>
	
</div>

<%@include file = "../footer.jsp" %>
</body>
</html>