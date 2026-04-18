package com.coursework.dao;

import com.coursework.entity.Orders;
import java.util.ArrayList;

public interface OrdersDao {
    boolean placeOrder(Orders order);
    ArrayList<Orders> fetchAllOrders();
    ArrayList<Orders> getOrdersByUserId(int userId);
    Orders findOrderById(int id);
    boolean updateOrderStatus(int orderId, String status);
    boolean deleteOrder(int id);
}