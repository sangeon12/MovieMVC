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

@WebServlet("/Schedule.do")
public class ScheduleController extends HttpServlet{
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
	ArrayList<MovieVO> movieList = instance.movieList;
	
	int movieNo = Integer.parseInt(req.getParameter("movieNo"));
	instance.scheduleAdd(movieNo);
	instance.scheduleMovie(movieNo);
	
	req.setAttribute("scheduleList", scheduleList);
	req.setAttribute("movieList", movieList);
	RequestDispatcher rd = req.getRequestDispatcher("schedule.jsp");
	rd.forward(req, resp);
}
}
