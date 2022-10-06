<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="../common.jsp" %>
	<link rel="stylesheet" href="/css/adetail.css">
	<link rel="stylesheet" href="/css/adoptReview.css">
</head>

<body>
<!--
	<div class="headContainer">
		<div class="headCategory">
			<a href="/ao/adopt">분양</a>  
			<a href="/ro/review">분양후기</a>
		</div>
	</div>
	
	<div class="headContainer2">
		<div class="headNotice">
			<h1>분 양 후 기 디 테 일</h1>
			<div class="wrap">
			<a href="/ro/review" class="mybutton">뒤로가기</a>
			</div>
		</div>
	</div>-->	
	
<!-- 내용 -->
	<div class="write2">
		<c:set value="${review}" var="review" />
		<c:set value="${loginUser}" var="user" />
		
		<div class="modify">
		<c:if test="${user.id eq review.id}">
			<button class="myButton" onclick="location.href='/ro/reviewDetail?seqno=${review.seqno}&page=reviewModify'">수정</button> 
			<button class="myButton" onclick="del_confirm('${review.seqno}')">삭제</button>
		</c:if>
		</div>
		
		<script>
		function del_confirm(seqno){
			var rs = confirm('정말로 삭제하시겠습니까?');
			if(rs){
				location.href = "/ro/reviewDelete?seqno=" + seqno;
			}
		}
		</script>
		
		<table>
		<tr>
		<th>${review.id}</th>
		<th>${review.wdate}</th>
		<th>${review.count}</th>
		</tr>
		
		<tr>
		<td colspan="3">분양일 : ${review.sold}</td>
		</tr>
		
		<tr>
		<td colspan="3">${review.content}</td>
		</tr>
		</table>
	</div>

<%@include file = "../footer.jsp" %>
</body>
</html>