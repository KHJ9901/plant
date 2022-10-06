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
<div class="asd_page_container">
	<div class="asd_cotainner">
		<div class="asd_con_detail">
			<section class="asd_detail_title">
				<h1>분 양 후 기</h1>
			</section>
			<c:set value="${review}" var="review" />
			<c:set value="${loginUser}" var="user" />
			<section class="asd_detail_form">
				<div class="asd_main_title">
					<div class="asd_group">
						<span class="asd_title">${review.id}</span>
					</div>
					
					<div class="asd_group">				
						<span class="asd_date">${review.wdate}</span>
						<span class="asd_title">${review.count}</span>
					</div>
				</div>
				
				<hr class="asd_line">
				<div class="asd_content">${review.content}</div>
				<div class="asd_content">${review.sold}</div>

				<div class="asd_report">
					<c:if test="${user.id eq review.id}">
						<button class="myButton" onclick="location.href='/review/rDetail?seqno=${review.seqno}&page=rModify'">수정</button> 
						<button class="myButton" onclick="del_confirm('${review.seqno}')">삭제</button>
					</c:if>			
				</div>
			</section>
		</div>
	</div>
</div>

<%@include file = "../footer.jsp" %>

<script>
function del_confirm(seqno){
	var rs = confirm('정말로 삭제하시겠습니까?');
	if(rs){
		location.href = "/review/reviewDelete?seqno=" + seqno;
	}
}
</script>
</body>
</html>