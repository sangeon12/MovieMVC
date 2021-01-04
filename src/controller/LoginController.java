package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.MovieDAO;

@WebServlet("/Login.do")
public class LoginController extends HttpServlet{
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
	
	String id = req.getParameter("id");
	String pw = req.getParameter("pw");
	String msg = "MovieList.do?loginAlert=1";
	
	if(Integer.parseInt(req.getParameter("log")) == 0) {
		instance.loginCheck = -2; //로그아웃
	}else {
		if(id.equals("") || pw.equals("")) {
			instance.loginCheck = -1; //공백체크
		}else {
			if(!id.equals("admin")) {
				if(instance.checkLogin(id, pw)) {
					instance.loginCheck = 1;
				}else {
					instance.loginCheck = 0;
				}
			}else {
				if(pw.equals("admin")) {
					instance.loginCheck = 2;
				}else {
					instance.loginCheck = 0;
				}
			}
		}
	}
	
	RequestDispatcher rd = req.getRequestDispatcher(msg);
	rd.forward(req, resp);
}
}
