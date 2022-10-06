<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file = "../common.jsp" %>


<link rel="stylesheet" href="/css/ask.css">

<body class="asd_body">
<%@include file = "../menu.jsp" %>
<div class="asd_page_container">
	<div class="asd_cotainner">
		<div class="asd_con_detail">
			<section class="asd_detail_title">
				<h1>질문하기</h1>
			</section>
			<section class="asd_detail_form">
<!-- 				<div class="asd_btn_group2"> -->
<!-- 					<button class="asd_hekp"> -->
<!-- 						<span>식물이 아파요</span> -->
<!-- 					</button> -->
<!-- 					<button class="asd_active"> -->
<!-- 					<span>식물이 궁금해요</span> -->
<!-- 					</button> -->
<!-- 				</div> -->
				
				<form method="post" action="/qna/register"class="asd_ask_help" enctype="multipart/form-data" onsubmit="return check(this)">
					<c:set value="${loginUser}" var="user"/>
					<input type = "hidden" name="id" value = "${user.id}">
					<div class="asd_input_title">
					궁금한 내용을 작성해주세요
					</div>
					<div class="asd_textarea_item">
						<div style="position: relative;">
							<textarea name="content" id="asd_taxtarea" class="asd_is_empty" rows="1" maxlength="500" style="height: 108px;"></textarea>
						</div>
					</div>
					<div class="asd_input_title">
					사진등록
						<br class="asd_md_down_only">
						<span class="asd_input_tips">사진을 등록하실 수 있어요.
						</span>
					</div>
					<div class="asd_photo_uploader">
					<div class="asd_upload_input">
							<label for="photo-uploader">
 								<input type="file" name="qnaimg">
							</label>
						</div>
					</div>
					
					<div class="asd_text_center">
						<input type="submit" class="asd_btn_primary" value=등록하기>
					</div>
				</form>		
			</section>
		</div>
	</div>
</div>
</body>

<%@include file="../footer.jsp" %>
</html>