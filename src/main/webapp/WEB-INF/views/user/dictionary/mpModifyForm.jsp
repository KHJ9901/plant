<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file = "../common.jsp" %>

<link rel="stylesheet" href="/css/detailPage.css">
<link rel="stylesheet" href="/css/mpReg.css">

<div class="wholeDetail">
<c:set value="${mplantdetail}" var="md" />
	<form method="post" enctype="multipart/form-data" class="" action="/dic/updateMplant">
		<input type="hidden" name="mplant_seqno" value="${md.mplant_seqno}">
	
		<div class="mpRegHead">
	 		<div class="mpRegImg">
				<c:forEach items="${md.mpimg}" var="mpi" >
				<c:choose>
					<c:when test="${empty mpi}">
						<input type="file" name="filename">
					</c:when>
					
					<c:when test="${!empty mpi}">
						<c:set value="${mpi}" var="img" />
							<c:set value="${img.fileType}" var="filetype" />
							<c:set value="${fn:substring(filetype, 0, fn:indexOf(filetype, '/'))}" var="type" />
							
							<div id="fileSector">
							
								<c:if test="${type eq 'image'}">
									<c:set value="${img.mpthumb.fileName}" var="thumb_file" />
									<img src="/plant/thumb/${thumb_file}">
								</c:if>
								
								${img.fileName} (사이즈:${img.fileSize})
								<input type="button" value="삭제" onclick="fileDel('${img.fileName}', '${img.saveFileName}', '${img.filePath}', '${thumb_file}')">
							</div>
					</c:when>
					
				</c:choose>
				</c:forEach>
				<p>식물사진을 업로드 해주세요</p>
			</div>
			
			<div class="detailHeadTitle">
				<div class="detailSubTitle">
						<input class="mpRegName" type="text" name="name" maxlength="50" 
							   placeholder="식물이름을 작성하세요.(50자이내)" required
							   value="${md.name}">
					
						<select name="cate" onchange="inputCate()">
					  		<option value="A" <c:if test="${md.cate eq 'A'}"> selected </c:if>>관엽식물</option>
					  		<option value="B" <c:if test="${md.cate eq 'B'}"> selected </c:if>>착생식물</option>
					  		<option value="C" <c:if test="${md.cate eq 'C'}"> selected </c:if>>다육식물</option>
					  		<option value="D" <c:if test="${md.cate eq 'D'}"> selected </c:if>>식충식물</option>
						</select>
				</div>
				
				<div class="mpAdvice">
					<div class="adviceText">식물의 난이도를 선택해주세요</div>
					<input type="radio" name="plevel" value="e"
						<c:if test="${md.plevel eq 'e'}"> checked </c:if> >쉬움
						
					<input type="radio" name="plevel" value="h"
						<c:if test="${md.plevel eq'h'}"> checked </c:if> >어려움
				</div>
			</div>
		</div>
		
		<hr class="firstHr">
		
		<div class="water">
			<h2>물은 이렇게 주세요</h2>
			<input class="regWater" type="text" name="water" maxlength="100" 
				   placeholder="수분정보를 기입하세요(100자이내)" required
				   value="${md.water}">
		</div>
		
		<hr class="secondHr">
		
		<div class="place">
			<h2>이런 장소를 좋아해요</h2>
			<input class="regPlace" type="text" name="place" maxlength="100" 
				   placeholder="장소정보를 기입하세요(100자이내)" required
				   value="${md.place}">
		</div>
			
		<hr class="thirdHr">
		
		<div class="moist">
			<h2>온도와 습도는 이렇게 맞춰주세요</h2>
			<input class="regTemp" type="text" name="temp" maxlength="100" 
				   placeholder="온도정보를 기입하세요(100자이내)" required
				   value="${md.temp}">
			<input class="regMoist" type="text" name="moist" maxlength="100" 
				   placeholder="습도정보를 기입하세요(100자이내)" required
				   value="${md.moist}">
		</div>
			
		<hr class="forthHr">
		
		<div class="etc">
			<h2>이런 부분은 더 신경 써주세요</h2>
			<input class="regEtc" type="text" name="etc" maxlength="100" 
				   placeholder="기타 추가정보를 기입하세요(100자이내)"
				   value="${md.etc}">
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
		return true;
	}
	
	function fileDel(no, savefilename, filepath, thumb_filename) {
		var ans = confirm("정말로 삭제하시겠습니까?");
		if (ans) {
			var x = new XMLHttpRequest();
			x.onreadystatechange = function() {
				if(x.readyState ===4 && x.status === 200) {
					var tag = document.getElementById("fileSector");
					
					if(x.responsText.trim() === "0") {
						alert("파일 삭제 실패");
					}	else {
						aler("파일 삭제함");
						tag.innerHTML = "<input type='file' name+'filename'>";
					}
						
				}	else {
						console.log('에러코드 : ' + x.status)
				}
			};
		}
		
		x.open("POST", "/file/fileDel", true);
		x.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		x.send("no="+no+"&savefilename="+savefilename+"&filepath="+filepath+"&thumb_filename="+thumb_filename);
		
	}

</script>
</div>
<%@ include file = "../footer.jsp" %>