package com.tap.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.TapFoods.model.user;
import com.tapsfoods.dao.UserdDAO;
import com.tapsfoods.daoimpl.UserDaoImpl;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String phoneNumber = req.getParameter("phoneNumber");
        String password = req.getParameter("password");
        String address = req.getParameter("address");

        // Create a new user object
        user newUser = new user(username, email, phoneNumber, password, address);

        // Instantiate the DAO and add the user
        UserdDAO userDAO = new UserDaoImpl();
        int result = userDAO.addUser(newUser);

        if (result > 0) {
            // Registration successful
            resp.sendRedirect("login.jsp");
        } else {
            // Registration failed
            resp.sendRedirect("failure.jsp");
        }
    }
}
