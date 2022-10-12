<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file = "../common.jsp" %>

<link rel="stylesheet" href="/css/detailPage.css">
<link rel="stylesheet" href="/css/mpReg.css">

<div class="wholeDetail">

	<form method="post" enctype="multipart/form-data" class="" action="/dic/insertMplant" onsubmit="return check(this)">
	<c:set value="${loginUser}" var="id" />
	<input type="hidden" value="${id.id}" name="id">
	
		<div class="mpRegHead">
	 		<div class="mpRegImg">
				<input type="file" name="ffffffffff">
				<p>식물사진을 업로드 해주세요</p>
			</div>
			
			<div class="detailHeadTitle">
				<div class="detailSubTitle">
						<input class="mpRegName" type="text" name="name" maxlength="50" 
							   placeholder="식물이름을 작성하세요.(50자이내)" required>
					
							<select name="cate" onchange="inputCate()">
						  		<option value="A">관엽식물</option>
						  		<option value="B">착생식물</option>
						  		<option value="C">다육식물</option>
						  		<option value="D">식충식물</option>
						  	</select>
							   
				</div>
				
				<div class="mpAdvice">
					<div class="adviceText">식물의 난이도를 선택해주세요</div>
					<input type="radio" name="plevel" value="e">쉬움
					<input type="radio" name="plevel" value="h">어려움
				</div>
			</div>
		</div>
		
		<hr class="firstHr">
		
		<div class="water">
			<h2>물은 이렇게 주세요</h2>
			<input class="regWater" type="text" name="water" maxlength="100" 
				   placeholder="수분정보를 기입하세요(100자이내)" required>
		</div>
		
		<hr class="secondHr">
		
		<div class="place">
			<h2>이런 장소를 좋아해요</h2>
			<input class="regPlace" type="text" name="place" maxlength="100" 
				   placeholder="장소정보를 기입하세요(100자이내)" required>
		</div>
			
		<hr class="thirdHr">
		
		<div class="moist">
			<h2>온도와 습도는 이렇게 맞춰주세요</h2>
			<input class="regTemp" type="text" name="temp" maxlength="100" 
				   placeholder="온도정보를 기입하세요(100자이내)" required>
			<input class="regMoist" type="text" name="moist" maxlength="100" 
				   placeholder="습도정보를 기입하세요(100자이내)" required>
		</div>
			
		<hr class="forthHr">
		
		<div class="etc">
			<h2>이런 부분은 더 신경 써주세요</h2>
			<input class="regEtc" type="text" name="etc" maxlength="100" 
				   placeholder="기타 추가정보를 기입하세요(100자이내)">
		</div>
		
			<input class="mpRegBt" type="submit" value="식물등록">
		
		<hr class="fifthHr">
	</form>
<script>
	function check(f) {
		if(f.reg_plevel.value == "") {
			alert("난이도를 선택하세요");
			return false;
		}
		console.log("zz");
		return true;
	}
</script>
</div>
<%@ include file = "../footer.jsp" %>