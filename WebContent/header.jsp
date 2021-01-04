<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="movie.MovieDAO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>영화 예매 서비스</h2>
	
	<%
		MovieDAO instance = MovieDAO.getInstance();
	
		if(instance.loginCheck <= 0){
	%>
		<form action="Login.do">
		<input type="text" name="id">&nbsp;&nbsp;&nbsp;
		<input type="password" name="pw">&nbsp;&nbsp;&nbsp;
		<input type="hidden" name="log" value="1">&nbsp;&nbsp;&nbsp;
		<input type="submit" value="로그인">&nbsp;&nbsp;&nbsp;
		</form>
		<hr>
	<%		
		}else if(instance.loginCheck == 1){
			%>
			<button onclick="location.href='PayResult.do'">내 예매 정보</button>
			<button onclick="location.href='Login.do?log=0'">로그아웃</button><br>
			<a href="MovieList.do">메인화면</a>
			<a href="SortList.do?category=01">액션</a>
			<a href="SortList.do?category=02">로맨스</a>
			<a href="SortList.do?category=03">코미디</a>
			<a href="SortList.do?category=04">스릴러</a>
			<a href="SortList.do?category=05">애니메이션</a>
			<hr>
		<%		
			}else if(instance.loginCheck == 2){
		%>
			<button>영화 추가하기</button>
			<button onclick="location.href='AdminPayResult.do'">전체 예매 목록</button>
			<button onclick="location.href='Login.do?log=0'">로그아웃</button><br>
			<a href="MovieList.do">메인화면</a>
			<a href="SortList.do?category=01">액션</a>
			<a href="SortList.do?category=02">로맨스</a>
			<a href="SortList.do?category=03">코미디</a>
			<a href="SortList.do?category=04">스릴러</a>
			<a href="SortList.do?category=05">애니메이션</a>
			<hr>
		<%		
			}
	%>
	</body>
</html>