<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="w3-top">
  <div class="w3-bar" id="myNavbar">
    <a class="w3-bar-item w3-button w3-hide-medium w3-hide-large w3-right hamburger" href="javascript:void(0);" onclick="toggleFunction()" title="Toggle Navigation Menu">
      <i class="fa fa-bars"></i></a>
      
    <a href="/" class="w3-bar-item w3-button">식물플래너</a>
    <a href="/dic/dictionary" class="w3-bar-item w3-button w3-hide-small">식물사전</a>
    <a href="/adopt/list" class="w3-bar-item w3-button w3-hide-small">분양</a>
    <a href="/board/boardlist" class="w3-bar-item w3-button w3-hide-small">게시판</a>
    
    <!-- 메뉴바 설정 -->
    <c:set value="${loginUser}" var = "loginuser"/>
    	<c:if test="${loginuser != null}">
		    <a href="/diary/list" class="w3-bar-item w3-button w3-hide-small">식물일기</a>
		    <a href="/chatting" class="w3-bar-item w3-button w3-hide-small">1:1채팅</a>
 			<a href="/chatList" class="w3-bar-item w3-button w3-hide-small">채팅리스트</a>
		    <a href="/lo/logout" class="w3-bar-item w3-button w3-hide-small w3-right"><i class="fa fa-board"></i>로그아웃</a>
		    <a href="/me/mypage" class="w3-bar-item w3-button w3-hide-small w3-right"><i class="fa fa-board"></i>마이페이지</a>
  	    </c:if>
   	    <c:if test="${loginuser eq null}">
	    	<a href="/lo/loginview" class="w3-bar-item w3-button w3-hide-small w3-right">
	    	<i class="fa fa-board"></i>로그인</a>
 	   </c:if>
  </div>


  <!-- Navbar on small screens -->
  <div id="navDemo" class="w3-bar-block w3-white w3-hide w3-hide-large w3-hide-medium smallMenu">
  
     <c:if test="${loginuser eq null}">
    	<a href="/lo/loginview" class="w3-bar-item w3-button w3-right">
    	<i class="fa fa-board1"></i>로그인</a>
    </c:if>
    
    <a href="/dic/dictionary" class="w3-bar-item w3-button">식물사전</a>
    <a href="/adopt/list" class="w3-bar-item w3-button">분양</a>
    <a href="/qna/qnalist" class="w3-bar-item w3-button">게시판</a>
    <a href="/diary/list" class="w3-bar-item w3-button w3-hide-small">식물일기</a>
    
    <c:if test="${loginuser != null}">
	    <a href="/diary/list" class="w3-bar-item w3-button">식물일기</a>
	    <a href="/chatting" class="w3-bar-item w3-button">1:1채팅</a>
 		<a href="/chatList" class="w3-bar-item w3-button">채팅리스트</a>
	    <a href="/lo/logout" class="w3-bar-item w3-button w3-right"><i class="fa fa-board"></i>로그아웃</a>
	    <a href="/me/mypage" class="w3-bar-item w3-button w3-right"><i class="fa fa-board"></i>마이페이지</a>
    </c:if>
    
  </div>
  
</div>

<script>
function toggleFunction() {
    var x = document.getElementById("navDemo");
    if (x.className.indexOf("w3-show") == -1) {
        x.className += " w3-show";
    } else {
        x.className = x.className.replace(" w3-show", "");
    }
}
</script>