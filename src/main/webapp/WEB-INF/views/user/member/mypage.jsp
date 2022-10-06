<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@include file = "../common.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" href="/css/mypage.css">

<div class="wholePage">
	<div class="headContainer">
		<div class="headCategory">
			<a href="/lo/editmypage">회원정보변경</a>  
			<a href="/me/myboard">내가작성한글</a>
		</div>
	</div>
	
	<div class="headContainer2">
		<div class="headNotice">
			<h1 class="headTitle">마이페이지</h1>
			
			<!-- 버튼이면 class="wrap" -->
			<!-- 검색이면 class="searchForm" -->
			

		</div>
	</div>

	
	
	<div class="bodyContainer">
		<div class="nickmy">
			<div class="userpng"><img src="/img/user.png">
			</div>
			<div class="myline">
			<c:set value="${loginUser}" var="user"/>
			<c:set value="${board}" var="board" />
			<%-- <c:set value="${reply}" var="reply" /> --%>
					<div class="joinmyid" name="id">
						${board.nickname}
					</div>
					<div class="joindata" name="wdate">
						${board.wdate}
					</div>
					<div class="joinmail" name="email">
						${board.email}
					</div>
					<div class="myphonnum" name="phone">
						${board.phone}
					</div>
					<div class="mywritecount" name="mywritecount">
						내가 쓴 게시글 수 : ${board.count}
					</div>
					<div class="myreplycount" name="myreplycount">
						내가 쓴 댓글 수 : ${board.reply} 
					</div>
				
			</div>
		</div>	
	</div>
</div>
<%@include file = "../footer.jsp" %>