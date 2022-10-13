<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="mgHeader.jsp" %>
<%@ include file="mgMenu.jsp" %>

<div id="modal">
	<div class="modal-content">
		<div class="login">
			<a href="javascript:modal_close()">
			<img id="나가기" src="https://cdn-icons-png.flaticon.com/512/458/458594.png">
			</a>

			<form class="loginform" method="post" action="login.do">
				<input class="loginInput" type="text" placeholder="아이디" maxlength="10" name="id" required><br>
				<input class="loginInput" type="password" placeholder="비밀번호" maxlength="10" name="pw" required><br>
				<input class="loginbtn" type="submit" value=" 로그인">
			</form>
		</div>
	</div>
</div>

<script>
function init() {
	// var msg = document.getElementsByName("msg").value; // ElementsByName 은 배열로 넘어옴
	var msg = document.getElementsByName("msg");
	//alert(msg);
	var alert_msg;
	var modal_pop = false;
	if(msg[0].value == "loginOk") {
		//alert("로그인이 되었습니다.");
		alert_msg = "로그인이 되었습니다.";
		modal_pop = false;
	}
	
	
	if(msg[0].value == "loginFail") {
		alert_msg = "일치하는 회원 정보가 없습니다.";
		modal_pop = true;
		//var stat = document.getElementsByName("stat");
		//if(stat[0].value == 1) {
			// alert(stat[0].value);
	}

	if(msg[0].value != "null") alert(alert_msg);
	if(modal_pop) document.getElementById("modal").style.display ="block";
}
	

function loginTag() {
 	document.getElementById("modal").style.display= "block";
} 
function modal_close() {
	document.getElementById("modal").style.display= "none";
}   
</script>
<body onload="init()">
	<input type="text" name="msg" id="msg" value="<%= request.getAttribute("msg") %>">
</body>
</html>