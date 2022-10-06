<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

   
<%@include file = "../common.jsp" %>


<link rel="stylesheet" href="/css/ask.css">

<body class="asd_body">
<%@include file = "../menu.jsp" %>
<div class="asd_page_container">
	<div class="asd_cotainner">
		<div class="asd_con_detail">
			<section class="asd_detail_title">
				<h1>자유게시판</h1>
			</section>
			<c:set value="${board}" var="board" />
			<c:set value="${loginUser}" var="user"/>
			<section class="asd_detail_form">
				<div class="asd_main_title">
					<div class="asd_group">
						<span class="asd_title">${board.title}</span>
					</div>
					
					<div class="asd_group">
					<span class="asd_writer">${board.name}</span>
					<span class="asd_seqarator">|</span>
					<span class="asd_date">${board.wdate}</span>
					</div>
				</div>
				<hr class="asd_line">
				
				<div class="asd_content">${board.content}</div>
				<div class="asd_bottom_group">
					<button class="asd_qna">
						<br class="asd_down_only">
					</button>
					
				</div>
				<c:if test="${user.id eq board.id}">
				<div class="asd_report">
				<a href="/bo/boardDetail?seqno=${board.seqno}&page=modify">수정</a>
				<span class="asd_seqarator">|</span>
				<a href="/bo/boardDelete?seqno=${board.seqno}">삭제</a>
				</div>
				</c:if>				
			</section>
			<section class="asd_reply">
			<hr class="asd_line">
				<div class="asd_reply_count">
					<span class="asd_reply_title">답변</span>
					<span class="asd_reply_count">${board.reply}</span>
				</div>
				<div class="asd_input_item">
					<form method="post" action="reply">
						<section style="position: relative;">
							<input type="hidden" name="asd_board_seqno"	value ="${board.seqno}">
							<textarea id="asd_textarea" name="asd_content" rows="1" maxlength="100" style="heigth: 108px;"></textarea>
							<!-- <label placeholder="답변을 남겨주세요." onfocus="this.placeholder=''" onblur="this.placeholder='답변을 남겨주세요.'" class="asd_label" for="textarea"></label> -->
							<div class="asd_btn_group">
								<input type= "hidden" name="asd_replyid" value = "${user.id}">
	 							<input class="asd_regist_bto" type= "submit" value ="등록"> 
							</div>
						</section>
					</form>
					<div class="asd_length_counter">(0/100)</div>
				</div>		
				
				<!-- 답변목록 -->
			<c:set value="${board.boardreply}" var="reply"/>
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
							${r.content}
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
</body>
</html>