<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<div class="w3-top">
  <div class="w3-bar" id="myNavbar">
    <a class="w3-bar-item w3-button w3-hover-black w3-hide-medium w3-hide-large w3-right" href="javascript:void(0);" onclick="toggleFunction()" title="Toggle Navigation Menu">
      <i class="fa fa-bars"></i></a>
    <a href="mgHome.jsp" class="w3-bar-item w3-button">홈</a>
    <a href="mgDiction.mg" class="w3-bar-item w3-button w3-hide-small">사전관리</a>
    <a href="/memboard/memBoard.jsp" class="w3-bar-item w3-button w3-hide-small">회원목록</a>
    <a href="/plant_manager/freeboard/freeBoard.jsp" class="w3-bar-item w3-button w3-hide-small">게시판관리</a>
    <a href="/plant_manager/freeboard/adoptBoard.jsp" class="w3-bar-item w3-button w3-hide-small">분양관리</a>

    <a href="logout.do" class="w3-bar-item w3-button w3-hide-small w3-right"><i class="fa fa-board"></i>로그아웃</a>
  </div>

  <!-- Navbar on small screens -->
  <div id="navDemo" class="w3-bar-block w3-white w3-hide w3-hide-large w3-hide-medium">
    <a href="#about" class="w3-bar-item w3-button" onclick="toggleFunction()">ABOUT</a>
    <a href="#portfolio" class="w3-bar-item w3-button" onclick="toggleFunction()">PORTFOLIO</a>
    <a href="#contact" class="w3-bar-item w3-button" onclick="toggleFunction()">CONTACT</a>
    <a href="#" class="w3-bar-item w3-button">SEARCH</a>
  </div>
</div>