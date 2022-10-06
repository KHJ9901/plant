<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>   
<%@ include file="../common.jsp" %>
<link rel="stylesheet" href="/css/mypageEdit.css">
<c:set value="${board}" var="user"/>
      <div class="mychange">
      <h2>정보변경</h2>
      </div>
      
      <div class="textForm">
      아이디
        	<input name="id" type="text" class="id" value="${user.id}" readonly>
      </div>
      
      <div class="textForm">
       새비밀번호	 <input name="pw" type="password" class="pw" placeholder="새비밀번호">
      </div>
      
      <div class="textForm">
       	비밀번호재입력	 <input name="loginPwConfirm" type="password" class="pw" placeholder="비밀번호 확인">
      </div>
      
      <div class="textForm">
      	  닉네임   <input name="nickname" type="text" class="nickname" value="${user.nickname}">
      </div>
      
      <div>
      		<button type="button" onclick="fn_dbCheckId()" class="checkname">중복확인</button>
       		 <input type="hidden" name="odDuplication" value="nameUncheck"/>
      </div>
      
       <div class="textForm">
      	이메일	 <input name ="email" type="text" value="${user.email}" class="email">
      </div>
      <div>
      		<button type="button" onclick="fn_dbCheckId()" name="dbCheckName" class="checkname">중복확인</button>
       		 <input type="hidden" name="odDuplication" value="nameUncheck"/>
      </div>
      
      <div class="textForm">
      		  <input type="text" placeholder="직접입력" class="email">
       </div>  
	         <select class="selDomain" name="selDomain" onchange="inputDomain()">
	         <option value="">직접입력</option>
	         <option value="naver.com">naver.com</option>
	         <option value="daum.net">daum.net</option>
	         <option value="gmail.com">gmail.com</option>
      </select>
      
      <div class="textForm">
      	  전화번호   <input name="phone" type="number" class="cellphoneNo" value="${user.phone}">
      </div>
      	<div class="wrap">
      		<input type="submit" class="myButton" value="수정완료">
      		<input type="button" id="memdel"class="mybutton" value="회원탈퇴">
      	</div>
      	<div>
     		
      	</div>

    <%@include file = "../footer.jsp" %>
    <script type="text/javascript" src="/js/Plantmember.js"></script>
    <script>
    $(document).ready(function(){
    
	$('#memdel').on("click",function(e){
		var id = $('#id').val();
		$.ajax({
			url : "${cpath}/member/memdel.do",
			type : "post",
			data : {id : id},
			dataType : 'json',
			success : function(result){
			console.log("결과값 : " + result);
				alert("사용해주셔서 감사합니다");
			},
			error : function(){
				alert("서버요청실패");
			}
		})
	})	
})
    
    </script>
    