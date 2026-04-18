package com.coursework.controller;

import com.coursework.dao.UserDao;
import com.coursework.dao.UserDaoImpl;
import com.coursework.dao.CartDao;
import com.coursework.dao.CartDaoImpl;
import com.coursework.entity.User;
import com.coursework.entity.Cart;
import com.coursework.utils.PasswordUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private final UserDao userDao = new UserDaoImpl();
    private final CartDao cartDao = new CartDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/register.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");

        User existingUser = userDao.findUserByEmail(email);
        if (existingUser != null) {
            request.setAttribute("error", "Email already registered.");
            request.getRequestDispatcher("/WEB-INF/views/register.jsp")
                    .forward(request, response);
            return;
        }

        String hashedPassword = PasswordUtil.hashPassword(password);

        User user = new User(name, email, hashedPassword);
        user.setPhone(phone);
        user.setRole("customer");

        boolean inserted = userDao.insertUser(user);

        if (inserted) {
            User newUser = userDao.findUserByEmail(email);
            Cart cart = new Cart(newUser.getUserId());
            cartDao.createCart(cart);
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            request.setAttribute("error", "Registration failed. Please try again.");
            request.getRequestDispatcher("/WEB-INF/views/register.jsp")
                    .forward(request, response);
        }
    }
}