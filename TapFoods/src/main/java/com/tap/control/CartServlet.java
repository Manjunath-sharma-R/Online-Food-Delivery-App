package com.tap.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tapsfoods.daoimpl.MenuDaoImpl;
import com.TapFoods.model.Cart;
import com.TapFoods.model.CartItem;
import com.TapFoods.model.Menu;


@WebServlet("/cart")
public class CartServlet extends HttpServlet
{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		HttpSession session = req.getSession();
		Cart cart =(Cart) session.getAttribute("cart");
		
		if(cart == null)
		{
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		
		String action = (String)req.getParameter("action");
		if("add".equals(action))
		{
			addItemToCart(req, cart);
		}
		else if("update".equals(action))
		{
			updateItemInCart(req, cart);
		}
		else if("remove".equals(action))
		{
			removeItemFromCart(req, cart);
		}
		
		session.setAttribute("cart", cart);
		resp.sendRedirect("cart.jsp");
	}
	
	private void addItemToCart(HttpServletRequest req, Cart cart) 
	{
		int menuId = Integer.parseInt(req.getParameter("menuId"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		
		MenuDaoImpl mdaoi = new MenuDaoImpl();
		Menu menuItem = mdaoi.getMenuItem(menuId);
		
//		HttpSession session=req.getSession();
//		session.setAttribute("restaurantId",menuItem.getRestaurantId() );
		
		if(menuItem != null)
		{
			CartItem item = new CartItem(menuItem.getMenuId(),
					menuItem.getRestaurantId(),
					menuItem.getItemName(),
					quantity,
					menuItem.getPrice(),
					quantity*(menuItem.getPrice()));
			
			cart.addItem(item);
		}
		
	}
	private void updateItemInCart(HttpServletRequest req, Cart cart) 
	{
		int itemId = Integer.parseInt(req.getParameter("menuId"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		cart.updateItem(itemId, quantity);
	}
	
	private void removeItemFromCart(HttpServletRequest req, Cart cart) 
	{
		int itemId = Integer.parseInt(req.getParameter("menuId"));
		cart.removeItem(itemId);
	}


}
