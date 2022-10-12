<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="../common.jsp" %>
	<link rel="stylesheet" href="/css/write.css">
	<link rel="stylesheet" href="/css/adoptReview.css">
</head>

<body>
<div class="write">
	<c:set value="${adopt}" var="adopt" />
	<form method="post" enctype="multipart/form-data" action="/adopt/aUpdate" onsubmit="return check(this)">
	<input type="hidden" name="seqno" value="${adopt.seqno}">
		
		<table>
		<tr>
		<th>분양 거래 장소</th>
		<td><input type="text" style="width:100%" name="station" value="${adopt.station}"></td>
		</tr>
		
		<tr>
		<th>내용</th>
		<td><textarea style="width:100%" cols="100" rows="20" name="content">${adopt.content}</textarea></td>
		</tr>
		
		<!-- <tr>
		<th>내가 등록한 식물</th>
		<td><input type="text" style="width:100%" name="mplant" value="${adopt.mplant_seqno}"></td>
		</tr> -->
		
		<tr>
		<th>pname</th>
		<td><input type="text" style="width:100%" name="pname" value="${adopt.pname}"></td>
		</tr>
		
		<tr>
		<th>water</th>
		<td><input type="text" style="width:100%" name="water" value="${adopt.water}"></td>
		</tr>
		
		<tr>
		<th>place</th>
		<td><input type="text" style="width:100%" name="place" value="${adopt.place}"></td>
		</tr>
		
		<tr>
		<th>temp</th>
		<td><input type="text" style="width:100%" name="temp" value="${adopt.temp}"></td>
		</tr>
		
		<tr>
		<th>moist</th>
		<td><input type="text" style="width:100%" name="moist" value="${adopt.moist}"></td>
		</tr>

		<tr>
		<th>첨부파일</th>
		<td>
		<c:set value="${adopt.adoptFile}" var="adoptfile" />
		<c:choose>
			<c:when test="${empty adoptfile}">
		 		<input type="file" name="filename">
		 	</c:when>
		 	
		 	<c:when test="${!empty adoptfile}">
		 		<c:forEach items="${adoptfile}" var="file" >
		 			<c:set value="${file.filetype}" var="filetype" />
		 			<c:set value="${fn:substring(filetype, 0, fn:indexOf(filetype, '/'))}" var="type" />
			 			<div id="fileSector">
			 			<c:if test="${type eq 'image'}">
			 				<c:set value="${file.thumbnail.fileName}" var="thumb_file" />
			 				<img src="/upload/thumbnail/${thumb_file}">
			 			</c:if>
			 			${file.filename}(사이즈:${file.filesize})
			 			<input type="button" value="삭제" onclick="fileDel('${file.no}','${file.savefilename}','${file.filepath}','${thumb_file}' )">
			 			</div>
		 		</c:forEach>
		 	</c:when>
		 </c:choose>
		 </td>
		 </table>
		
		<div class="modify">
		<input type="submit" class="myButton" value="수정">
		<input type="reset" class="myButton" onclick="history.back(-1);" value="취소">
		</div>
	</form>
</div>


<script>
function fileDel(no, savefilename, filepath, thumb_filename){	
	
	 var ans = confirm("정말로 삭제하시겠습니까?");
	 if (ans){
		var x = new XMLHttpRequest();
		x.onreadystatechange = function(){
			 if(x.readyState === 4 && x.status === 200){
				 
				 var tag = document.getElementById("fileSector");
				
				 if(x.responseText.trim() === "0"){
					 alert("파일 삭제를 실패하였습니다.");
				 } else {
					 alert("파일을 삭제하였습니다.")
					 tag.innerHTML = "<input type='file' name='filename'>";
				 }
				 
				 } else {
					 console.log('에러코드:'+x.status);
				 }
		};
	}
	 
	 x.open("POST", "/file/fileDel", true);
	 x.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	 x.send("no="+no+"&savefilename="+savefilename+"&filepath="+filepath+"&thumb_filename="+thumb_filename);
}

</script>




<%@include file="../footer.jsp"%>
</body>
</html>