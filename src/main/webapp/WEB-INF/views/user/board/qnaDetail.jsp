<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
     
<%@include file = "../common.jsp" %>


<link rel="stylesheet" href="/css/ask.css">

<body class="asd_body">
<%@include file = "../menu.jsp" %>
<div class="asd_page_container">
	<div class="asd_cotainner">
		<div class="asd_con_detail">
			<section class="asd_detail_title">
				<h1>질문·답변</h1>
			</section>
			<c:set value="${ask}" var="ask" />
			<c:set value="${loginUser}" var="user"/>
			<section class="asd_detail_form">
				<div class="asd_main_title">
					<div class="asd_group">
						<span class="asd_title">${ask.id}</span>
					</div>
					
					<div class="asd_group">				
					<span class="asd_date">${ask.wdate}</span>
					</div>
				</div>
				<hr class="asd_line">
				
				<div class="asd_content">${ask.content}</div>
				
				<!-- 첨부파일 -->
				<hr class="asd_line">
				<div>
				
				<c:set value="${ask.qna_img}" var="qnaimg" />
				<c:if test ="${qnaimg != null}">
					<c:forEach items="${qnaimg}" var="img">
						<form class="ask_file" name="filedown" method="post" action="/qfile/fileDown">
							<input type="hidden" name ="filename" value="${img.uploadfile}">
						  	<input type="hidden" name ="savefilename" value="${img.savefile}">
						  	<input type="hidden" name ="filepath" value="${img.location}">
						
							<c:set value="${img.type}" var="filetype" />
							<c:set value="${fn:substring(filetype, 0, fn:indexOf(filetype,'/')) }" var="type"/>
					
							<c:if test="${type eq 'image'}">
								<c:set value="${img.thumbnail.filename}" var="thumb_file" />
								<img src="/upload/tmp/${thumb_file}">${img.thumbnail.filename}
							</c:if>
						</form>
					</c:forEach>
				</c:if>				
				</div>
				
<!-- 				댓글 리스트 출력 영역 -->
<!-- 				<div id="list-ul"> -->
<!-- 					<ul class="reply_ul"> -->
<!-- 						<li data-rno='3'> -->
<!-- 						</li> -->
<!-- 					</ul> -->
<!-- 				</div> -->
				
				
				
				<div class="asd_bottom_group">
					<button class="asd_qna">
						<br class="asd_down_only">
						답변 ${ask.reply}
					</button>
					
					<span class="asd_seqarator">|</span>
					<button class="asd_helpful">
						<br class="asd_down_only">
						<c:set value="${loginUser}" var="user"/>
<%-- 						<input type = "hidden" name="id" value = "${user.id}"> --%>
						<a href="/qn/qnaLike?seqno=${ask.seqno}&id=${ask.id}">도움돼요 ${ask.like}</a>
					</button>
				</div>
				<c:if test="${user.id eq ask.id}">
				<div class="asd_report">
				<a href="/qna/detail?seqno=${ask.seqno}&page=qnamodify">수정</a>
				<span class="asd_seqarator">|</span>
				<a href="/qna/qnaDelete?seqno=${ask.seqno}">삭제</a>
				</div>
				</c:if>				
			</section>
			<section class="asd_reply">
			<hr class="asd_line">
				<div class="asd_reply_count">
					<span class="asd_reply_title">답변</span>
					<span class="asd_reply_count">${ask.reply}</span>
				</div>
				<div class="asd_input_item">
<!-- 					<form method="post" action="reply"> -->
						<section style="position: relative;">
<%-- 							<input type="hidden" name="qnaseqno" value ="${ask.seqno}"> --%>
							<textarea id="asd_textarea" name="content" rows="1" maxlength="100" style="heigth: 108px;"></textarea>
							<!-- <label placeholder="답변을 남겨주세요." onfocus="this.placeholder=''" onblur="this.placeholder='답변을 남겨주세요.'" class="asd_label" for="textarea"></label> -->
							<div class="asd_btn_group">
<%-- 								<input type= "hidden" name="replyid" value = "${ask.id}"> --%>
 	 							<button class="asd_regist_bto" id= "addReplyBtn">답변등록</button> <!-- <input class="asd_regist_bto" type= "submit" value ="등록">  -->
							</div>
					</section>
<!-- 					</form> -->
					<div class="asd_length_counter">(0/100)</div>
				</div>
			<!-- 답변목록 -->
			<div id ="rest">
<%-- 			<c:set value="${ask.askreply}" var="reply"/> --%>
<%-- 				<c:forEach items="${reply}" var="r"> --%>
<!-- 					<ul class="asd_reply_list"> -->
<!-- 						<li class= "li_reply_list"> -->
<!-- 							<div class="asd_topper"> -->
<!-- 								<div class="asd_group"> -->
<%-- 									<span>${r.id}</span> --%>
<!-- 								</div> -->
<!-- 							</div> -->
							
<!-- 							<section> -->
<!-- 							<div class="asd_topper_reply"> -->
<%-- 							${r.content} --%>
<!-- 							</div> -->
<!-- 							</section> -->
							
<!-- 							<div class="asd_bottom_btn_group"> -->
<%-- 								<button class="asd_no_click">${r.wdate}</button>					 --%>
<!-- 								<span class="asd_seqarator">|</span> -->
<!-- 								<button data-seqno="3" id="ReplyModifyBtn">수정</button> -->
<!-- 								<span class="asd_seqarator">|</span> -->
<!-- 								<button id="ReplyDeleteBtn">삭제</button> -->
<!-- 							</div> -->
<!-- 						</li> -->
<!-- 					</ul> -->
<%-- 				</c:forEach> --%>
				</div>
				<!-- 댓글 페이지 리스트 출력 -->
				<div class="reply-page-list"></div>
			</section>
		</div>
	</div>
<%@include file="../footer.jsp" %>
</div>

<div id="reply_modal">
	<div id="black-box">
		<p>
		<button id="modalCloseBtn" style="float: right; font_size:25px">&#10062;</button>
		<h1 id="logo">댓글</h1>
		<textarea name="content"></textarea>
		
		<button name="ReplyModifyBtn">댓글수정</button> | <button name="ReplyDeleteBtn">댓글삭제</button>
	</div>
</div>


<script>
function del() {
	var ans = confirm("삭제하시겠습니까?");
	
	if(ans){
		location.href = "/qna/qnalist"
	}

}
</script>
<script type="text/javascript" src="/js/qnaReply.js"></script>    
<script>
// 즉시실행함수(IIFE)
// (function(){
// 	문장;
// })();
var seqno = '<c:out value="${ask.seqno}" />';
var id = '<c:out value="${user.id}" />';
$(document).ready(function(){
	console.log(qnaReplyService);

	console.log("=============");
	console.log("Reply get LIST");
	
	var modal = $("#reply_modal");
	var modal_content = modal.find("textarea[name='content']");
	var currentPage = 1;

	modal.hide();
	
	showList(1);
	
	/* 모달 닫기버튼 */
	$("#modalCloseBtn").on("click", function(e){
		modal.hide();
	});
	
	$(document).on("click", "button[name='update']", function(e){
		var rno = $(this).data("seqno");
		qnaReplyService.get(rno, function(data){
			console.log("REPLY GET DATA");
			console.log("댓글" + rno + "번내용:" + data.content);
			modal_content.val(data.content);
			modal.data("rno", data.qr_seqno);
		});
		modal.show();
	});
	
	/* 댓글 수정버튼 클릭시 */
	$(document).on("click","button[name='ReplyModifyBtn']", function(e){
		console.log("댓글 수정 번호 :" + modal.data("rno"));
		console.log("댓글 수정 내용 :" + modal_content.val());
		
		var reply = {seqno : modal.data ("rno"),
					 content: modal_content.val()};
		
		qnaReplyService.update(reply, function(result){
			alert(result);
			modal.hide();
			showList(currentPage);
		});
		
	});
	
	
	/* 댓글 삭제  버튼 클릭시*/
	$(document).on("click","button[name='ReplyDeleteBtn']", function(e){
		var rno = modal.data("rno");
		console.log("댓글 삭제번호 :" + rno);
		qnaReplyService.remove(rno, function(result){
			alert(result);
			modal.hide();
			showList(currentPage);
		});
	});
	
	
	function showList(page){
		qnaReplyService.getList({bno:seqno, page:page || 1}, function(replyCnt, list){
			
			console.log("댓글 수 :" + replyCnt);
			
			/* 댓글이 새롭게 등록 된 경우 */
			if(page == -1) {
				currentPage = Math.ceil(qnaCnt/5.0);
				showList(currentPage);
				return;
			}
			
			/* 댓글이 없는 경우 */
			if(list == null || list.length==0) {
				$("#rest").html("");
/* 				$(".reply_ul").html(""); */
				return;
			}
			
			/* 댓글이 있는 경우 */
			var str="";
			for(var i=0, len=list.length || 0; i < len; i++) {
				console.log(list[i]);
				str +=  "<ul class='asd_reply_list'>";
				str +=  "	<li class= 'li_reply_list'>";
				str +=  "	<div class='asd_topper'>";
				str +=	"		<div class='asd_group'>";
				str +=  "			<span>"+list[i].id+"</span>";
				str +=	"		</div>";
				str +=	"	</div>";
				str +=	"	<section>";
				str +=	"	<div class='asd_topper_reply'>";
				str +=	"	"+list[i].content+"";
				str +=	"	</div>";
				str +=	"	</section>";
				str +=	"	<div class='asd_bottom_btn_group'>";
				str +=	"	<button class='asd_no_click'>"+list[i].wdate+"</button>	";				
				str +=	"		<span class='asd_seqarator'>|</span>";
				str +=	"		<button data-seqno='"+list[i].qr_seqno+"' name='update'>수정.삭제</button>";
// 				str +=	"		<span class='asd_seqarator'>|</span>";
// 				str +=	"		<button id='ReplyDeleteBtn'>삭제</button>";
				str +=	"	</div>";
				str +=	"	</li>";
				str +=	"</ul>";
			}
			
			/* 백업용 */
/* 			var str="";
			for(var i=0, len=list.length || 0; i < len; i++) {
				console.log(list[i]);
				str += "<li data-rno='"+ list[i].qr_seqno +"'><div class='replyRow'>" + list[i].rn + " | " + list[i].id;
				str += " | " + list[i].wdate + " | " + list[i].content +"</div></li>";
			} */
			
			$("#rest").html(str);
			
			/* $(".reply_ul").html(str); */
			
			showReplyPage(replyCnt, currentPage);
		});
	}

	
	/* 댓글 페이지 리스트 출력 */
	function showReplyPage(replyCnt, currentPage){
		
		var endPage = Math.ceil(currentPage/5.0)*5;
		var startPage = endPage - 4;
		console.log("emdPage :" + endPage )
		
		var prev = startPage != 1;
		var next = false;
		
		if(endPage * 5 >= replyCnt){
			endPage = Math.ceil(replyCnt/5.0); 
		}
		if(endPage * 5 < replyCnt) {
			next = true;
		}
		
		var str = "<ul class='pageUL'>";
		if(prev){
			str +="<li class='page-link'>";
			str +="<a href='" + (startPage - 1) + "'> 이전페이지 </a></li>";			
		}
		
		for(var i=startPage; i <= endPage; i++){
			var active = currentPage == i ? "active" : "";
			str += "<li class='page-link" + active + "'>";
			str += "<a href='" + i + "'>" + i + "</a></li>";
		}
		
		if(next){
			str += "<li class='page-link'>";
			str += "<a href='" + (endPage+1) + "'> 다음페이지</a></li>";
		}
		
		str += "</ul>";
		console.log(str);
		$(".reply-page-list").html(str);
	}
	
	/* 댓글 페이지번호 클릭 시  댓글 리스트 출력*/
	$(".reply-page-list").on("click", "li a", function(e){
		console.log("page click--------------");
		e.preventDefault(); //a태그를 눌러도 href링크로 이동하지 않게
		var clickPage = $(this).attr("href");
		console.log("currentPage:" + clickPage);
		currentPage = clickPage;
		showList(currentPage);
	});
	/* 댓글 등록버튼 클릭시 */
	$("#addReplyBtn").on("click", function(e){
		var comment = document.getElementById("asd_textarea").value;
		
		var reply = {
				seqno: seqno,
				id: id,
				content: comment
		};
		
		qnaReplyService.add(reply, function(result){
			alert("댓글이 등록되었습니다." + result);
			document.getElementById("asd_textarea").value ="";
// 			document.getEleMentById("newLine").innerHTML = "<li>" +reply.comment + "</li>";
			showList(-1);
		});
	});
});
</script>
</body>
</html>