<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../common.jsp" %>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>   
<link rel="stylesheet" href="/css/memberform.css">
<!-- <script src="/js/homecheck.js"></script> -->

	<div class="formContainer"> 
	<c:set value="${loginUser}" var="user"/>                                                                                     
	      	<h2>회원가입</h2>
	    	<div class="textForm">
	        	<label for ="id"  class="id" >아이디</label>
	        	<input type ="text" class="id" name="id" id="id">
	        	<div>
	        		<font id = " id_feedback" size="2"></font>
	        	</div>
	     		<input type="hidden" name="odDuplication" value="idUncheck"/>
	        </div>
	        
	        <div class="textForm">
	        	<input id="pw" name="pw" type="password" class="pw" placeholder="비밀번호" required>
	        </div>
	        
	       	<div class="textForm">
	        	<input name="loginPwConfirm" type="password" class="pw" placeholder="비밀번호 확인" required>
	      	</div>
	      	
	        <div class="textForm">
	        	<input id="name2" name="name" type="text" class="name" placeholder="이름" required>
	        </div>
	        
	        <div class="textForm">
		    	<input type="text" id="email" name="email" placeholder="이메일아이디" class="email" required>
		    	<button type="button"id="EmailDoubleCheck" name="dbCheckemail" class="checkid" >중복확인</button>
		    </div>
		    
		    <div class="textForm">
	        	<input type="text" placeholder="직접입력" class="email" id="domain" name="domain" required>
	        	 <select name="selDomain" onchange="inputDomain()">
	      		<option value="">직접입력</option>
	      		<option value="naver.com">naver.com</option>
	      		<option value="daum.net">daum.net</option>
	      		<option value="gmail.com">gmail.com</option>
	      		</select>
	       </div>  
	       
	       <div class="textForm">
	       		<input id="nickname" name="nickname" type="text" class="nickname" placeholder="닉네임" required>
	      		<button type="button" id="NicknameDoubleCheck"  name="dbCheckName" class="checkname" >중복확인</button>
	     		<input type="hidden" name="odDuplication" value="nameUncheck"/>
	      </div>
	      
	      <div class="textForm">
	        	<input id="phone" name="phone" type="number" class="cellphoneNo" placeholder="전화번호" required>
	      </div>
	      
	      <!-- <input type="button" id="insertmember" class="myButton" value="J O I N"/> -->
	      <button id="insertmember" class="myButton"> J O I N</button>
   </div>    

    
<!-- <script>

function check(){
	if(!document.joinForm.loginId.value){
		alert("아이디를 입력하세요.");
		return false;
	}
	if(!document.joinForm.loginPw.value){
		alert("비밀번호를 입력하세요.");
		return false;
	}
	if(document.joinForm.loginPw.value != document.joinForm.loginPwConfirm.value){
		alert("비밀번호가 서로 다릅니다.")
		return false;
	}
}

function confirmId(){
	if(document.joinForm.loginId.value == "") {
		alert("아이디를 입력하세요");
		return;
	}
	url = "memregform.jsp?loginId=" + document.joinForm.loginId.value;
	open(url, "confirm","toolbar=no,location=ne,status=no, menubar=no, scrollvars=no,resizable=no,width=300,height=200");
}
function confirmEmail(){
	if(document.joinForm.memregemail.value == "") {
		alert("이메일을 입력하세요");
		return;
	}
	url = "memregform.jsp?memregemail=" + document.joinForm.memregemail.value;
	open(url, "confirm","toolbar=no,location=ne,status=no, menubar=no, scrollvars=no,resizable=no,width=300,height=200");
function confirmNickname(){
	if(document.joinForm.nickname.value == "") {
		alert("닉네임을 입력하세요");
		return;
	}
	url = "memregform.jsp?nickname=" + document.joinForm.nickname.value;
	open(url, "confirm","toolbar=no,location=ne,status=no, menubar=no, scrollvars=no,resizable=no,width=300,height=200");
</script> -->



    <%@include file = "../footer.jsp" %>


<script type="text/javascript" src="/js/Plantmember.js"></script>
<script>
$(document).ready(function(){
	

	
	/* 회원가입 넣고싶다 */
 	$("#insertmember").on("click",function(e){
	console.log(id.value);
	console.log($("#name2").val());
	
		var member = {
				id : id.value,
				pw : pw.value,
				name : name2.value,
				email : ""+email.value +"@"+domain.value,
				nickname : nickname.value,
				phone : phone.value
		}
		memberService.insertmember(member,function(result){
			alert("회원등록완료 로그인을 해주세요." + result);
			location.href="/lo/loginview"
			//document.getElementById("newLine").innerHTML = "<li>" + reply.comment + "</li>";
		});
	});
	/* 아이디중복확인 */
	/* $("#IdDoubleCheck").on("click", function(e){

		console.log("아이디 체크 했는데?");
		var member ={
			id : id.value
		}
		console.log("view : "+member.id);
		memberService.IdDoubleCheck(member, function(result){
			alert("사용가능한 아이디" + result);
		});
	}); */
	
	$('#id').change(function(){
		let id = $('#id').val();
		$.ajax({
			url : "${cpath}/member/idDoubleCheck.do",
			type : "post",
			data : {id : id},
			dataType : 'json',
			success : function(result){
				if(result == 1){
			console.log("결과값 : " + result);
				alert("이미 사용하는 아이디입니다");
				} else{
					alert("사용 가능한 아이디입니다");
				}
			},
			error : function(){
				alert("서버요청실패");
			}
	})
	})	
})


</script>