<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
    
<%@include file = "../common.jsp" %>


<link rel="stylesheet" href="/css/ask.css">

<body class="asd_body">
<%@include file = "../menu.jsp" %>
<div class="asd_page_container2">
	<div class="asd_cotainner">
		<div class="asd_con_detail">
			<section class="asd_detail_title">
				<h1>질문수정</h1>
			</section>
			<section class="asd_detail_form">
				<div class="asd_btn_group2">
				</div>
				<c:set value="${ask}" var="qna" />
				<form class="asd_ask_help" method="post" enctype="multipart/form-data" action="/qna/qnaUpdate">
					<div class="asd_input_title">
					궁금한 내용을 작성해주세요
					</div>
					<div class="asd_textarea_item">
						<div style="position: relative;">
							<textarea id="asd_taxtarea" class="asd_is_empty" rows="1" maxlength="500" style="height: 108px;" name="content">${qna.content}</textarea>
						</div>
					</div>
					<div class="asd_input_title">
					사진등록
						<br class="asd_md_down_only">
						<!-- 첨부파일 -->
					<c:set value="${qna.qna_img}" var="qnaimg" />
					<c:choose>
						<c:when test="${empty qnaimg}">
							<input type="file" name="filename">
						</c:when>
						<c:when test="${!empty qnaimg}">
							<c:forEach items="${qnaimg}" var="file">
								<c:set value="${file.type}" var="filetype" />
								<c:set value="${fn:substring(filetype, 0, fn:indexOf(filetype, '/'))}" var="type" />
								
								<div id="fileSector">
								<c:if test="${type eq 'image'}">
									<c:set value="${file.thumbnail.fileName}" var="thumb_file" />
									<img src="/fileupload/thumbnail/${thumb_file}">
								</c:if>
								<input type="button" value="삭제" onclick="fileDel('${file.qiseqno}', '${file.savefile}', '${file.location}', '${thumb_file}')">
								</div>
							</c:forEach>
						</c:when>
					</c:choose>
<!-- 				<span class="asd_input_tips">사진을 등록하실 수 있어요. -->
<!-- 				</span> -->
					</div>
					<input type= "hidden" value="${ask.seqno}" name="seqno" >
					<input type= "hidden" value="${ask.id}" name="id" >
					<div class="asd_text_center">
						<input class="asd_btn_primary" type="submit" value="수정하기">
					</div>
				</form>		
			</section>
		</div>
	</div>
</div>
<script>
function fileDel(no, savefile, location, thumbnail){
	
	var ans = confirm("삭제하시겠습니까?");
	if (ans){
		var x = new XMLHttpRequest();
		x.onreadystatechange = function(){			
			if(x.readyState === 4 && x.status === 200) {
				
				var tag = document.getElementById("fileSector");
				
				if(x.responseText.trim() === "0") {
					alert("파일 삭제 실패")
				}else {
					alert("파일 삭제 성공")
					tag.innerHTML = "<input type='file' name='filename'>";
				}
			} else {
				console.log('에러코드' + x.status)
			}			
		};
	}
	
	x.open("POST", "/qfile/fileDel", true);
	x.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	x.send("no="+no+"&saveFile="+savefile+"&location="+location+"&thumbnail="+thumbnail);
}

</script>
</body>

<%@include file="../footer.jsp" %>
</html>