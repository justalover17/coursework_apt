package com.coursework.controller;

import com.coursework.dao.CartDao;
import com.coursework.dao.CartDaoImpl;
import com.coursework.dao.CartDetailsDao;
import com.coursework.dao.CartDetailsDaoImpl;
import com.coursework.dao.FoodItemDao;
import com.coursework.dao.FoodItemDaoImpl;
import com.coursework.entity.Cart;
import com.coursework.entity.CartDetails;
import com.coursework.entity.FoodItem;
import com.coursework.entity.User;
import com.coursework.utils.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private final CartDao cartDao = new CartDaoImpl();
    private final CartDetailsDao cartDetailsDao = new CartDetailsDaoImpl();
    private final FoodItemDao foodDao = new FoodItemDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "view";
        }

        switch (action) {
            case "view":
                viewCart(request, response);
                break;
            case "add":
                addToCart(request, response);
                break;
            case "remove":
                removeItem(request, response);
                break;
            case "update":
                updateQuantity(request, response);
                break;
            default:
                viewCart(request, response);
                break;
        }
    }

    private void viewCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = SessionUtil.getUser(request);

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Cart cart = cartDao.getCartByUserId(user.getUserId());

        if (cart != null) {
            ArrayList<CartDetails> items = cartDetailsDao.getCartItems(cart.getCartId());

            double total = 0;
            for (CartDetails item : items) {
                FoodItem food = foodDao.findFoodById(item.getFoodId());
                total += food.getPrice() * item.getQuantity();
            }

            request.setAttribute("cartItems", items);
            request.setAttribute("total", total);
        }

        request.getRequestDispatcher("/WEB-INF/views/cart-view.jsp")
                .forward(request, response);
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        User user = SessionUtil.getUser(request);

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int foodId = Integer.parseInt(request.getParameter("foodId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        Cart cart = cartDao.getCartByUserId(user.getUserId());

        if (cart == null) {
            Cart newCart = new Cart(user.getUserId());
            cartDao.createCart(newCart);
            cart = cartDao.getCartByUserId(user.getUserId());
        }

        CartDetails cartItem = new CartDetails(cart.getCartId(), foodId, quantity);
        cartDetailsDao.addToCart(cartItem);

        response.sendRedirect(request.getContextPath() + "/cart?action=view");
    }

    private void removeItem(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int cartDetailId = Integer.parseInt(request.getParameter("cartDetailId"));
        cartDetailsDao.removeItem(cartDetailId);

        response.sendRedirect(request.getContextPath() + "/cart?action=view");
    }

    private void updateQuantity(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int cartDetailId = Integer.parseInt(request.getParameter("cartDetailId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        CartDetails cd = new CartDetails(cartDetailId, 0, 0, quantity);
        cartDetailsDao.updateQuantity(cd);

        response.sendRedirect(request.getContextPath() + "/cart?action=view");
    }
}