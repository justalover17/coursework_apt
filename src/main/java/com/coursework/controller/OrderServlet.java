package com.coursework.controller;

import com.coursework.dao.OrdersDao;
import com.coursework.dao.OrdersDaoImpl;
import com.coursework.dao.OrderDetailsDao;
import com.coursework.dao.OrderDetailsDaoImpl;
import com.coursework.dao.CartDao;
import com.coursework.dao.CartDaoImpl;
import com.coursework.dao.CartDetailsDao;
import com.coursework.dao.CartDetailsDaoImpl;
import com.coursework.dao.FoodItemDao;
import com.coursework.dao.FoodItemDaoImpl;
import com.coursework.entity.Orders;
import com.coursework.entity.OrderDetails;
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

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    private final OrdersDao orderDao = new OrdersDaoImpl();
    private final OrderDetailsDao orderDetailsDao = new OrderDetailsDaoImpl();
    private final CartDao cartDao = new CartDaoImpl();
    private final CartDetailsDao cartDetailsDao = new CartDetailsDaoImpl();
    private final FoodItemDao foodDao = new FoodItemDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "history";
        }

        switch (action) {
            case "checkout":
                checkout(request, response);
                break;
            case "history":
                orderHistory(request, response);
                break;
            case "details":
                orderDetails(request, response);
                break;
            default:
                orderHistory(request, response);
                break;
        }
    }

    private void checkout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = SessionUtil.getUser(request);

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Cart cart = cartDao.getCartByUserId(user.getUserId());

        if (cart == null) {
            response.sendRedirect(request.getContextPath() + "/cart?action=view");
            return;
        }

        ArrayList<CartDetails> cartItems = cartDetailsDao.getCartItems(cart.getCartId());

        if (cartItems.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/cart?action=view");
            return;
        }

        double total = 0;
        for (CartDetails item : cartItems) {
            FoodItem food = foodDao.findFoodById(item.getFoodId());
            total += food.getPrice() * item.getQuantity();
        }

        Orders order = new Orders(0, user.getUserId(), total, "Pending");
        orderDao.placeOrder(order);

        ArrayList<Orders> orders = orderDao.getOrdersByUserId(user.getUserId());
        int orderId = orders.get(0).getOrderId();

        for (CartDetails item : cartItems) {
            FoodItem food = foodDao.findFoodById(item.getFoodId());
            double subtotal = food.getPrice() * item.getQuantity();
            OrderDetails od = new OrderDetails(0, orderId, item.getFoodId(), item.getQuantity(), subtotal);
            orderDetailsDao.addOrderItem(od);
        }

        for (CartDetails item : cartItems) {
            cartDetailsDao.removeItem(item.getCartDetailId());
        }

        response.sendRedirect(request.getContextPath() + "/order?action=history");
    }

    private void orderHistory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = SessionUtil.getUser(request);

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        ArrayList<Orders> orders = orderDao.getOrdersByUserId(user.getUserId());
        request.setAttribute("orders", orders);

        request.getRequestDispatcher("/WEB-INF/views/order-history.jsp")
                .forward(request, response);
    }

    private void orderDetails(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int orderId = Integer.parseInt(request.getParameter("orderId"));

        Orders order = orderDao.findOrderById(orderId);
        ArrayList<OrderDetails> items = orderDetailsDao.getOrderItems(orderId);

        double total = 0;
        for (OrderDetails item : items) {
            total += item.getSubtotal();
        }

        request.setAttribute("order", order);
        request.setAttribute("items", items);
        request.setAttribute("total", total);

        request.getRequestDispatcher("/WEB-INF/views/order-details.jsp")
                .forward(request, response);
    }
}