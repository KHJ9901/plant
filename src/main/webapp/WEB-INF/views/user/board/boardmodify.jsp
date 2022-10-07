<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file = "../common.jsp" %>

<%@include file = "../menu.jsp" %>

<link rel="stylesheet" href="/css/write.css">

<div class="write">
	<div>
		<form method="post" action="/board/boardUpdate" onsubmit="return check(this)">
			<input type = "hidden" placeholder="이름" name= "id" value="${board.id}">
			<input type = "hidden" name= "seqno" value="${board.seqno}">
			
			<table>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" style="width:100%" value="${board.title}"></td>
			</tr>
			
			<tr>
				<th>내용</th>
				<td><input style="width:100%;" name="content" value="${board.content}"></td>
			</tr>
			
				<div class="write2">
					<div class="write3">
						<input type="submit" class="myButton" value="수정">
						<input type="reset" class="myButton" value="취소">
					</div>
				</div>
			</table>
		</form>
	</div>
</div>

<%@include file="../footer.jsp"%>