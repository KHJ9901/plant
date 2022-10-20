<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
					<!-- 왼쪽 -->
					<div class="left-container">
						<div class="left-visits">
							<p>TODAY <span style="color:red;">0</span> | TOTAL 0</p>
						</div>
						<div class="left-box">
							<img class="profile-img" src="/img/haha.jpg" alt="profile image" />
							<div class="profile">
								<p>매일매일 식물일기를 남겨보아요</p>
		              			<button class="myButtonD" type="button" onclick="location.href='/diary/diaryReg'">등록</button>
		            		</div>
	          			</div>
	        		</div>
	        		
	        		<!-- 중간 -->
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

							<div class="diary-container2">
								<c:set value="${loginUser}" var="user"/>
								<form method="post" enctype="multipart/form-data" action="/diary/diaryRegister" onsubmit="return check(this)">
								<input type="hidden" name="id" value = "${user.id}">
								
								<table>
								<tr>
								<td><textarea cols="120" rows="15" name="content"></textarea></td>
								</tr>
								<!-- <tr>
								<td><input type="file" name="filename"></td>
								</tr> -->
								</table>
								<div class="modify">
								<input type="submit" class="myButton" value="등록">
								<input type="reset" class="myButton" onclick="history.back(-1);" value="취소">
								</div>
								</form>
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