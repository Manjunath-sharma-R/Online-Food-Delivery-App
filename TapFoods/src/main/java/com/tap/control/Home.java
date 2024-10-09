package com.tap.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.TapFoods.model.Restaurant;
import com.tapsfoods.daoimpl.RestaurantDaoImpl;


@WebServlet("/home")
public class Home extends HttpServlet {
	private RestaurantDaoImpl restaurantDAO;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 restaurantDAO=new RestaurantDaoImpl();
		 List<Restaurant> restaurantList= restaurantDAO.fetchAllRestaurant();
		 HttpSession session=req.getSession();
		 session.setAttribute("restaurantList", restaurantList);
		
		
		resp.sendRedirect("home.jsp");
	}
	

}
