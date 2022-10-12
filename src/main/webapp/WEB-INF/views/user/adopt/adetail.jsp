<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="../common.jsp" %>
	<link rel="stylesheet" href="/css/ask.css">
	<link rel="stylesheet" href="/css/adetail.css">
	<link rel="stylesheet" href="/css/adoptReview.css">
</head>

<body>
<div class="asd_page_container">
	<div class="asd_cotainner">
		<div class="asd_con_detail">
			<section class="asd_detail_title">
				<h1>분 양</h1>
			</section>
			<c:set value="${adopt}" var="adopt" />
			<c:set value="${loginUser}" var="user" />
			<section class="asd_detail_form">
				<div class="asd_main_title">
					<div class="asd_group">
						<span class="asd_title">${adopt.id}</span>
					</div>
					
					<div class="asd_group">				
						<span class="asd_date">${adopt.wdate}</span>
						<span class="asd_count">${adopt.count}</span>
					</div>
				</div>
				
				<hr class="asd_line">
				<div class="asd_station">
				<span><img src="/img/map.png" width="15px" alt="station">${adopt.station}</span></div>
				
				<!-- 내용 -->
				<div class="asd_content">${adopt.content}</div>
				
				<!--첨부파일-->
				<div class="adoptfile">
				<c:set value="${adopt.adoptFile}" var="adoptfile" />
				<c:if test ="${adoptfile != null}">
					<c:forEach items="${adoptfile}" var="file">
						<form class="ask_file" name="filedown" method="post" action="/plant/file/fileDown">
						<input type="hidden" name ="filename" value="${file.filename}">
					  	<input type="hidden" name ="savefilename" value="${file.savefilename}">
					  	<input type="hidden" name ="filepath" value="${file.filepath}">
					  	<input type="hidden" name="filesize" value="${file.filesize}">
					
							<c:set value="${file.filetype}" var="filetype" />
							<c:set value="${fn:substring(filetype, 0, fn:indexOf(filetype,'/'))}" var="type"/>
					
							<c:if test="${type eq 'image'}">
								<c:set value="${file.thumbnail.fileName}" var="thumb_file" />
								<img src="/upload/thumbnail/${thumb_file}">
							</c:if>
							<!-- <input type="submit" value="다운로드"> -->
						</form>
					</c:forEach>
				</c:if>				
				</div>
				<hr class="asd_line">
				<div class="asd_info">
					<span class="data">식물</span>${adopt.pname}<br>
					<span class="data">수분</span>${adopt.water}<br>
					<span class="data">장소</span>${adopt.place}<br>
					<span class="data">온도</span>${adopt.temp}<br>
					<span class="data">습도</span>${adopt.moist}<br>
				</div>
				
				<div class="asd_report">
				<c:if test="${user.id eq adopt.id}">
					<button class="myButton" onclick="location.href='/adopt/adetail?seqno=${adopt.seqno}&page=aModify'">수정</button> 
					<button class="myButton" onclick="del_confirm('${adopt.seqno}')">삭제</button>
				</c:if>				
				</div>
		
				
			</section>
			<section class="asd_reply">
			<hr class="asd_line">
				<div class="asd_reply_count">
					<span class="asd_reply_title">댓글</span>
					<%-- <span class="asd_reply_count">${adopt.adoptreply}</span> --%>
				</div>
				<div class="asd_input_item">
						<section style="position: relative;">
							<textarea id="asd_textarea" name="content" rows="1" maxlength="100" style="heigth: 108px;"></textarea>
							<div class="asd_btn_group">
	 							<button id="addReplyBtn" class="asd_regist_bto">등록</button>
							</div>
					</section>
					<div class="asd_length_counter">(0/100)</div>
				</div>
				
			<!-- 댓글 리스트 출력 영역  -->
			<div id="reply-list">
				<ul class="reply_ul">
					<li data-rno='15'>
						<div>작성자 | 작성일 | 댓글내용</div>
					</li>
				</ul>
			</div>
			
			<!-- 댓글 페이지 리스트 출력 -->
			<div class="reply-page-list"></div>
				
			<!-- 답변목록 -->
			<c:set value="${adopt.adoptreply}" var="reply"/>
				<c:forEach items="${reply}" var="r">
					<ul class="asd_reply_list">
						<li class= "li_reply_list">
							<div class="asd_topper">
								<div class="asd_group">
									<span>${r.id}</span>
								</div>
							</div>
							
							<section>
							<div class="asd_topper_reply">
							${r.comment}
							</div>
							</section>
							
							<div class="asd_bottom_btn_group">
								<button class="asd_no_click">${r.wdate}</button>					
								<span class="asd_seqarator">|</span>
								<button>수정</button>
								<span class="asd_seqarator">|</span>
								<button>삭제</button>
							</div>
						</li>
					</ul>
				</c:forEach>
			</section>
		</div>
	</div>
<%@include file="../footer.jsp" %>
</div>

 		<div id="reply_modal">
			<div class="modal-content">
				<div class="container">
					<button id="modalCloseBtn" style="float:right">&#10062;</button>
					<textarea name="content"></textarea>
					<button id="replyModifyBtn">수정</button>	
					<button id="replyDeleteBtn">삭제</button>	
				</div>
			</div>	
		</div>
	

<script>
function del_confirm(seqno){
	var rs = confirm('정말로 삭제하시겠습니까?');
	if(rs){
		location.href = "/adopt/adelete?seqno=" + seqno;
	}
}
</script>

<script type="text/javascript" src="/js/adopt_reply.js"></script>
	
<script>
/*즉시실행함수(IIFE)
(function(){
	문장
})();
*/
var seqno = '<c:out value="${adopt.seqno}" />';
var id = '<c:out value="${user.id}" />';

$(document).ready(function(){
	console.log(adoptReplyService);
	
	console.log("============");
	console.log("adoptReply get LIST");
	
	var modal = $("#reply_modal");
	var modal_content = modal.find("textarea[name='content']");
	var currentPage = 1;
	
	modal.hide();
	
	showList(1);
	
	/*모달 닫기 버튼*/
	$("#modalCloseBtn").on("click", function(e){
		modal.hide();
	});
	
	$(".reply_ul").on("click", "li", function(e){
		
		var rno = $(this).data("rno");
		
		adoptReplyService.get(rno, function(data){
			console.log("REPLY GET DATA");
			console.log("댓글 " + rno + "번내용:" + data.content);
			modal_content.val(data.content);
			modal.data("rno", data.seqno);
		});
		modal.show();
	});
	
	
	/*댓글 등록 버튼*/
	$("#addReplyBtn").on("click", function(e){
		var comment = document.getElementById("asd_textarea").value;
		
		var adoptreply = {
							adoptNo:seqno,
							id:id,
							comment:comment
		};
		
		adoptReplyService.add(adoptreply, function(result){
			alert("댓글이 등록되었습니다."+result);
			document.getElementById("asd_textarea").value = "";
			//document.getElementById("newLine").innerHTML = "<li>" + reply.comment + "</li>";
			showList(-1);
		});
	});
	
	/*댓글 수정 버튼 클릭 시*/
	$("#replyModifyBtn").on("click", function(e){
		console.log("댓글 수정 번호:" + modal.data("rno"));
		console.log("댓글 수정 내용:" + modal_content.val());
		
		var adoptreply = {seqno : modal.data("rno"),
					      content : modal_content.val()};
		
		adoptReplyService.update(adoptreply, function(result){
			alert(result);
			modal.hide();
			showList(1);
		});
	});
	
	/*댓글 삭제버튼 클릭 시*/
	$("#replyDeleteBtn").on("click", function(e){

		var rno = modal.data("rno");
		
		console.log("댓글 삭제 번호:" + modal.data("rno"));
		
		adoptReplyService.remove(rno, function(result){
			alert(result);
			modal.hide();
			showList(1);
		});
	});

	
	function showList(page){
		adoptReplyService.getList({bno:seqno, page:page || 1}, function(replyCnt, list){
			
			console.log("댓글수:"+replyCnt);
			
			/*댓글이 새롭게 등록된 경우*/
			if(page == -1){
				currentPage = Math.ceil(replyCnt/5.0);
				showList(currentPage);
				return;
			}
			
			/*댓글이 없는 경우*/
			if(list == null || list.length == 0){
				$(".reply_ul").html("");
				return;
			}
			
			/*댓글이 있는 경우*/
			var str="";
			for(var i=0, len=list.length || 0; i < len; i++){
				console.log(list[i]);
				str += "<li data-rno='" + list[i].seqno + "'><div class='replyRow'>" + list[i].rn + " | " + list[i].id;
				str += " | " + list[i].wdate + " | " + list[i].content + "</div></li>"
			}
			
			$(".reply_ul").html(str);
			
			showReplyPage(replyCnt);
		});
	}
	
	//showReplyPage(18);
	
	/*댓글 페이지 리스트 출력*/
	function showReplyPage(replyCnt){
		
		//var currentPage = 1;
		
		var endPage = Math.ceil(currentPage/5.0)*5;
		var startPage = endPage - 4;
		console.log("endPage:"+endPage);
		
		var prev = startPage != 1;
		var next = false;
		
		if(endPage * 5 >= replyCnt){
			endPage = Math.ceil(replyCnt/5.0);
		}
		if(endPage * 5 < replyCnt){
			next = true;
		}
		
		var str = "<ul class='pageUL'>";
		if(prev){
			str += "<li class'page-link'>";
			str += "<a href='" + (startPage-1) + "'> 이전</a></li>";
		}
		
		for(var i=startPage; i<=endPage; i++){
			var active = currentPage == i ? "active" : "";
			str += "<li class='page-link " + active + "'>";
			str += "<a href='" + i + "'>" + i + "</a></li>";
		}
		
		if(next){
			str += "<li class='page-link'>";
			str += "<a href='" + (endPage+1) + "'> 다음</a></li>";
		}
		
		str += "</ul>";
		console.log(str);
		$(".reply-page-list").html(str);
	}
	
	/*댓글 페이지번호 클릭 시 댓글 리스트 출력*/
	$(".reply-page-list").on("click", "li a", function(e){
		console.log("page click==========");
		e.preventDefault(); //a태그를 눌러도 href 링크로 이동하지 않게
		var clickPage = $(this).attr("href");
		console.log("currentPage:" + clickPage);
		currentPage = clickPage;
		showList(currentPage);
	});
});
</script>

</body>
</html>