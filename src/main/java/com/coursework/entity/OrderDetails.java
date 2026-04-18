package com.coursework.entity;

public class OrderDetails {

    private int orderDetailId;
    private int orderId;
    private int foodId;
    private int quantity;
    private double subtotal;

    public OrderDetails() {}

    public OrderDetails(int orderDetailId, int orderId, int foodId, int quantity, double subtotal) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.foodId = foodId;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    public int getOrderDetailId() { return orderDetailId; }
    public int getOrderId() { return orderId; }
    public int getFoodId() { return foodId; }
    public int getQuantity() { return quantity; }
    public double getSubtotal() { return subtotal; }

    public void setOrderDetailId(int orderDetailId) { this.orderDetailId = orderDetailId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public void setFoodId(int foodId) { this.foodId = foodId; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
}