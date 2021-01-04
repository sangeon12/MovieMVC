<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="movie.MovieVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시작화면</title>
<style type="text/css">
.list{
	display: gird;
	grid-template-columns: 1fr 1fr 1fr;
}
</style>
</head>
<body>
<%@ include file = "header.jsp" %>
<%
	if(instance.first) {
		instance.firstMember();
		instance.ticketAddList();
		instance.resetSeatArr();
	}
	
	if(request.getParameter("loginAlert") != null){
		if(!instance.first){
			if(instance.loginCheck > 0){
					%><script type="text/javascript">alert("로그인 성공");</script><%			
			}else{
				if(instance.loginCheck == 0 ){
					%><script type="text/javascript">alert("아이디 또는 비밀번호가 틀렸습니다.");</script><%	
				}else if(instance.loginCheck == -1 ){
					%><script type="text/javascript">alert("아이디 또는 비밀번호창이 비어있습니다.");</script><%
				}else{
					%><script type="text/javascript">alert("로그아웃 되었습니다.");</script><%
				}
			}
		}
	}
%>

	<%
	if(instance.loginCheck > 0){
		ArrayList<MovieVO> movieList = (ArrayList<MovieVO>)request.getAttribute("movieList");
		
		for(MovieVO list : movieList){
		%>
		<div class="list">
			<a href="Schedule.do?movieNo=<%= list.getMovieNo() %>"><img alt="<%= list.getImg() %>" src="images/<%= list.getImg() %>" width="200" height="200"><br></a>
			<%=list.getMovieName() %>
		</div>	
		<%	
		}
	}else{
	%>
		<pre>
영화 예매를 원하시면 로그인을 해주세요.
영화 추가를 원하시면 관리자 로그인을 해주세요.		
		</pre>
	<%
	}
	%>
<%@ include file = "footer.jsp" %>
</body>
</html>
