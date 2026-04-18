package com.coursework.controller;

import com.coursework.utils.CookieUtil;
import com.coursework.utils.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SessionUtil.invalidate(request);
        CookieUtil.deleteCookie(response, "email");

        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}