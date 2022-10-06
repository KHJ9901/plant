<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="../common.jsp" %>
	<link rel="stylesheet" href="/css/diary.css">
</head>

<body>
<div class="wholePage">
	<div class="container3">
		<div class="bookcover">
			<div class="bookdot">
				<div class="page">
	   
			       <div class="left-container">
			          <div class="left-visits">
			            <p>TODAY <span style="color:red;">0</span> | TOTAL 0</p>
			          </div>
			          <div class="left-box">
			            <img class="profile-img" src="/img/haha.jpg" alt="profile image" />
			            <div class="profile">
			              <p>매일매일 식물일기를 남겨보아요</p>
			              <div class="profile-name"></div>
			              <button class="myButtonD" type="button" onclick="location.href='/do/diaryRegForm'">등록</button>
			            </div>
			          </div>
			        </div>
	        
		        <div class="middle-container">
		          <div class="middle-title">
		            <div class="middle-desc">나의 식물일기</div>
		            <div class="middle-url">my plant diary</div>
		          </div>
					<div class="middle-box">
					<c:set var="now" value="<%=new java.util.Date()%>" />
					<c:set var="today"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd(E)" /></c:set> 
		            
		            <div class="date-container">
						<div class="today-date"><c:out value="${today}" /></div>
						<div class="calendar">
			                <div class="day">1</div>
			                <div class="day">2</div>
			                <div class="day">3</div>
			                <div class="day">4</div>
			                <div class="sat">5</div>
			                <div class="sun">6</div>
			                <div class="day">7</div>
			                <div class="day">8</div>
			                <div class="day">9</div>
			                <div class="day">10</div>
			                <div class="day">11</div>
			                <div class="sat">12</div>
			                <div class="sun">13</div>
			                <div class="day">14</div>
			                <div class="day">15</div>
			                <div class="day">16</div>
			                <div class="day">17</div>
			                <div class="day">18</div>
			                <div class="sat">19</div>
			                <div class="sun">20</div>
			                <div class="day">21</div>
			                <div class="day">22</div>
			                <div class="day">23</div>
			                <div class="day">24</div>
			                <div class="day">25</div>
			                <div class="sat">26</div>
			                <div class="sun">27</div>
			                <div class="day">28</div>
						</div>
		            </div>
		          
			        <div class="diary-container">
			        	<c:set value="${diary}" var="diary" />
			        	
			        		<div class="diary-box">
								<p class="date">${diary.wdate}</p>
								<p class="content">${diary.content}</p>
								<div class="modify">
									<button class="myButton" onclick="location.href='/do/diaryDetail?seqno=${diary.diary_seqno}&page=diaryModify'">수정</button> 
									<button class="myButton" onclick="location.href='diaryDelete.jsp?seqno=${diary.diary_seqno}'">삭제</button>
								</div>
							</div>
			        	
			        	
<%-- 						<c:forEach items="${diary}" var="diary">
							<div class="diary-box">
								<p class="date">${diary.getWdate()}</p>
								<p class="content">${diary.getContent()}</p>
								<p>${diary.diary_seqno()}</p>
								<c:set value="${diary}" var="diary" />
								<div class="modify">
									<button class="myButton" onclick="location.href='/diaryDetail.do?seqno=${diary.diary_seqno}&page=diaryModify'">수정</button> 
									<button class="myButton" onclick="location.href='diaryDelete.jsp?seqno=${diary.diary_seqno}'">삭제</button>
								</div>
							</div>
						</c:forEach> --%>
		        	</div>
		      		</div>
				</div>
				</div>
			</div>
		</div>
	</div>
</div>

<%@include file = "../footer.jsp" %>
</body>
</html>