package com.coursework.utils;

import com.coursework.entity.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionUtil {

    public static void setAttribute(HttpServletRequest request, String key, Object value) {
        HttpSession session = request.getSession();
        session.setAttribute(key, value);
    }

    public static Object getAttribute(HttpServletRequest request, String key) {
        HttpSession session = request.getSession();
        return session.getAttribute(key);
    }

    public static User getUser(HttpServletRequest request) {
        return (User) getAttribute(request, "user");
    }

    public static void invalidate(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
    }
}