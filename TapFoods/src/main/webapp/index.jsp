<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <style>
        body {
            background-color: #ffcccb; /* Light red background */
            color: #333; /* Dark grey text */
            font-family: Arial, sans-serif;
            text-align: center;
            padding: 50px;
        }
        a {
            color: #333; /* Dark grey links */
            text-decoration: none;
            padding: 10px 20px;
            border: 2px solid #333; /* Dark grey border */
            border-radius: 5px;
            margin: 5px;
            transition: background-color 0.3s, color 0.3s;
        }
        a:hover {
            background-color: #f08080; /* Darker light red on hover */
            color: white; /* White text on hover */
        }
    </style>
</head>
<body>
    <h1>Welcome to Our Website</h1>
    <a href="signUp.jsp">Sign Up</a> 
    <a href="login.jsp">Login</a>
</body>
</html>
