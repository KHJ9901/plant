<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file = "../common.jsp" %>

<%@include file = "../menu.jsp" %>

<link rel="stylesheet" href="/css/write.css">

<div class="write">
	<div>
		<c:set value="${loginUser}" var="user"/>
		<form method="post" action="write" onsubmit="return check(this)">
			<input type="hidden" name="id" value = "${user.id}"> 
			<table>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" style="width:100%"></td>
			</tr>
			
			<tr>
				<th>내용</th>
				<td><textarea style="width:100%" cols="100" rows="30" name="content"></textarea></td>
			</tr>
			
			<tr>
				<th>파일
				<input type="file" name="chooseFile">
				</th>
			</tr>

				<div class="write2">
					<div class="write3">
						<input type="submit" class="myButton" value="등록">
						<input type="reset" class="myButton" value="취소">
					</div>
				</div>
			</table>
		</form>
	</div>
</div>

<%@include file="../footer.jsp"%>