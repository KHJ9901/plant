<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file = "../common.jsp" %>

<link rel="stylesheet" href="/css/login.css">
	
<div class="wholePage">	
	<div class="bodyContainer">
		<div class="loginbox">
			<div class="loginboxcontent">
				<h2 class="logintitle">로 그 인</h2>
				<form method="post" action="/login">
					<input type="text"id="" class="username_input" placeholder="아이디" id="username_input" name="Username"> 
					<input type="password" id="pw" class="userpassword_input" placeholder="비밀번호" id="Password" name="Password">
					<input type="submit" class="loginButton" value="로그인" >
				</form>
				<div class="login">
					<a href = "/lo/memRegForm">회원가입</a> |
					<a href = "/lo/pwlost">비밀번호찾기</a>
				</div>
				<div class="sns">
					<a href="아직안정함"><img src="/img/google.png"></a>
					<a href="아직안정함"><img src="/img/facebook.png"></a>
					<a href="아직안정함"><img src="/img/apple.png"></a>
				</div>
			</div>
			
		</div>
	</div>
</div>




<%@include file = "../footer.jsp" %>