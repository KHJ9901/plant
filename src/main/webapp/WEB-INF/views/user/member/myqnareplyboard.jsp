<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link rel="stylesheet" href="/css/myboard.css">


<div class="row">
 <div class="boardupdown">
  <div class="leftcolumn">
	<div class="bdbox">
		<div class="bdtitle">
		<c:set value="${loginUser}" var="user"/>
			
		 	<div>
				<h1><a href="mypage.jsp"></a>${user.id}</h1>
				<img src ="/img/plant.png" width="80" height="80" alt="프로필사진" class="border-sub">
			</div>
			<div class="membdbt">
				<div class="subBt">
					<div class="regDel">
						<button class="membdbt1" type="button" onclick="">등록</button>
					</div>	
						<div class="sort_area">
						<a href="/me/myboard" class="link_sort1">작성한글</a>
						<a href="/me/myreply" class="link_sort2">댓글단글</a>
						<a href="/me/mylike" class="link_sort3">좋아요한 글</a>
					</div>
					<div class = "boardarea">
						<a href="/me/myreply" class="link_sort1">자유게시판</a>
						<a href="/me/myqnareplyboard" class="link_sort2">질문게시판</a>
					</div>
					<br>
				</div>
				
				<div class="listName">
				 	<h3>댓글 쓴 글 목록</h3>
				</div>
				
			</div>
		</div>
	
		<div class="bdbody">
			<div class="bdlist">
					<div class="top">
						<div class="memBdNum">번호</div>
						<div class="memBdTitle">제목</div>
						<!-- <div class="memBdName">작성자이름</div> -->
						<div class="memBdId">작성자ID</div>
						<div class="memBdWdate">작성일</div>
						<div class="memBdCount">조회수</div>
					</div>
					
						<c:forEach items="${board}" var="board" >
						<div class="bdBottom">
							<div class="memBdNum">${board.rn}</div>
							<div class="memBdNum"><a href="/bo/boardDetail?seqno=${board.seqno}">${board.content}</a></div>
							<%-- <div class="memBdName">${board.name}</div> --%>
							<div class="memBdId">${board.id}</div>
							<div class="memBdWdate">${board.wdate}</div>
							<div class="memBdCount">${board.count}</div>
							</div>
						</c:forEach>
			</div>
			<div class="bdpage">
				<a href="#" class="bt_prev"></a>
				<a href="#" class="num">1</a>
				<a href="board2.html" class="num">2</a>
				<a href="board2.html" class="num">3</a>
				<a href="board2.html" class="num">4</a>
				<a href="board2.html" class="num">5</a>
				<a href="board2.html" class="bt_next">></a>
			</div>
		</div>	
    </div>
  </div>
 </div>
</div>

<%@ include file="../footer.jsp" %>

</body>
</html>



