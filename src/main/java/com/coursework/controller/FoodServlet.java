package com.coursework.controller;

import com.coursework.dao.FoodItemDao;
import com.coursework.dao.FoodItemDaoImpl;
import com.coursework.dao.CategoryDao;
import com.coursework.dao.CategoryDaoImpl;
import com.coursework.entity.FoodItem;
import com.coursework.entity.Category;
import com.coursework.entity.User;
import com.coursework.utils.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/food")
public class FoodServlet extends HttpServlet {

    private final FoodItemDao foodDao = new FoodItemDaoImpl();
    private final CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listFood(request, response);
                break;
            case "view":
                viewFood(request, response);
                break;
            case "add":
                showAddForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteFood(request, response);
                break;
            default:
                listFood(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("insert".equals(action)) {
            insertFood(request, response);
        } else if ("update".equals(action)) {
            updateFood(request, response);
        }
    }

    private void listFood(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<FoodItem> foods = foodDao.fetchAllFood();
        request.setAttribute("foods", foods);
        request.getRequestDispatcher("/WEB-INF/views/food-list.jsp")
                .forward(request, response);
    }

    private void viewFood(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        FoodItem food = foodDao.findFoodById(id);
        request.setAttribute("food", food);
        request.getRequestDispatcher("/WEB-INF/views/food-view.jsp")
                .forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = SessionUtil.getUser(request);
        if (user == null || !"admin".equals(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/food?action=list");
            return;
        }

        ArrayList<Category> categories = categoryDao.fetchAllCategories();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/WEB-INF/views/food-add.jsp")
                .forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = SessionUtil.getUser(request);
        if (user == null || !"admin".equals(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/food?action=list");
            return;
        }

        int id = Integer.parseInt(request.getParameter("id"));
        FoodItem food = foodDao.findFoodById(id);
        ArrayList<Category> categories = categoryDao.fetchAllCategories();

        request.setAttribute("food", food);
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/WEB-INF/views/food-edit.jsp")
                .forward(request, response);
    }

    private void insertFood(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String description = request.getParameter("description");

        FoodItem food = new FoodItem(name, price, categoryId);
        food.setDescription(description);
        foodDao.insertFood(food);

        response.sendRedirect(request.getContextPath() + "/food?action=list");
    }

    private void updateFood(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String description = request.getParameter("description");

        FoodItem food = new FoodItem(id, name, price, categoryId, description, null, null);
        foodDao.updateFood(food);

        response.sendRedirect(request.getContextPath() + "/food?action=list");
    }

    private void deleteFood(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        User user = SessionUtil.getUser(request);
        if (user == null || !"admin".equals(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/food?action=list");
            return;
        }

        int id = Integer.parseInt(request.getParameter("id"));
        foodDao.deleteFood(id);
        response.sendRedirect(request.getContextPath() + "/food?action=list");
    }
}