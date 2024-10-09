<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <style>
        body {
            background-color: #ffcccb; /* Light red background */
            color: #333; /* Dark grey text */
            font-family: Arial, sans-serif;
            text-align: center;
            padding: 50px;
        }
        table {
            margin: 0 auto; /* Center the table */
            border-collapse: collapse; /* Remove gaps between table cells */
        }
        td {
            padding: 10px; /* Add some padding */
        }
        input[type="text"], input[type="password"] {
            padding: 10px;
            border: 2px solid #333; /* Dark grey border */
            border-radius: 5px;
            width: 200px; /* Fixed width */
        }
        input[type="submit"] {
            background-color: #f08080; /* Light red submit button */
            color: white; /* White text */
            border: none; /* Remove border */
            padding: 10px 20px; /* Padding for the button */
            border-radius: 5px; /* Rounded corners */
            cursor: pointer; /* Pointer cursor */
            transition: background-color 0.3s; /* Smooth transition */
        }
        input[type="submit"]:hover {
            background-color: #ff6666; /* Darker red on hover */
        }
    </style>
</head>
<body>
<center>
    <h1>Login Page</h1>
    <form action="login" method="post">
        <table>
            <tr>
                <td>Email</td>
                <td><input type="text" name="email" required></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password" required></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Login"></td>
            </tr>
        </table>
    </form>
</center>
</body>
</html>
