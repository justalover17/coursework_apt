package com.coursework.dao;

import com.coursework.entity.FoodItem;
import com.coursework.utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;

public class FoodItemDaoImpl implements FoodItemDao {

    @Override
    public boolean insertFood(FoodItem food) {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO food_item (name, price, category_id, description) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, food.getName());
            ps.setDouble(2, food.getPrice());
            ps.setInt(3, food.getCategoryId());
            ps.setString(4, food.getDescription());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error inserting food: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
    }

    @Override
    public ArrayList<FoodItem> fetchAllFood() {
        ArrayList<FoodItem> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM food_item";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                FoodItem food = new FoodItem(
                        rs.getInt("food_id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("category_id"),
                        rs.getString("description"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                );
                list.add(food);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching food: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        return list;
    }

    @Override
    public FoodItem findFoodById(int id) {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM food_item WHERE food_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new FoodItem(
                        rs.getInt("food_id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("category_id"),
                        rs.getString("description"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error finding food: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        return null;
    }

    @Override
    public boolean updateFood(FoodItem food) {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "UPDATE food_item SET name=?, price=?, category_id=?, description=? WHERE food_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, food.getName());
            ps.setDouble(2, food.getPrice());
            ps.setInt(3, food.getCategoryId());
            ps.setString(4, food.getDescription());
            ps.setInt(5, food.getFoodId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error updating food: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
    }

    @Override
    public boolean deleteFood(int id) {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "DELETE FROM food_item WHERE food_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error deleting food: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
    }
}