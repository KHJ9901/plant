<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="../common.jsp" %>
	<link rel="stylesheet" href="/css/write.css">
	<link rel="stylesheet" href="/css/adoptReview.css">
</head>

<body>
<div class="write">
	<c:set value="${loginUser}" var="user"/>
	<form method="post" enctype="multipart/form-data" action="/adopt/aRegister" onsubmit="return check(this)">
	<input type="hidden" name="id" value = "${user.id}"> 
		
		<table>
		<tr>
		<th>분양 거래 장소</th>
		<td><input type="text" style="width:100%" name="station"></td>
		</tr>
		
		<tr>
		<th>내용</th>
		<td><textarea style="width:100%" cols="100" rows="20" name="content"></textarea></td>
		</tr>
		
		
		<!--<tr>
		<th>내가 등록한 식물</th>
		<td><input type="hidden" style="width:100%" name="mplant"></td>
		</tr>-->
		
		<tr>
		<th>식물 이름</th>
		<td><input type="text" style="width:100%" name="pname"></td>
		</tr>
		
		<tr>
		<th>수분?</th>
		<td><input type="text" style="width:100%" name="water"></td>
		</tr>
		
		<tr>
		<th>장소?</th>
		<td><input type="text" style="width:100%" name="place"></td>
		</tr>
		
		<tr>
		<th>온도?</th>
		<td><input type="text" style="width:100%" name="temp"></td>
		</tr>
		
		<tr>
		<th>습도?</th>
		<td><input type="text" style="width:100%" name="moist"></td>
		</tr>
		
		<!-- <tr>
		<th>사진첨부</th>
		<td><input type="file" name="filename"></td>
		</tr> -->
	</table>
		
		<div class="modify">
		<input type="submit" class="myButton" value="등록">
		<input type="reset" class="myButton" onclick="history.back(-1);" value="취소">
		</div>
	</form>
</div>

<%@include file="../footer.jsp"%>
</body>
</html>