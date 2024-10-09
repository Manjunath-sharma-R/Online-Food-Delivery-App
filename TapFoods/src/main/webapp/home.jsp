<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.TapFoods.model.user" %>
<%@ page import="java.util.List" %>
<%@ page import="com.TapFoods.model.Restaurant" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
</head>
<body>
    <% user user = (user)session.getAttribute("loggedInUser"); %>
    <% if (user != null) { %>
        <h3>Welcome Back, <%= user.getUsername() %>!</h3>
    <% } else { %>
        <h3>You Haven't Logged In</h3>
    <% } %>
    
    <h2>Featured Restaurants</h2>
    <% 
        List<Restaurant> restaurantList = (List<Restaurant>) session.getAttribute("restaurantList"); 
        if (restaurantList != null) {
            for (Restaurant restaurant : restaurantList) {
    %>
                <div>
                    <h3><a href="menu?restaurantId=<%= restaurant.getRestaurantId() %>"><%= restaurant.getRestaurantName() %></a></h3>
                    <p>CuisineType: <%= restaurant.getCuisineType() %></p>
                    <p>Delivery Time: <%= restaurant.getDeliveryTime() %> minutes</p>
                </div>
                
    <% 
            }
        } else {
            out.println("<p>No featured restaurants available.</p>");
        }
    %>
    
</body>
</html>
