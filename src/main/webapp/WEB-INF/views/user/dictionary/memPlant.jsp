<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file = "../common.jsp" %>
<link rel="stylesheet" href="/css/dictionary.css">

<div class="wholePage">
	<div class="headContainer">
	
		<div class="headCategory">
			<%@ include file  = "../dictionary/dicCommonBar.jsp" %>
		</div>
		
	</div>
	<div class="headContainer2">
		<div class="headNotice">
			<h1>회원이 등록한 식물</h1>
		</div>
		
		<div class="headSubTitle">회원들이 등록한 식물이에요. 참고하시기 바랍니다 
		</div>
		<c:set value="${loginUser}" var = "login"/>
		<%-- <c:if test="${loginuser != null }"> --%>
			<div class="wrap">
				<div class="mybutton" onclick="loginCheck()">식물등록</div>
			</div>
		<%-- </c:if> --%>
		
	</div>
	
	<div class="dicSearch">
		<form name="dicsearch" method="post" action="/dic/mplant">
			<input type="hidden" name="currentPage" value="${pageMaker.cri.currentPage}" />
			
			<input name="searchText" class="searchText" type="text" placeholder="검색하세요" 
				value="${pageMaker.cri.searchText}">
				
			<input class="searchBt" type="button" value="검색" onclick="document.forms['dicsearch'].submit()">
		</form>
	</div>
	
	<script>
		function goAction() {
			document.forms["mpSearchForm"].submit();
		}
		
		function loginCheck() {
			if(${login == null}) {
				alert("식물을 등록하기 위해선 로그인을 하셔야합니다.");
			} else {
				location.href="mpInsertForm";
			}
		}
	</script>

	<div class="searchBody">
		<div class="searchContentWrap">

 			<c:forEach items="${mplant}" var="mp">
				<a href="/dic/mplantDetail?seqno=${mp.mplant_seqno}">
					<div class="searchContent">
							<c:set value="${mp.filetype}" var="filetype" />
								<c:set value="${fn:substring(filetype, 0, fn:indexOf(filetype, '/')) }" var="type" />
								
								<c:if test="${type eq 'image'}">
									<c:set value="${mp.thumbfilename}" var="thumb_file" />
									<img src="/upload/tmp/${thumb_file}">
								</c:if>
								<p>${mp.name}</p>
					</div>
				</a>
			</c:forEach>
		</div>
		<p>총레코드 개수 : ${pageMaker.total}</p>
	</div>
		
		<div class="pagination">
			<c:if test="${pageMaker.prev}">
				<a href="/dic/mplant?currentPage=${pageMaker.startPage-1}&rowPerPage=${pageMaker.cri.rowPerpage}">&laquo;</a>
			</c:if>
			
			<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
				<a href="/dic/mplant?currentPage=${num}&rowPerPage=${pageMaker.cri.rowPerpage}"
				   class="${pageMaker.cri.currentPage == num ? "active=" : "" }">${num}</a>
			</c:forEach>
			
			<c:if test="${pageMaker.next}">
				<a href="/dic/mplant?currentPage=${pageMaker.endPage+1}&rowPerPage=${pageMaker.cri.rowPerpage}">&raquo;</a>
			</c:if>
		</div>			
	</div>
</div>

<%@ include file = "../footer.jsp" %>