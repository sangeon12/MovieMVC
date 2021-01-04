<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="movie.ScheduleVO" %>
    <%@ page import="movie.MovieVO" %>
    <%@ page import="java.util.ArrayList" %> 
    <%@ page import="java.sql.Time" %> 
    <%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file = "header.jsp" %>
	<%
		ArrayList<ScheduleVO> scheduleList = (ArrayList<ScheduleVO>)request.getAttribute("scheduleList");
		ArrayList<MovieVO> movieList = (ArrayList<MovieVO>)request.getAttribute("movieList");
		//&nbsp;&nbsp;&nbsp;
		
		SimpleDateFormat transFormat = new SimpleDateFormat("hh:mm");
		String time1 = transFormat.format(scheduleList.get(0).getRunDay());
		String time2 = transFormat.format(scheduleList.get(1).getRunDay());
		String time3 = transFormat.format(scheduleList.get(2).getRunDay());
		String time4 = transFormat.format(scheduleList.get(3).getRunDay());	
	%>
	
	<img alt="<%= movieList.get(0).getImg() %>" src="images/<%= movieList.get(0).getImg() %>" width="200" height="200"><br>
	영화이름 : <%= movieList.get(0).getMovieName() %><br> 
	장르 : <%= instance.category(movieList.get(0).getCategory()) %><br> 
	상영시간 : <%= movieList.get(0).getRuntime() %><br>
	후기 : <%= movieList.get(0).getInfo() %><br>
	<form action="Ticketing.do">
		예매 : <select name="ticketing">
    			<option value="<%= scheduleList.get(0).getSchNo() %>"><%= scheduleList.get(0).getRoomNo() %>관 <%= time1 %></option>
    			<option value="<%= scheduleList.get(1).getSchNo() %>"><%= scheduleList.get(1).getRoomNo() %>관 <%= time2 %></option>
    			<option value="<%= scheduleList.get(2).getSchNo() %>"><%= scheduleList.get(2).getRoomNo() %>관 <%= time3 %></option>
    			<option value="<%= scheduleList.get(3).getSchNo() %>"><%= scheduleList.get(3).getRoomNo() %>관 <%= time4 %></option>
			</select>
			<input type="submit" value="예매하기">
	</form>
	<%@ include file = "footer.jsp" %>
</body>
</html>