<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<c:set value="${adopt}" var="adopt" />
	<form method="post" enctype="multipart/form-data" action="/adopt/aUpdate" onsubmit="return check(this)">
	<input type="hidden" name="seqno" value="${adopt.seqno}">
		
		<table>
		<tr>
		<th>분양 거래 장소</th>
		<td><input type="text" style="width:100%" name="station" value="${adopt.station}"></td>
		</tr>
		
		<tr>
		<th>내용</th>
		<td><textarea style="width:100%" cols="100" rows="20" name="content">${adopt.content}</textarea></td>
		</tr>
		
		<!-- <tr>
		<th>내가 등록한 식물</th>
		<td><input type="text" style="width:100%" name="mplant" value="${adopt.mplant_seqno}"></td>
		</tr> -->
		
		<tr>
		<th>pname</th>
		<td><input type="text" style="width:100%" name="pname" value="${adopt.pname}"></td>
		</tr>
		
		<tr>
		<th>water</th>
		<td><input type="text" style="width:100%" name="water" value="${adopt.water}"></td>
		</tr>
		
		<tr>
		<th>place</th>
		<td><input type="text" style="width:100%" name="place" value="${adopt.place}"></td>
		</tr>
		
		<tr>
		<th>temp</th>
		<td><input type="text" style="width:100%" name="temp" value="${adopt.temp}"></td>
		</tr>
		
		<tr>
		<th>moist</th>
		<td><input type="text" style="width:100%" name="moist" value="${adopt.moist}"></td>
		</tr>
		
		<!-- <tr>
		<th>사진첨부</th>
		<td><input type="file" name="filename"></td>
		</tr> -->
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