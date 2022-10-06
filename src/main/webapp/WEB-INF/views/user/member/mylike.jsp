<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common.jsp" %>

<link rel="stylesheet" href="/css/myboard.css">


<div class="row">
 <div class="boardupdown">
  <div class="leftcolumn">
	<div class="bdbox">
		<div class="bdtitle">
		 	<div>
				<h1><a href="mypage.jsp">닉네임</a></h1>
				<img src ="/img/plant.png" width="80" height="80" alt="프로필사진" class="border-sub">
			</div>
			<div class="membdbt">
				<div class="subBt">
					<div class="sort_area">
						<a href="/member/myboard.jsp" class="link_sort1">작성한글</a>
						<a href="/member/myreply.jsp" class="link_sort2">댓글단글</a>
						<a href="/member/mylike.jsp" class="link_sort3">좋아요한 글</a>
					</div>
					<div class = "boardarea">
						<a href="/member/myboard.jsp" class="link_sort1">분양게시판</a>
						<a href="/member/myreply.jsp" class="link_sort2">질문게시판</a>
						<a href="/member/mylike.jsp" class="link_sort3">나의식물</a>
					</div>
				</div>
				
				<div class="listName">
				 	<h3>좋아요누른글 목록</h3>
				</div>
				
			</div>
		</div>
	
		<div class="bdbody">
			<div class="bdlist">
					<div class="top">
						<input type="checkbox" name="memselect" value=""> 모두선택
						<div class="memBdNum">번호</div>
						<div class="memBdTitle">제목</div>
						<div class="memBdWdate">작성일</div>
						<div class="memBdName">작성자이름</div>
						<div class="memBdId">작성자ID</div>
						<div class="memBdCount">조회수</div>
					</div>
					
					<div class="bdBottom">
						<div>
							<input class="bdBottomBt" type="checkbox" name="memselect" value="">
						</div>
						<div class="memBdNum">123</div>
						<div class="memBdTitle">글 제목란</div>
						<div class="memBdWdate">2022-08-01</div>
						<div class="memBdCount">33</div>
						<div class="memBdName">식물플래너</div>
						<div class="memBdId">planner22</div>
					</div>
		
			</div>
			<div class="bdpage">
				<a href="#" class="bt_prev"><</a>
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



