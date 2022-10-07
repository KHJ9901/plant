<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../mgHeader.jsp" %>

<%@ include file="../mgMenu.jsp" %>


<div class="mgMpwholeDetail">

	<div class="detailHead">
	<c:set value="${mpd}" var="dt" />
	<c:set value="${dt.mgmpimg}" var="mpimg" />

		<c:set value="${mpimg.fileType}" var="filetype" />
	
 		<div class="detailImg">
		
				<c:set value="${fn:substring(filetype, 0, fn:indexOf(filetype, '/'))}" var="type" />
				
				<c:if test="${type eq 'image'}">
					<c:set value="${mpimg.fileName}" var="img_file" />
					<img src="/plant/${img_file}">
				</c:if> 
				
		</div>
		
		
			<div class="detailHeadTitle">
				<div class="detailSubTitle">
						<div class="dicRegName">${dt.name}</div>
						<div class="dicRegName">${dt.mplant_seqno}번째 회원 식물</div>
					
						<div class="dicRegCate">${dt.cate}</div>
				</div>
			</div>
		</div>
		
		<hr class="firstHr">
		
		<div class="dicWater">
			<h2>물은 이렇게 주세요</h2>
			<div class="dicRegWater">${dt.water}</div>
		</div>
		
		<hr class="secondHr">
		
		<div class="dicPlace">
			<h2>이런 장소를 좋아해요</h2>
			<div class="dicRegPlace">${dt.place}</div>
		</div>
			
		<hr class="thirdHr">
		
		<div class="dicMoist">
			<h2>온도와 습도는 이렇게 맞춰주세요</h2>
			<div class="dicRegTemp">${dt.temp}"</div>
			<div class="dicRegMoist">${dt.moist}</div>
		</div>
			
		<hr class="forthHr">
		
		<div class="dicEtc">
			<h2>이런 부분은 더 신경 써주세요</h2>
			<div class="dicRegEtc">${dt.etc}</div>
		</div>
		
		<hr class="fifthHr">
	

</div>
<%@ include file = "../mgFooter.jsp" %>