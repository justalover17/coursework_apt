package com.coursework.dao;

import com.coursework.entity.OrderDetails;
import java.util.ArrayList;

public interface OrderDetailsDao {
    boolean addOrderItem(OrderDetails orderDetails);
    ArrayList<OrderDetails> getOrderItems(int orderId);
    boolean deleteOrderItem(int orderDetailId);
}