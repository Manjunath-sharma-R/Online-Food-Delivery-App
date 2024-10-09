package com.tap.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tapsfoods.daoimpl.MenuDaoImpl;
import com.tapsfoods.daoimpl.RestaurantDaoImpl;
import com.TapFoods.model.Menu;
import com.TapFoods.model.Restaurant;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int restaurantId = Integer.parseInt(req.getParameter("restaurantId"));
        
        // DAO instances
        MenuDaoImpl menuDao = new MenuDaoImpl();
        RestaurantDaoImpl restaurantDao = new RestaurantDaoImpl();

        // Fetching menu items and restaurant details
        List<Menu> menuItems = menuDao.fetchSpecificMenuItem(restaurantId);
        Restaurant restaurant = restaurantDao.fetchSpecificRestaurant(restaurantId);
        
        // Storing data in the session
        HttpSession session = req.getSession();
        session.setAttribute("menuItems", menuItems);
        session.setAttribute("restaurantId", restaurantId);
        session.setAttribute("restaurantName", restaurant.getRestaurantName());
        
        // Redirecting to the menu page
        resp.sendRedirect("menu.jsp");
    }
}
