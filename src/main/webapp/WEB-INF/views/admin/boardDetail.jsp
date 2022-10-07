<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="mgHeader.jsp" %>

<%@ include file="mgMenu.jsp" %>
<body>

<div class="row">
  <div class="leftcolumn">
    <!-- <div class="card"> -->
	<div class="bdbox">
		<div class="bdtitle">
			<strong>1번째 글</strong>
			<div class="membdbt">
				<button class="membdbt1" type="button"
						onclick="location.href='memboard.jsp'">목록</button>
			</div>
		</div>
		
	<div class="bdbody">
	
			<div class="bdlist">
					<div class="top">
						<div class="bdDetailTitle">제목</div>
					</div>
					
					<div class="bdDetailSub">
						<div class="bdDetailWdate">작성일자: 2022-08-01</div>
						<div class="bdDetailCount">조회수 : 22</div>
						<div class="bdDetailName">작성자 : 관리자</div>
					</div>
					<div class="bdDetailContentWrap">
						<div class="bdDetailContent" id="post">
						adfasdfdsfsdffasdfasdfsdafsdafsdafsdfasdfasdfasfdasfsadfsdafdasfsdafadsfsdaadfasdfdsfsdfsdasdafsdafsdafasdfsdafsdafsdafsdafdasfsdafsdafsdfasdfasdfasfdasfsadfsdafdasfsdafadsadfasdfdsfsdfsdasdafsdafsdafasdfsdafsdafsdafsdafdassdasdafsdafsdafasdfsdafsdafsdafsdafdasfasdfsdafsdafsdafsdfasdfasdfasfdasfsadfsdafdasfsdafads
						adfasdfdsfsdfsdasdafsdafsdafasdfsdafsdafsdafsdafdas
						fasdfsdafsdafsdafsdfasdfasdfasfdasfsadfsdafdasfsdafads
						adfasdfdsfsdfsdasdafsdafsdafasdfsdafsdafsdafsdafdas
						fasdfsdafsdafsdafsdfasdfasdfasfdasfsadfsdafdasfsdafads
						adfasdfdsfsdfsdasdafsdafsdafasdfsdafsdafsdafsdafdas
						fasdfsdafsdafsdafsdfasdfasdfasfdasfsadfsdafdasfsdafads
						
						</div>
					</div>
			</div>
			
			<hr>

			<div class="reply">
					<div class="re_num">1번</div>
					
					댓글 작성자 : 식물플래너
					작성일자 : 2022-07-31
					<hr>
			</div>
			
		<div class="listbt">
			<a href="/khjweb/board/bdDeleteProc.jsp?numh=">삭제</a>
		</div>
		
		
	</div>	

  </div>
</div>
</div>
<%@ include file="mgFooter.jsp" %>

</body>
</html>



