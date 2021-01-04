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
import movie.MovieVO;
import movie.ScheduleVO;

@WebServlet("/Ticketing.do")
public class TicketingController extends HttpServlet{
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
	
	int schNo = Integer.parseInt(req.getParameter("ticketing"));
	instance.scheduleNo = schNo;
	int seatCnt = instance.maxRoom(schNo, scheduleList.get(0).getRoomNo());
	seatCnt = 20 - seatCnt;
	//instance.countRoom(schNo, scheduleList.get(0).getRoomNo());
	
	instance.seatNo();
	int[] seatNoArr = instance.seatNoArr;
	
	
	req.setAttribute("seatCnt", seatCnt);
	req.setAttribute("seatNoArr", seatNoArr);
	RequestDispatcher rd = req.getRequestDispatcher("ticketing.jsp");
	rd.forward(req, resp);
}
}
