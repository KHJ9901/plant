<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
<%@include file = "../common.jsp" %>

<link rel="stylesheet" href="/css/ask.css">

<div class="wholePage">
	<div class="headContainer">
		<div class="headCategory">
			<a href="/qna/qnalist">질문·답변</a>  
			<a href="/bo/board">자유게시판</a>
		</div>
	</div>
	
	<div class="headContainer2">
		<div class="headNotice">
			<h1>질문·답변</h1>
			<div class="headSubTitle">질문을 통해 문제를 해결하고 답변을 통해 지식을 공유해 주세요!</div>
			
			<!-- 버튼이면 class="wrap" -->
			<!-- 검색이면 class="searchForm" -->
			<c:if test="${loginuser != null}">
			<div class="wrap">
			<a href="/qna/qnanew"  class="mybutton">질문등록</a>
			</div>
			</c:if>
		</div>
	</div>



	<section>
		<div class="container3">
			<ul class="ulline">
				<c:forEach items="${ask}" var="ask">
					<li class="ak_item">
						<div class="li_item">
							<a href="/qna/detail?seqno=${ask.getSeqno()}">
								<p class="li_content">${ask.getContent()}</p>
							</a>
							<p class="li_status">
								<span class="li_date">${ask.getWdate()}</span> 
								<span class="li_date2">댓글${ask.getReply()}</span>
								<span class="li_date2">조회수${ask.getCount()}</span>
							</p>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</section>
			<div class="ask_pagination">
				<c:if test="${pageMaker.prev}">
					<a href="/qna/qnalist?currentPage=${pageMaker.startPage-1}&rowPerpage=${pageMaker.cri.rowPerpage}">&laquo;</a>						
				</c:if>
				  
				 <c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
					 <a href="/qna/qnalist?currentPage=${num}&rowPerpage=${pageMaker.cri.rowPerpage}"
					 class="${pageMaker.cri.currentPage == num? "active" : " " }">${num}</a>
				 </c:forEach>
				 <c:if test="${pageMaker.next}">
				 	<a href="/qna/qnalist?currentPage=${pageMaker.endPage+1}&rowPerpage=${pageMaker.cri.rowPerpage}">&raquo;</a>
				 </c:if>
			</div>

</div>

<%@include file="../footer.jsp" %>