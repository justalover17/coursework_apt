package com.coursework.entity;

import java.sql.Timestamp;

public class Orders {

    private int orderId;
    private int userId;
    private double totalAmount;
    private String status;
    private Timestamp createdAt;

    // ✅ Empty constructor (VERY IMPORTANT)
    public Orders() {}

    // ✅ Constructor used in DAO
    public Orders(int orderId, int userId, double totalAmount, String status) {
        this.orderId = orderId;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    // ✅ Getters
    public int getOrderId() { return orderId; }
    public int getUserId() { return userId; }
    public double getTotalAmount() { return totalAmount; }
    public String getStatus() { return status; }
    public Timestamp getCreatedAt() { return createdAt; }

    // ✅ Setters
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public void setUserId(int userId) { this.userId = userId; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public void setStatus(String status) { this.status = status; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}