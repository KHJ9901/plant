<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html>
<head>    
<%@include file = "common.jsp" %>
</head>
 
<body onload="init()">
<div class="bgimg-1 w3-display-container w3-opacity-min" id="home">
  <div class="w3-display-middle" style="white-space:nowrap;">
    <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">식물플래너</span>
  </div>
</div>
<input type="hidden" name="msg" value="<%= request.getAttribute("msg") %>">
<%@ include file ="footer.jsp" %>
</body>
<script>
function init(){
    const msg = document.getElementsByName("msg");
    var alert_msg;
    if(msg[0].value == "loginOk") {
       alert_msg ="로그인이 되었습니다.";
    }  
    
    if(msg[0].value == "loginFail") {
       alert_msg = "회원정보가없습니다.";   
    } 
    
    if(msg[0].value == "memberOk") {
       alert_msg = "회원등록이 되었습니다.";
    } 
    
      if(msg[0].value !== "null") alert(alert_msg); 
 }


</script>
</html>
