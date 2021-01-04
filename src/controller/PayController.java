package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.MemberVO;
import movie.MovieDAO;
import movie.ScheduleVO;
import movie.TicketVO;

@WebServlet("/Pay.do")
public class PayController extends HttpServlet{
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
	ArrayList<ScheduleVO> scheduleList = instance.scheduleList;
	ArrayList<MemberVO> memberList = instance.memberList;
	
	instance.countRoom(instance.scheduleNo, scheduleList.get(0).getRoomNo());
	
	int seatNo = Integer.parseInt(req.getParameter("seatNo"));
	TicketVO vo = new TicketVO(instance.getMaxNo(), instance.scheduleNo, seatNo, memberList.get(instance.loginMember).getId());
	instance.ticketAdd(vo);
	
	instance.firstPay = true;
	
	RequestDispatcher rd = req.getRequestDispatcher("MovieList.do");
	rd.forward(req, resp);
}
}
