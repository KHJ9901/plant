<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="mgHeader.jsp" %>

<%@ include file="mgMenu.jsp" %>

<div class="bgimg-1 w3-display-container w3-opacity-min" id="home">
  <div class="w3-display-middle" style="white-space:nowrap;">
    <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">관리자 페이지</span>
  </div>
</div>

<%@ include file="mgFooter.jsp" %>
<body onload="init()">
	<input type="text" name="msg" id="msg" value="<%= request.getAttribute("msg") %>">
</body>
<script>
function init() {
	// var msg = document.getElementsByName("msg").value; // ElementsByName 은 배열로 넘어옴
	var msg = document.getElementsByName("msg");
	//alert(msg);
	var alert_msg;
	var modal_pop = false;
	if(msg[0].value == "loginOk") {
		//alert("로그인이 되었습니다.");
		alert_msg = "관리자 로그인이 되었습니다.";
		modal_pop = false;
	}
	
	
	if(msg[0].value == "loginFail") {
		alert_msg = "일치하는 관리자 정보가 없습니다.";
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
</html>