<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="../common.jsp" %>
	<link rel="stylesheet" href="/css/write.css">
	<link rel="stylesheet" href="/css/adoptReview.css">
</head>

<body>
<div class="write">
	<c:set value="${review}" var="review" />
	<form method="post" enctype="multipart/form-data" action="/review/rUpdate" onsubmit="return check(this)">
	<input type="hidden" name="seqno" value="${review.seqno}">
		<table>
<%-- 			<tr>
			<th>분양번호</th>
			<td><input type="hidden" style="width:100%" name="apply_seqno" value="${review.apply_seqno}"></td>
			</tr> --%>
			<tr>
			<th>내용</th>
			<td><textarea style="width:100%" cols="100" rows="20" name="content">${review.content}</textarea></td>
			</tr>
		</table>
		
		<div class="modify">
		<input type="submit" class="myButton" value="수정">
		<input type="reset" class="myButton" onclick="history.back(-1);" value="취소">
		</div>
	</form>
</div>

<%@include file="../footer.jsp"%>
</body>
</html>