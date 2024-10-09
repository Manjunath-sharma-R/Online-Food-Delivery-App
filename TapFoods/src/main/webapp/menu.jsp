<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.TapFoods.model.Menu, java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Menu Page</title>
</head>
<body>

<div>

    <!-- Display Restaurant Name -->
    <div>
        <%= session.getAttribute("restaurantName") %>
    </div>

    <!-- Menu Items -->
    <div>
        <% List<Menu> menuItems = (List<Menu>) session.getAttribute("menuItems");
        if (menuItems != null) {
            for (Menu menuItem : menuItems) { %>
                <div>
                    <img src="<%= menuItem.getImgPath() %>" alt="<%= menuItem.getItemName() %>">
                    <div>
                        <h2><%= menuItem.getItemName() %></h2>
                        <p><%= menuItem.getDescription() %></p>
                        <p>₹<%= menuItem.getPrice() %></p>
                    </div>
                    <form action="cart">
                        <input type="hidden" name="menuId" value="<%= menuItem.getMenuId() %>">
                        Quantity:<input type="number" name="quantity" value="1" min="1">
                        <input type="submit" value="Add to cart">
                        <input type="hidden" name="action" value="add">
                    </form>
                </div>
            <% } 
        } else { %>
            <p>No menu items available.</p>
        <% } %>
    </div>
    
</div>

</body>
</html>
