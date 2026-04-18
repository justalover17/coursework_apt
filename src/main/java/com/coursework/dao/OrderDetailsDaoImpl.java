package com.coursework.dao;

import com.coursework.entity.OrderDetails;
import com.coursework.utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;

public class OrderDetailsDaoImpl implements OrderDetailsDao {

    @Override
    public boolean addOrderItem(OrderDetails od) {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO order_details (order_id, food_id, quantity, subtotal) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, od.getOrderId());
            ps.setInt(2, od.getFoodId());
            ps.setInt(3, od.getQuantity());
            ps.setDouble(4, od.getSubtotal());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error adding order item: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
    }

    @Override
    public ArrayList<OrderDetails> getOrderItems(int orderId) {
        ArrayList<OrderDetails> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM order_details WHERE order_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetails od = new OrderDetails(
                        rs.getInt("order_detail_id"),
                        rs.getInt("order_id"),
                        rs.getInt("food_id"),
                        rs.getInt("quantity"),
                        rs.getDouble("subtotal")
                );
                list.add(od);
            }
        } catch (SQLException e) {
            System.out.println("Error getting order items: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        return list;
    }

    @Override
    public boolean deleteOrderItem(int orderDetailId) {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "DELETE FROM order_details WHERE order_detail_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderDetailId);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error deleting order item: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
    }
}