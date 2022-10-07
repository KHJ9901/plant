<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../mgHeader.jsp" %>

<%@ include file="../mgMenu.jsp" %>

<link rel="stylesheet" href="/css/mgDicReg.css">

<div class="dicRegWholeDetail">

	<form method="post" enctype="multipart/form-data" class="" action="mgDicReg">
	<c:set value="${loginUser}" var="id" />
	<input type="hidden" value="${id.id}" name="id">
	
		<div class="dicRegHead">
	 		<div class="dicRegImg">
	 			<c:set value="${find}" var="fin" />
				<input type="file" name="${fin}">
				<p>식물사진을 업로드 해주세요</p>
			</div>
			
			<div class="detailHeadTitle">
				<div class="detailSubTitle">
						<input class="dicRegName" type="text" name="reg_kname" maxlength="50" 
							   placeholder="식물한글이름을 작성하세요.(50자이내)" required>
						<input class="dicRegName" type="text" name="reg_ename" maxlength="50" 
							   placeholder="식물영문이름을 작성하세요.(50자이내)" required>
					
						<input type="text" placeholder="식물 카테고리" name="reg_cate">
							<select name="reg_cate" onchange="inputCate()">
						  		<option value="">직접작성</option>
						  		<option value="A">관엽식물</option>
						  		<option value="B">착생식물</option>
						  		<option value="C">다육식물</option>
						  		<option value="D">식충식물</option>
						  	</select>
							   
						<fieldset id="필드">
						  	<legend>장소 카테고리</legend>
							  	<input type="radio" name="reg_lcate" value="실내용"> 실내용
							  	<input type="radio" name="reg_lcate" value="야외용"> 야외용
				  		</fieldset>
				  		
				</div>
			</div>
		</div>
		
		<hr class="firstHr">
		
		<div class="dicWater">
			<h2>물은 이렇게 주세요</h2>
			<input class="dicRegWater" type="text" name="reg_water" maxlength="100" 
				   placeholder="수분정보를 기입하세요(100자이내)" required>
		</div>
		
		<hr class="secondHr">
		
		<div class="dicPlace">
			<h2>이런 장소를 좋아해요</h2>
			<input class="dicRegPlace" type="text" name="reg_place" maxlength="100" 
				   placeholder="장소정보를 기입하세요(100자이내)" required>
		</div>
			
		<hr class="thirdHr">
		
		<div class="dicMoist">
			<h2>온도와 습도는 이렇게 맞춰주세요</h2>
			<input class="dicRegTemp" type="text" name="reg_temp" maxlength="100" 
				   placeholder="온도정보를 기입하세요(100자이내)" required>
			<input class="dicRegMoist" type="text" name="reg_moist" maxlength="100" 
				   placeholder="습도정보를 기입하세요(100자이내)" required>
		</div>
			
		<hr class="forthHr">
		
		<div class="dicEtc">
			<h2>이런 부분은 더 신경 써주세요</h2>
			<input class="dicRegEtc" type="text" name="reg_etc" maxlength="100" 
				   placeholder="기타 추가정보를 기입하세요(100자이내)">
		</div>
		
			<input class="dicRegBt" type="submit" value="식물등록">

		
		<hr class="fifthHr">
	</form>
<script>
	function check(f) {
		if(f.reg_plevel.value == "") {
			alert("난이도를 선택하세요");
			return false;
		}
		return true;
	}
</script>
</div>
<%@ include file="../mgFooter.jsp" %>