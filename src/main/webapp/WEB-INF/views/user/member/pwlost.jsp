<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    
<%@ include file="../common.jsp" %>
<link rel="stylesheet" href="/css/pwlost.css">
<script src="/js/homecheck.js"></script>
<c:set var="find" value="${find}"/>
<body onload="plz('${find}')">
<form action="/lo/pwfind" name="find-pw" class="find-pw-cert" method="post">
	<div class="pwlost">
	     	<div class="textcenter">                                                                                          
	    		<h2>비밀번호 찾기</h2>
				<h4>등록된 회원정보로 찾기</h4>
				<p>회원 가입 시 등록한 정보를 정확히 입력해주세요.</p>
			</div>
			
	       <div class="textForm">
	       		<input name="pwlostId" type="text" class="id" placeholder="아이디" required>
	      </div>
	      
	       <div class="textForm">
	       		<input name="pwlostname" type="text" class="pwlostname" placeholder="이름" required>
	      </div>
	      
	       <div class="textForm">
	        	<input name="pwlostemail" type="text" class="pwlostemail" placeholder="이메일" required>
	      </div>
	      
	      <button type="submit">비밀번호 찾기</button>
		  		
		 
	</div>
</form>

</body>
<!-- <script type="text/javascript">
function plz(msg){
//   var msg = document.getElementsByName("change");
   //alert(msg);
   var alert_msg;
   var modal_pop;
   if(msg == "ok") {
      alert_msg ="로그인이 되었습니다";
      modal_pop = false;
   }
   
   if(msg == "pwfail"){
      alert_msg ="회원등록이 되었습니다.";
      modal_pop = true;
         
   };
   
   if(msg == "no_member"){
      alert_msg ="로그인 정보가 없습니다.";
      modal_pop = true;
      
   }
   
   if(msg == "null") alert(alert_msg);
   if(modal_pop == true) {
      document.getElementById("modal").style.display = "block";
   }
} -->

<script>
function plz(find){
    var alert_msg;
    if(find == "pwlost") {
       alert_msg = "회원정보가없습니다.";   
    }   
       //if(find != "null") alert(alert_msg);
 }
</script>

<%@include file = "../footer.jsp" %>

      