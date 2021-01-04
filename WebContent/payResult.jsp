<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="movie.TicketVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file = "header.jsp" %>
	<%
		ArrayList<TicketVO> ticketList2 = (ArrayList<TicketVO>)request.getAttribute("ticketList2");
	%>
	<table border="1">
		<tr>
			<th>티켓 번호</th>
			<th>결제한 날짜</th>
			<th>영화 이름</th>
			<th>내가 선택한 상영관 스케줄 번호</th>
			<th>내가 선택한 상영관과 좌석 번호</th>
			<th>id</th>
		</tr>
	<%
		for(TicketVO list : ticketList2){
	%>
		<tr>
			<td><%= list.getTicketNo() %></td>
			<td><%= list.getBookDate() %></td>
			<td><%= instance.movieSchNo(list.getSchNo()) %></td>
			<td><%= list.getSchNo() %></td>
			<td><%= list.getSeatNo() %></td>
			<td><%= list.getId() %></td>
		</tr>
	<%		
		}
	%>
	</table>
	<%@ include file = "footer.jsp" %>
</body>
</html>