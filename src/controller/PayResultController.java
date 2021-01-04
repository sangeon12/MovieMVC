package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.MovieDAO;
import movie.TicketVO;

@WebServlet("/PayResult.do")
public class PayResultController extends HttpServlet{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	doProcess(req, resp);
}
@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	doProcess(req, resp);
	}

public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.setCharacterEncoding("utf-8");
	
	MovieDAO instance = MovieDAO.getInstance();
	instance.ticketAddList2();
	ArrayList<TicketVO> ticketList2 = instance.ticketList2;
	
	
	req.setAttribute("ticketList2", ticketList2);
	RequestDispatcher rd = req.getRequestDispatcher("payResult.jsp");
	rd.forward(req, resp);
}
}
