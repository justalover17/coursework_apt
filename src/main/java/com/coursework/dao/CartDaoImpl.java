package com.coursework.dao;

import com.coursework.entity.Cart;
import com.coursework.utils.DatabaseConnection;
import java.sql.*;

public class CartDaoImpl implements CartDao {

    @Override
    public boolean createCart(Cart cart) {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO cart (user_id) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cart.getUserId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error creating cart: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
    }

    @Override
    public Cart getCartByUserId(int userId) {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM cart WHERE user_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Cart(
                        rs.getInt("cart_id"),
                        rs.getInt("user_id"),
                        rs.getTimestamp("created_at")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error getting cart: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        return null;
    }

    @Override
    public boolean deleteCart(int cartId) {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "DELETE FROM cart WHERE cart_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cartId);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error deleting cart: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
    }
}