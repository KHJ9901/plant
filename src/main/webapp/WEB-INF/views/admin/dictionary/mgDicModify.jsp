<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../mgHeader.jsp" %>

<%@ include file="../mgMenu.jsp" %>

<link rel="stylesheet" href="/css/mgDicReg.css">

<div class="dicRegWholeDetail">
<c:set value="${dt}" var="dt" />
	<form method="post" enctype="multipart/form-data" class="" action="mgDicModify">
	<input type="hidden" value="${dt.seqno}" name="seqno">
	
		<div class="dicRegHead">
	 		<div class="dicRegImg">
				<c:set value="${dt.mgdicimg}" var="mdi" />
				<%-- <c:choose> --%>
					<c:if test="${empty mdi}">
						<input type="file" name="filename">
					</c:if>
					
					<c:if test="${!empty mdi}">
						<c:set value="${mdi}" var="img" />
							<c:set value="${img.fileType}" var="filetype" />
							<c:set value="${fn:substring(filetype, 0, fn:indexOf(filetype, '/'))}" var="type" />
							
							<div id="fileSector">
							
								<c:if test="${type eq 'image'}">
									<c:set value="${img.mgdicthumb.fileName}" var="thumb_file" />
									<img src="/plant/thumb/${thumb_file}">
								</c:if>
								
								${img.fileName} (사이즈:${img.fileSize})
								<input type="button" value="삭제" onclick="fileDel('${img.fileName}','${img.filePath}', '${thumb_file}')">
							</div>
							
					</c:if>
					
				<%-- </c:choose> --%>
				<p>식물사진을 업로드 해주세요</p>
			</div>
			
			<div class="detailHeadTitle">
				<div class="detailSubTitle">
						<input class="dicRegName" type="text" name="reg_kname" maxlength="50" 
							   placeholder="식물한글이름을 작성하세요.(50자이내)" required
							   value="${dt.kname}">
						<input class="dicRegName" type="text" name="reg_ename" maxlength="50" 
							   placeholder="식물영문이름을 작성하세요.(50자이내)" required
							   value="${dt.ename}">
					
						<select name="reg_cate">
					  		<option value=""
					  			<c:if test="${dt.cate != 'A'} || ${dt.cate != 'B'} || ${dt.cate != 'C'} || ${dt.cate != 'D'}"> checked </c:if> >${dt.cate} </option>
					  		<option value="A"
					  			<c:if test="${dt.cate == 'A'}"> checked </c:if> >관엽식물</option>
					  		<option value="B"
								<c:if test="${dt.cate == 'B'}"> checked </c:if> >착생식물</option>
					  		<option value="C" 
					  			<c:if test="${dt.cate == 'C'}"> checked </c:if> >다육식물</option>
					  		<option value="D" 
					  			<c:if test="${dt.cate == 'D'}"> checked </c:if> >식충식물</option>
					  	</select>
					  	
						<fieldset id="필드">
						  	<legend>장소 카테고리</legend>
							  	<input type="radio" name="reg_lcate" value="실내용"
							  		<c:if test="${dt.lcate == '실내용'}"> checked </c:if>> 실내용
							  	<input type="radio" name="reg_lcate" value="야외용"
									<c:if test="${dt.lcate == '야외용'}"> checked </c:if>> 야외용
				  		</fieldset>
				</div>
			</div>
		</div>
		
		<hr class="firstHr">
		
		<div class="dicWater">
			<h2>물은 이렇게 주세요</h2>
			<input class="dicRegWater" type="text" name="reg_water" maxlength="100" 
				   placeholder="수분정보를 기입하세요(100자이내)" required
				   value="${dt.water}">
		</div>
		
		<hr class="secondHr">
		
		<div class="dicPlace">
			<h2>이런 장소를 좋아해요</h2>
			<input class="dicRegPlace" type="text" name="reg_place" maxlength="100" 
				   placeholder="장소정보를 기입하세요(100자이내)" required
				   value="${dt.place}">
		</div>
			
		<hr class="thirdHr">
		
		<div class="dicMoist">
			<h2>온도와 습도는 이렇게 맞춰주세요</h2>
			<input class="dicRegTemp" type="text" name="reg_temp" maxlength="100" 
				   placeholder="온도정보를 기입하세요(100자이내)" required
				   value="${dt.temp}">
			<input class="dicRegMoist" type="text" name="reg_moist" maxlength="100" 
				   placeholder="습도정보를 기입하세요(100자이내)" required
				   value="${dt.moist}">
		</div>
			
		<hr class="forthHr">
		
		<div class="dicEtc">
			<h2>이런 부분은 더 신경 써주세요</h2>
			<input class="dicRegEtc" type="text" name="reg_etc" maxlength="100" 
				   placeholder="기타 추가정보를 기입하세요(100자이내)"
				   value="${dt.etc}">
		</div>
		
			<input class="dicRegBt" type="submit" value="수정">
		
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