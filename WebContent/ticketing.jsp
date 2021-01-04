<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file = "header.jsp" %>
	<%
		int seatCnt = (int)request.getAttribute("seatCnt");
	%>
	남은좌석 : <%= seatCnt %> <br>
	<%
			
			for(int i = 1; i <=20; i++){
			int[] seatNoArr = (int[])request.getAttribute("seatNoArr");
			System.out.println(seatNoArr[i-1]+" "+ i);
			if(seatNoArr[i-1] == i){
				%>
				<button disabled><%= i %></button>&nbsp;&nbsp;&nbsp;
				<%	
			}else{
				%>
				<button onclick="location.href='pay.jsp?seatNo=<%=i%>'"><%= i %></button>&nbsp;&nbsp;&nbsp;
				<%	
			}	
		}	
	%>
	<%@ include file = "footer.jsp" %>
</body>
</html>