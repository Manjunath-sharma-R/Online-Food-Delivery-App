package com.tap.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.TapFoods.model.user;
import com.tapsfoods.dao.UserdDAO;
import com.tapsfoods.daoimpl.UserDaoImpl;


@WebServlet("/login")
public class LoginServlet extends HttpServlet 
{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		
		UserdDAO userDAO= new UserDaoImpl();
		user user=userDAO.getUser(email);
		
		if (password.equals(user.getPassword())) //imp
		{
			
			
			HttpSession session=req.getSession();
			
			session.setAttribute("loggedInUser", user);
			resp.sendRedirect("home");
		}
		else {
			resp.sendRedirect("failure.jsp");
		}
	}
	
	

}
