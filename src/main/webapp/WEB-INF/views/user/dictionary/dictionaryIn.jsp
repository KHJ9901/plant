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
			<h1>실내용 식물사전</h1>
		</div>
		
		<div class="headSubTitle">실내 식물을 사전에서 검색해 보아요
		</div>
	</div>
	
	<div class="dicSearch">
		<form name="dicsearch" method="post" action="/dic/dictionaryIn">
			<input type="hidden" name="currentPage" value="${pageMaker.cri.currentPage}" />
			
			<select name="searchField">
				<option value="kname" 
					<c:if test="${pageMaker.cri.searchField == 'kname'}">selected</c:if>>한글이름
				</option>
				
				<option value="ename"
					<c:if test="${pageMaker.cri.searchField == 'ename'}">selected</c:if>>영문이름
				</option>
			</select>
			
			<input name="searchText" class="searchText" type="text" placeholder="검색하세요" 
				value="${pageMaker.cri.searchText}">
				
			<input class="searchBt" type="button" value="검색" onclick="document.forms['dicsearch'].submit()">
		</form>
	</div>
	
	<script>
		function goAction() {
			document.forms["dicSearchForm"].submit();
		}
	</script>

	<div class="searchBody">
		<div class="searchContentWrap">

 			<c:forEach items="${dictionin}" var="dic">
				<a href="/dic/dicDetail?seqno=${dic.seqno}">
					<div class="searchContent">
							<c:set value="${dic.filetype}" var="filetype" />
								<c:set value="${fn:substring(filetype, 0, fn:indexOf(filetype, '/')) }" var="type" />
								
								<c:if test="${type eq 'image'}">
									<c:set value="${dic.thumbfilename}" var="thumb_file" />
									<img src="/upload/tmp/${thumb_file}">
								</c:if>
								<p>${dic.kname}</p>
					</div>
				</a>
			</c:forEach>
		</div>
		<p>총레코드 개수 : ${pageMaker.total}</p>
	</div>
		<div class="pagination">
		<c:if test="${pageMaker.prev}">
			<a href="/dic/dictionaryIn?currentPage=${pageMaker.startPage-1}&rowPerPage=${pageMaker.cri.rowPerpage}">&laquo;</a>
		</c:if>
		
		<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
			<a href="/dic/dictionaryIn?currentPage=${num}&rowPerPage=${pageMaker.cri.rowPerpage}"
			   class="${pageMaker.cri.currentPage == num ? "active=" : "" }">${num}</a>
		</c:forEach>
		
		<c:if test="${pageMaker.next}">
			<a href="/dic/dictionaryIn?currentPage=${pageMaker.endPage+1}&rowPerPage=${pageMaker.cri.rowPerpage}">&raquo;</a>
		</c:if>
	</div>		


	</div>
	
</div>

<%@ include file = "../footer.jsp" %>