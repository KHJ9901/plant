<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file = "../common.jsp" %>
<link rel="stylesheet" href="/css/detailPage.css">

<div class="wholeDetail">

	<div class="detailHead">
	<c:set value="${mplantdetail}" var="md" />
		<c:forEach items="${md.mpimg}" var="mpimg" >
		
			<c:set value="${mpimg.fileType}" var="mpitype" />
			
			<div class="detailImg">
			
				<c:set value="${fn:substring(mpitype, 0, fn:indexOf(mpitype, '/'))}" var="type" />
				
				<c:if test="${type eq 'image'}">
					<c:set value="${mpimg.fileName}" var="img_file" />
					<img src="/plant/${img_file}">
				</c:if> 
			</div>
		</c:forEach>
		
		<div class="detailHeadTitle">
		
			<div class="detailSubTitle">
			
				<div class="cateHeart">
				
					<div class="categoryWrap">
					
						<div class="detailCategory">회원이 등록한 식물
						</div>
						
						<div class="detailCategory">${md.cate}</div>
						
					</div>
					
					<div class="heartDiv">
						<button type="button" class="heart">♡</button>
					</div>
				</div>
				
				<div class="plantName">
					<div class="kname">${md.name}</div>
				</div>
			
				<div class="advice">
					<div class="adviceText">키우는 난이도가 어땠나요?</div>
					<div class="levelText">
					 난이도 : 
					<c:if test="${md.plevel eq 'e'}">쉬움</c:if>
					<c:if test="${md.plevel eq 'h'}">어려움</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<hr class="firstHr">
	
	<div class="water">
		<h2>물은 이렇게 주세요</h2>
		<div>${md.water}</div>
	</div>
	
	<hr class="secondHr">
	
	<div class="place">
		<h2>이런 장소를 좋아해요</h2>
		<div>${md.place}</div>
	</div>
		
	<hr class="thirdHr">
	
	<div class="moist">
		<h2>온도와 습도는 이렇게 맞춰주세요</h2>
		<div>온도 : ${md.temp}</div>
		<div>습도 : ${md.moist}</div>
	</div>
		
	<hr class="forthHr">
	
	<div class="etc">
		<h2>이런 부분은 더 신경 써주세요</h2>
		<div>${md.etc}</div>
	</div>
		
	<hr class="fifthHr">
	
	<c:set value="${loginUser}" var="user" />
			
	<c:if test="${user.id eq md.id}">
		<a href="/dic/mpModifyForm?seqno=${md.mplant_seqno}">수정</a>
		<a href="/dic/deleteMplant?seqno=${md.mplant_seqno}'">삭제</a>
	</c:if>
	
	<!-- 댓글 등록 폼  -->
	<div>
		<textarea id="content" name="content" 
			<c:if test="${user != null}">placeholder="댓글작성"</c:if>
			<c:if test="${user == null}">placeholder="로그인을 해야 댓글 작성이 가능합니다"</c:if>
			 rows="2" cols="50"></textarea>
			 
		<button id="loginCheck">댓글등록</button>
		
	</div>
	
	<hr>
<!-- 댓글 모달  -->
<div id="reply_modal">
	<div class="modal-content">
		<div class="login">
			<button id="modalCloseBtn">x</button>

			<textarea name="content"> </textarea>
			
			<button id="replyModifyBtn">댓글수정</button>
			<button id="replyDeleteBtn">댓글삭제</button>
		</div>
	</div>
</div>

	<!-- 댓글 리스트 출력 영역 -->
	<div id="reply-list">
		<ul class="reply_ul">
			<li data-rno='1'>
			</li>
		</ul>
	</div>
	
	<!-- 댓글 페이지 리스트 출력  -->
	<div class="reply-page-list">
	
	</div>

</div>
<script>


	function del_confirm(seqno) {
		var rs = confirm('정말로 삭제하시겠습니까?');
		if(rs) {
			location.href="boardDelete?seqno=" + seqno;
		}
	}
</script>
	
<script type="text/javascript" src="/js/MpReply.js"></script>

<script>
var seqno = '<c:out value="${md.mplant_seqno}" />';
var id = '<c:out value="${user.id}" />';

$(document).ready(function(){
	console.log(replyService);
	console.log("==================구분선===================");
	console.log("Reply get LIST");
	
	//댓글 모달창
	var modal = $("#reply_modal");
	var modal_content = modal.find("textarea[name='content']");
	
	modal.hide();	
	showList(1);
	
	var currentPage = 1;
	
	/* 댓글 목록  */
	function showList(page) {
		replyService.getList({bno:seqno, page:page || 1}, function(replyCnt, list) {
//			for(var i=0, len=list.length || 0; i < len; i++) {
//				console.log(list[i]);
//			}
			console.log("댓글 수 : " + replyCnt);
			/* 댓글이 새롭게 등록 된 경우 */
			if(page == -1) {
				currentPage = Math.ceil(replyCnt/5.0);
				showList(currentPage);
				return;
			}
			
			/* 댓글이 없는 경우 */
			if(list == null || list.length == 0) {
				$(".reply_ul").html("");
				return;
			}
			
			console.log("==================구분선===================");
			/* 댓글이 있는 경우 */
			var str="";
			for(var i=0, len=list.length || 0; i < len; i++) {
				console.log(list[i]);
				str += "<li data-rno='" + list[i].mpr_seqno + "'><div class='replyRow'>" + list[i].rn + " | " + list[i].id ;
				str += " | " + list[i].wdate + " | " + list[i].content + "</div></li>";
			}
			$(".reply_ul").html(str);
			
			showReplyPage(replyCnt, currentPage);
		});
	}
	
	//showReplyPage(10);
	
	// 댓글 페이지 리스트 출력
	function showReplyPage(replyCnt) {
		
		//var currentPage = 1;
		
		var endPage = Math.ceil(currentPage/5.0)*5; 
		//무조건 윗번호로 반올림함
		
		var startPage = endPage - 4;
		console.log("endPage : " + endPage);
		
		//startNum 과 endNum 은 페이지의 값 인덱스 번호
		var prev = startPage != 1;
		var next = false;
		
		if(endPage * 5 >= replyCnt) {
			endPage = Math.ceil(replyCnt/5.0);
		}
		if(endPage * 5 < replyCnt) {
			next = true;
		}
		
		var str = "<ul class='pageUl' style='display: -webkit-inline-box; letter-spacing: 20px;'>";
		if(prev) {
			str += "<li class='page-link'>";
			str += "<a href='" + (startPage - 1)+"'> 이전페이지 </a><li>";
		}
		
		for(var i=startPage; i <= endPage; i++){
			var active = currentPage == i ? "active" : "";
			str += "<li class='page-link " + active + "'>";
			str += "<a href='" + i + "'>" + i + "</a></li>";
		}
		
		if(next) {
			str += "<li class='page-link'>";
			str += "<a href='" + (endPage + 1) + "'> 다음페이지 </a></li>";
		}
		
		str += "</ul>";
		console.log(str);
		$(".reply-page-list").html(str);
	}
	
	/* 댓글 페이지번호 클릭 시 */
	$(".reply-page-list").on("click", "li a", function(e){
		console.log("페이지 번호 클릭=================================");
		e.preventDefault(); //a테그를 눌러도 href 링크로 이동하지 않게
		var clickPage = $(this).attr("href");
		console.log("currentPage : " + clickPage);
		currentPage = clickPage;
		showList(currentPage);
	});
	
	/* 모달 닫기 버튼  */
	$("#modalCloseBtn").on("click", function(e){
		modal.hide();	
	});

	/* 댓글 삽입 */
	$("#loginCheck").on("click", function(e) {
		if(${user == null}) {
			console.log("*******************************************");
			alert("로그인 후 댓글 작성이 가능합니다.");
			
		} else if(${user != null}) {
			console.log("***************여기까지 왔니?*****************");
				var content = document.getElementById("content").value;
				
				var reply = {
					content : content,
					mplant_seqno : seqno,
					id : id
				};
				
				replyService.add(reply, function(result){
					alert("댓글이 등록되었습니다." + result);
					document.getElementById("content").value = "";
					showList(-1);
					//document.getElementById("newLine").innerHTML = "<li>" + reply.comment + "</li>";
				})
			}
	});

	var rno;
	/* 수정 창 띄우기 */
	$(".reply_ul").on("click", "li", function(e){
		
		rno = $(this).data("rno");
		
		console.log("무엇?"+rno);
		replyService.getOneReply(rno, function(data){
			console.log("==================REPLY GET DATA==================");
			console.log("댓글"+rno+"번 내용 : " + data.content);
			modal_content.val(data.content);
			modal.data("rno", data.seqno); //input hidden 같은 역할
		});
		modal.show();
	
	});
	
	/* 댓글 수정   */
	$("#replyModifyBtn").on("click", function(e){
		console.log("댓글 수정 번호 : " + rno);
		console.log("댓글 수정 내용 : " + modal_content.val());
		
		var reply = {mpr_seqno : rno, 
					 content : modal_content.val()};
		
		replyService.updateReply(reply, function(result){
			alert(result);
			modal.hide();
			showList(currentPage);
		});
	});
	
	/* 댓글 삭제  */
	$("#replyDeleteBtn").on("click", function(e){
		console.log("댓글 삭제 번호 : " + rno);
		
		replyService.deleteReply(rno, function(result){
			alert(result);
			modal.hide();
			showList(currentPage);
		});
	});
});
</script>
<%@ include file = "../footer.jsp" %>