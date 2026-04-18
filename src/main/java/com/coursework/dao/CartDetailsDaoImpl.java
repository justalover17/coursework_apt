package com.coursework.dao;

import com.coursework.entity.CartDetails;
import com.coursework.utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;

public class CartDetailsDaoImpl implements CartDetailsDao {

    @Override
    public boolean addToCart(CartDetails cd) {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO cart_details (cart_id, food_id, quantity) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cd.getCartId());
            ps.setInt(2, cd.getFoodId());
            ps.setInt(3, cd.getQuantity());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error adding to cart: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
    }

    @Override
    public ArrayList<CartDetails> getCartItems(int cartId) {
        ArrayList<CartDetails> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM cart_details WHERE cart_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cartId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CartDetails cd = new CartDetails(
                        rs.getInt("cart_detail_id"),
                        rs.getInt("cart_id"),
                        rs.getInt("food_id"),
                        rs.getInt("quantity")
                );
                list.add(cd);
            }
        } catch (SQLException e) {
            System.out.println("Error getting cart items: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        return list;
    }

    @Override
    public boolean updateQuantity(CartDetails cd) {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "UPDATE cart_details SET quantity=? WHERE cart_detail_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cd.getQuantity());
            ps.setInt(2, cd.getCartDetailId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error updating quantity: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
    }

    @Override
    public boolean removeItem(int cartDetailId) {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "DELETE FROM cart_details WHERE cart_detail_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cartDetailId);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error removing item: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
    }
}