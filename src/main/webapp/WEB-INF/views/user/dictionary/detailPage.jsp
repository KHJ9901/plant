<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file = "../common.jsp" %>

<link rel="stylesheet" href="/css/detailPage.css">

<div class="wholeDetail">

	<div class="detailHead">
	<c:set value="${dictiondetail}" var="dt" />
		<c:set value="${dt.filetype}" var="filetype" />

 		<div class="detailImg">
		
			<c:set value="${fn:substring(filetype, 0, fn:indexOf(filetype, '/'))}" var="type" />
			
			<c:if test="${type eq 'image'}">
				<c:set value="${dt.savefilename}" var="img_file" />
				<img src="/upload/${img_file}">
			</c:if> 
		</div>
		
		<div class="detailHeadTitle">
		
			<div class="detailSubTitle">

				<div class="cateHeart">

					<div class="detailCategory">${dt.cate}</div>
					
					<div class="heartDiv">
						<div class="heart">♡</div>
					</div>
					
				</div>
				
				<div class="plantName">
					<div class="kname">${dt.kname}</div>
					<div class="ename">${dt.ename}</div>
				</div>
				
			</div>
			
			<div class="advice">
				<div class="adviceText">키우는 난이도가 어땠나요?</div>
				<div class="easy">키우기 쉬워요</div>
				<div class="hard">키우기 어려워요</div>
			</div>
			
		</div>
		
	</div>
		
	
	
	<hr class="firstHr">
	
	<div class="water">
		<h2>물은 이렇게 주세요</h2>
		<div>${dt.water}</div>
	</div>
	
	<hr class="secondHr">
	
	<div class="place">
		<h2>이런 장소를 좋아해요</h2>
		<div>${dt.place}</div>
	</div>
		
	<hr class="thirdHr">
	
	<div class="moist">
		<h2>온도와 습도는 이렇게 맞춰주세요</h2>
		<div>온도 : ${dt.temp}</div>
		<div>습도 : ${dt.moist}</div>
	</div>
		
	<hr class="forthHr">
	
	<div class="etc">
		<h2>이런 부분은 더 신경 써주세요</h2>
		<div>${dt.etc}</div>
	</div>
	
	<hr class="fifthHr">
	
	<div class="detailQuestion">
		<h2>질문하기</h2>
		<div>
			<div>식물이 아프거나 키우기 어렵다면 다른 식집사 분들의 도움을 받아보세요.</div>
		</div>
		<div class="questionBt">
			<button type="button" onclick="location.href='qeustion.jsp'">질문</button>
		</div>
	</div>

</div>
<%@ include file = "../footer.jsp" %>