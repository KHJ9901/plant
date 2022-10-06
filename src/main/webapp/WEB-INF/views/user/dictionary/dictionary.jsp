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
			<h1>식물사전</h1>
		</div>
		
		<div class="headSubTitle">본인에게 어울리는 식물을 식물사전에서 검색해 보아요
		</div>
	</div>
	
	<div class="dicSearch">
		<form name="dicSearchForm" autocomplete="off">
			<input type="text" name="searchText" placeholder="검색하세요" value="" />
			<input type="button" onclick="getSearchList()" class="searchBt" value="검색" />				
		</form>
	</div>

<script>
function goAction() {
	document.forms["dicSearchForm"].submit();
}
</script>

<script>
var seqno = '<c:out value="${dic.seqno}" />';


$(document).ready(function(){
	
	showResult(1);
	
	var currentPage = 1;
	
	function showResult(page) {
		plantSearch.searchResult(bno:seqno, page:page || 1}, function(resultCnt, list) {
			$("#dicSearchBt").on("click", "div", function(e){
				console.log("검색 쳐찍어!!!*********************************");

				/* 검색결과 없는 경우 */
				if(list == null || list.length == 0) {
					$(".searchContentWrap").html("");
					return;
				}
				
				console.log("==================구분선===================");
				/* 내용이 있는 경우 */
				var str="";
				for(var i=0, len=list.length || 0; i < len; i++) {
					console.log(list[i]);
					str += "<div data-rno='" + list[i].seqno + "'class='dicSearchResult'>" + list[i].kname + "</div>" ;
				}
				$(".searchContentWrap").html(str);
				
				showReplyPage(replyCnt, currentPage);
			});
		});
	}
});
</script>

	<div class="searchBody">
		<div class="searchContentWrap">
		
		</div>
		
<%-- 			<c:forEach items="${diction}" var="dic">
				<a href="/dic/dicDetail?seqno=${dic.seqno}">
				
					<div class="searchContent">
							<c:set value="${dic.dicthumb.fileType}" var="filetype" />
								<c:set value="${fn:substring(filetype, 0, fn:indexOf(filetype, '/')) }" var="type" />
								
								<c:if test="${type eq 'image'}">
									<c:set value="${dic.dicthumb.fileName}" var="thumb_file" />
									<img src="/plant/thumb/${thumb_file}">
								</c:if>
								<p>${dic.kname}</p>
					</div>
					
				</a>
			</c:forEach> --%>
	<p>총레코드 개수 : ${pageMaker.total}</p>
	
	</div>
		<div class="pagination">
		<c:if test="${pageMaker.prev}">
			<a href="/dic/dictionary?currentPage=${pageMaker.startPage-1}&rowPerPage=${pageMaker.cri.rowPerpage}">&laquo;</a>
		</c:if>
		
		<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
			<a href="/dic/dictionary?currentPage=${num}&rowPerPage=${pageMaker.cri.rowPerpage}"
			   class="${pageMaker.cri.currentPage == num ? "active=" : "" }">${num}</a>
		</c:forEach>
		
		<c:if test="${pageMaker.next}">
			<a href="/dic/dictionary?currentPage=${pageMaker.endPage+1}&rowPerPage=${pageMaker.cri.rowPerpage}">&raquo;</a>
		</c:if>
	</div>
	
</div>

<%@ include file = "../footer.jsp" %>