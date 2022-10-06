<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common.jsp" %>
<link rel="stylesheet" href="/css/pwlost.css">
<script src="/js/homecheck.js"></script>
<c:set var="find" value="${find}"/>
<body onload="plz('${find}')">

<form action="/member/login.jsp" name="find-pw" class="find-pw-cert" method="post">
	<div class="pwlost">
	     <div class="textcenter"> 
	     	<c:set value="${member}" var="member"/>                                                                                     
	    		<h2>내 비밀번호</h2>
				<h3>${member.pw}</h3>
				<button class="mybutton" type="submit">로그인</button>			
		 </div>
	</div>
</form>
<%@include file = "../footer.jsp" %>
</body>
<script>
function plz(find){
    var alert_msg;
    if(find == "pwfind") {
       alert_msg ="성공.";
    }         
    if(find == "pwlost") {
       alert_msg = "회원정보가없습니다.";   
    }   
       if(find != "null") alert(alert_msg);
 }
</script>